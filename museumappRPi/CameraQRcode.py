import csv

from imutils.video import VideoStream
from pyzbar import pyzbar
import argparse
import imutils
import time
import cv2

ap = argparse.ArgumentParser()
ap.add_argument("-o", "--output", type=str, default="barcodes.csv",
            help="path to output CSV file containing barcodes")
args = vars(ap.parse_args())

#vs = VideoStream(src=0).start()
vs = VideoStream(usePiCamera=True).start()
time.sleep(2.0)

frame = vs.read()
frame = imutils.resize(frame, width=400)
barcodes = pyzbar.decode(frame)

for barcode in barcodes:
    (x, y, w, h) = barcode.rect
    cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)

    barcodeData = barcode.data.decode("utf-8")
barcodeType = barcode.type

text = "{} ({})".format(barcodeData, barcodeType)
cv2.putText(frame, text, (x, y - 10),
     cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255), 2)

key = cv2.waitKey(1) & 0xFF
# if the `s` key is pressed, break from the loop
# if key == ord("s"):
#     break
print("[INFO] cleaning up...")
csv.close()
cv2.destroyAllWindows()
vs.stop()