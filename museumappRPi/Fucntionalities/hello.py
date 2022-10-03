import logging
from time import sleep

from flask import Flask, render_template, url_for, redirect, Response, jsonify
import Visit as visit
import threading
import argparse
import datetime, time
import imutils
import cv2
import os
from functools import wraps
logging.getLogger("urllib3").setLevel(logging.WARNING)

app = Flask(__name__, template_folder="../templates")
app.config['SERVER_NAME'] = '192.168.1.157:8000'
app.config['DEBUG'] = True
# app.config['PREFERRED_URL_SCHEME'] = 'http'
# app.config['APPLICATION_ROOT'] = '/Users/carolina/Desktop/MuseumApp/museumappRPi'

# initialize the output frame and a lock used to ensure thread-safe
# exchanges of the output frames (useful when multiple browsers/tabs are viewing the stream)
outputFrame = None
lock = threading.Lock()
qrcode = ''

data = ""

os.environ['OPENCV_FFMPEG_CAPTURE_OPTIONS'] = 'rtsp_transport;udp'

source = "rtsp://192.168.1.123:8554/stream"
cap = cv2.VideoCapture(source, cv2.CAP_FFMPEG)
detector = cv2.QRCodeDetector()
time.sleep(2.0)


# visit_id = 19
@app.route('/')
def read_qrcode():
    return render_template('read-qrcode.html')


@app.route('/qrcode_detection')
def qrcode_detection():
    global qrcode, data
    if data != "":
        qrcode = reading(data)
    else:
        qrcode = 'false'
    return jsonify(qrcode)


def reading(url):
    url_parts = str(url).split("/")
    visit_id = url_parts[-1]
    return visit_id
    # visitInfo = visit.getVisitInfo(visit_id)
    # print("Visit info: " + str(visitInfo))
    # visit.checkVisit(visitInfo, visit_id)


def stream(frameCount):
    global outputFrame, lock, data,qrcode
    if cap.isOpened():
        # cv2.namedWindow(('IP camera DEMO'), cv2.WINDOW_AUTOSIZE)
        while True:
            ret_val, frame = cap.read()
            # print("Is there a frame? " + str(ret_val))
            if frame.shape:
                frame = cv2.resize(frame, (640, 360))
                data, bbox, _ = detector.detectAndDecode(frame)
                with lock:
                    outputFrame = frame.copy()
            else:
                continue
    else:
        print('camera open failed')


def generate():
    # grab global references to the output frame and lock variables
    global outputFrame, lock

    # loop over frames from the output stream
    while True:
        # wait until the lock is acquired
        with lock:
            # check if the output frame is available, otherwise skip
            # the iteration of the loop
            if outputFrame is None:
                continue

            # encode the frame in JPEG format
            (flag, encodedImage) = cv2.imencode(".jpg", outputFrame)

            # ensure the frame was successfully encoded
            if not flag:
                continue

        # yield the output frame in the byte format
        yield (b'--frame\r\n' b'Content-Type: image/jpeg\r\n\r\n' +
               bytearray(encodedImage) + b'\r\n')


@app.route("/video_feed")
def video_feed():
    # return the response generated along with the specific media
    # type (mime type)
    return Response(generate(),
                    mimetype="multipart/x-mixed-replace; boundary=frame")


@app.route('/end-exhibitor/<visitId>')
def end_exhibitor(visitId):
    visit.endExhibitor(visitId)
    return redirect(url_for('read_qrcode'))


@app.route('/list-artifacts/<visitId>')
def list_artifacts(visitId):
    print("Chegou a listArtifacts")
    artifacts = visit.listShowArtifacts(visitId)[0]
    print(artifacts)
    # for i in range(len(artifacts)):
    # led_name = artifacts[i][12]
    # leds.controlArtifact(led_name=led_name, on_off=True)
    exhibitorName = visit.listShowArtifacts(visitId)[1]
    print("visit_id= " + str(visitId))
    return render_template('list-artifacts.html', artifacts=artifacts, exhibitor_name=exhibitorName, visit_id=visitId)


@app.route('/artifact-info/<artifact_id>/<visit_id>')
def artifact_info(artifact_id, visit_id):
    print("Artifact Id: " + artifact_id)
    artifacts = visit.listShowArtifacts(visit_id)[0]
    for i in range(len(artifacts)):
        led_name = artifacts[i][12]
        # leds.controlArtifact(led_name=led_name, on_off=False)
    artifact = visit.ArtifactInfo(artifact_id)[0]
    led_name = artifact["name"]
    # leds.controlArtifact(led_name=led_name, on_off=True)
    exhibitorName = visit.ArtifactInfo(artifact_id)[1]
    return render_template('artifact-info.html', artifact=artifact, exhibitor_name=exhibitorName)


@app.route('/error-exhibitor-not-in-visit/<visit_id>')
def error_exhibitor_not_in_visit(visit_id):
    artifact_id = 7
    rightExhibitorName = visit.ExhibitorPhase(visit_id)[0]
    exhibitorName = visit.ArtifactInfo(artifact_id)[1]
    error = visit.ExhibitorPhase(visit_id)[1]
    if error == 0:
        message = "Este expositor não faz parte da sua visita."
    elif error == 1:
        message = "Ainda não está na altura certa de visitar este expositor."
    else:
        message = "Ups! Ocorreu um erro! \n Por favor dirija-se a alguém do museu. \n Pedimos desculpa pelo incómodo "
    print(exhibitorName)
    return render_template('error-exhibitor-not-in-visit.html', right_exhibitor_name=rightExhibitorName,
                           exhibitor_name=exhibitorName, message=message)


if __name__ == '__main__':
    # construct the argument parser and parse command line arguments
    ap = argparse.ArgumentParser()
    ap.add_argument("-i", "--ip", type=str, required=False, default='0.0.0.0',
                    help="ip address of the device")
    ap.add_argument("-o", "--port", type=int, required=False, default=8000,
                    help="ephemeral port number of the server (1024 to 65535)")
    ap.add_argument("-f", "--frame-count", type=int, default=32,
                    help="# of frames used to construct the background model")
    args = vars(ap.parse_args())

    t = threading.Thread(target=stream, args=(args["frame_count"],))
    t.daemon = True
    t.start()

    # start the flask app
    app.run(host=args["ip"], port=args["port"], debug=True,
            threaded=True, use_reloader=False)

# release the video stream pointer
cap.release()
cv2.destroyAllWindows()
