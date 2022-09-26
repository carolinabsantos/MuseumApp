import Artifact as ArtifactsList
import Exhibitor as Exhibitor

import DB
from Camera import VideoCamera
import pdb
print("Menu")
print("1 - all Artifacts")
print("2 - Artifacts of one Exhibitor")
print("3 - Artifacts of one TimeMachine")
print("4 - Artifacts of one Exhibitor and one Time Machine")
print("5 - Information of one Exhibitor")
while True:
    ans = input("Choose an Option Number: ")
    if ans == "1":
        ArtifactsList.allArtifacts()
    elif ans == "2":
        exhibitor = input("enter the exhibitor name: ")
        ArtifactsList.exhibitorArtifacts(exhibitor)
    elif ans == "3":
        timeMachine = input("Enter the time_machine name: ")
        print(ArtifactsList.timeMachineArtifacts(timeMachine))
    elif ans == "4":
        exhibitor = input("enter the exhibitor name: ")
        timeMachine = input("Enter the time_machine name: ")
        ArtifactsList.timeMachineExhibitorArtifacts(exhibitor, timeMachine)
    elif ans == "5":
        name = input("enter the exhibitor name: ")
        print(Exhibitor.printExhibitor(name))
    else:
        print("Please Enter a Valid Option Number!")

#
# @app.route('/')
# def index():
#     return render_template('index.html')
#
#
# def gen(camera):
#     while True:
#         frame = camera.get_frame()
#         yield (b'--frame\r\n'
#                b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')
#
#
# @app.route('/video_feed')
# def video_feed():
#     return Response(gen(VideoCamera()),
#                     mimetype='multipart/x-mixed-replace; boundary=frame')
#
#
# if __name__ == '__main__':
#     app.run(host='192.168.1.123:8000', debug=True)
