import cv2
import webbrowser

import websocket as websocket

import Fucntionalities.Visit as visit

RTSP_URL = 'rtsp://192.168.1.123:8554/stream'

ws = websocket.WebSocketApp(self.ws_uri, header=self.headers,
                            on_message=self.on_msg,
                            on_error=self.on_error,
                            on_close=self.on_closed)
ws.on_open = self.on_open

# Iniitial CONNECT required to initialize the server's client registries.
ws.send("CONNECT\naccept-version:1.0,1.1,2.0\n\n\x00\n")

# Subscribing to all required desitnations.
for destination in self.destinations:
    sub = stomper.subscribe(destination, "clientuniqueId", ack="auto")
    ws.send(sub)
# Run until interruption to client or server terminates connection.
ws.run_forever()

cap = cv2.VideoCapture(RTSP_URL, cv2.CAP_FFMPEG)
# initialize the cv2 QRCode detector
detector = cv2.QRCodeDetector()


def reading(url):
    url_parts = str(url).split("/")
    visit_id = url_parts[-1]
    print("Visit id is " + visit_id)
    visitInfo = visit.getVisitInfo(visit_id)
    visit.checkVisit(visitInfo, visit_id)



while True:
    _, img = cap.read()
    # detect and decode
    data, bbox, _ = detector.detectAndDecode(img)
    # check if there is a QRCode in the image
    if data:
        # Reading QRCode data
        a = data
        reading(a)
    cv2.imshow("QRCODScanner", img)
    if cv2.waitKey(1) == ord("q"):
        break
    cap.release()

# b=webbrowser.open(str(a))

# cv2.destroyAllWindows()
