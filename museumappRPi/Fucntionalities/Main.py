import Artifact as ArtifactsList
import DB
import Exhibitor as Exhibitor
import TimeMachine as TimeMachine
import Visit as Visit

print("Menu")
print("1 - all Artifacts")
print("2 - Artifacts of one Exhibitor")
print("3 - Artifacts of one TimeMachine")
print("4 - Artifacts of one Exhibitor and one Time Machine")
print("5 - Information of one Exhibitor")
print("6 - Information of one Time Machine")
print("7 - Artifact Specific")
print("8 - Visit State")

while True:
    ans = input("Choose an Option Number: ")
    if ans == "1":
        name = input("enter the exhibitor name: ")
        ArtifactsList.allArtifacts(name)
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
    elif ans == "6":
        name = input("enter the time machine name: ")
        print(TimeMachine.printTimeMachine(name))
    elif ans == "7":
        artifact_id = input("enter the artifact id: ")
        print(DB.artifact_from_db(artifact_id))
    elif ans == "8":
        visit_id = input("enter the visit id: ")
        Visit.getVisitInfo(visit_id)
    else:
        print("Please Enter a Valid Option Number!")


#
# if __name__ == '__main__':
#     app.run(host='192.168.1.123:8000', debug=True)
