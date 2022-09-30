import cv2
import webbrowser
import os

RTSP_URL = 'rtsp://192.168.1.123:8554/stream'

os.environ['OPENCV_FFMPEG_CAPTURE_OPTIONS'] = 'rtsp_transport;udp'


def first_reading(url):
    url_parts = str(url).split("/")
    visit_id = url_parts[-1]
    print("Visit id is " + visit_id)


def readQRCode():
    cap = cv2.VideoCapture(RTSP_URL, cv2.CAP_FFMPEG)
    # initialize the cv2 QRCode detector
    detector = cv2.QRCodeDetector()

    while True:
        _, img = cap.read()
        # detect and decode
        data, bbox, _ = detector.detectAndDecode(img)
        # check if there is a QRCode in the image
        if data:
            a=data
            #first_reading(data)
            break
        #cv2.imshow("QRCODScanner", img)
        #if cv2.waitKey(1) == ord("q"):
        #    break
        #  first_reading(a)
    print(a)
    cap.release()

# b=webbrowser.open(str(a))

# cv2.destroyAllWindows()
