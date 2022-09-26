import cv2

# set up camera object called Cap which we will use to find OpenCV
cap = cv2.VideoCapture(0)

# QR code detection Method
detector = cv2.QRCodeDetector()

WHITE = [255, 255, 255]
face_cascade = cv2.CascadeClassifier('Haar/haarcascade_frontalcatface.xml')
eye_cascade = cv2.CascadeClassifier('Haar/haarcascade_eye.xml')


def draw_box(Image, x, y, w, h):
    cv2.line(Image, (x, y), (x + int(w / 5), y), WHITE, 2)
    cv2.line(Image, (x + int((w / 5) * 4), y), (x + w, y), WHITE, 2)
    cv2.line(Image, (x, y), (x, y + int(h / 5)), WHITE, 2)
    cv2.line(Image, (x + w, y), (x + w, y + int(h / 5)), WHITE, 2)
    cv2.line(Image, (x, (y + int(h / 5 * 4))), (x, y + h), WHITE, 2)
    cv2.line(Image, (x, (y + h)), (x + int(w / 5), y + h), WHITE, 2)
    cv2.line(Image, (x + int((w / 5) * 4), y + h), (x + w, y + h), WHITE, 2)
    cv2.line(Image, (x + w, (y + int(h / 5 * 4))), (x + w, y + h), WHITE, 2)


class VideoCamera(object):
    def __init__(self):
        self.video = cv2.VideoCapture(0)

    def __del__(self):
        self.video.release()

    def get_frame(self):

        _, img = cap.read()

        # Below is the method to read the QR code by detetecting the bounding box coords and decoding the hidden QR data
        data, bbox, _ = detector.detectAndDecode(img)

        # This is how we get that Blue Box around our Data. This will draw one, and then Write the Data along with the top (Alter the numbers here to change the colour and thickness of the text)
        if bbox is not None:
            for i in range(len(bbox)):
                cv2.line(img, tuple(bbox[i][0]), tuple(bbox[(i, 1) % len(bbox)][0]), color=(255,
                                                                                            0, 0), thickness=2)
            cv2.putText(img, data, (int(bbox[0][0][0]), int(bbox[0][0][1]) - 10), cv2.FONT_HERSHEY_SIMPLEX,
                        1, (255, 250, 120), 2)

            # Below prints the found data to the below terminal (This we can easily expand on to capture the data to an Excel Sheet)
            # You can also add content to before the pass. Say the system reads red it'll activate a Red LED and the same for Green.
            if data:
                print("data found: ", data)
                if data == 'red':
                    pass
                if data == 'green':
                    pass

        # Below will display the live camera feed to the Desktop on Raspberry Pi OS preview
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        ret, jpeg = cv2.imencode('.jpg', gray)
        return jpeg.tobytes()

    # def get_frame(self):
    #     success, image = self.video.read()
    #     # We are using Motion JPEG, but OpenCV defaults to capture raw images,
    #     # so we must encode it into JPEG in order to correctly display the
    #     # video stream.
    #
    #     gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    #     faces = face_cascade.detectMultiScale(cv2.COLOR_BGR2GRAY, 1.3, 5)
    #     for (x, y, w, h) in faces:
    #         gray_face = cv2.resize((gray[y:y + h, x:x + w]), (110, 110))
    #         eyes = eye_cascade.detectMultiScale(gray_face)
    #         for (ex, ey, ew, eh) in eyes:
    #             draw_box(gray, x, y, w, h)
    #
    #     ret, jpeg = cv2.imencode('.jpg', cv2.COLOR_BGR2GRAY)
    #
    #     return jpeg.tobytes()