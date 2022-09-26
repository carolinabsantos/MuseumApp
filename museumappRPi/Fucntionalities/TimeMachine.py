from Fucntionalities import DB
from Fucntionalities.Artifact import Artifact
from Fucntionalities.Exhibitor import Exhibitor


class TimeMachine:
    def __init__(self, name):
        self.artifacts = None
        self.exhibitors = None

        self.id = id(self)
        self.name = name

    def define_artifacts(self, name):
        """
        define_artifacts() -> Defines the list of Artifacts from this Time Machine
        Parameters -> None
        """
        artifacts_from_timeMachine = DB.timeMachineArtifacts_from_db(name)
        for a in range(len(artifacts_from_timeMachine)):
            ar = Artifact.__init__(artifacts_from_timeMachine[a][0], artifacts_from_timeMachine[a][2])
            self.artifacts.append(ar)

    def define_exhibitors(self, name):
        """
        define_exhibitors() -> Defines the list of Exhbitors from this Time Machine
        Parameters -> None
        """
        exhibitors_from_timeMachine = DB.listOfExhibitorsNames_from_db(name)
        for e in range(len(exhibitors_from_timeMachine)):
            exhibitor = Exhibitor.Exhibitor.__init__(exhibitors_from_timeMachine[e])
            self.exhibitors.append(exhibitor)


def printTimeMachine(timeMachine_name):
    timeMachine = TimeMachine(timeMachine_name)
    timeMachine.define_artifacts(timeMachine.name)
    timeMachine.define_exhibitors(timeMachine.name)
    print("Exhibitor Name: " + timeMachine.name)
    # print(" \n Id: " + ex.id)
    listString = str(timeMachine.artifacts)
    print("Artifacts: " + listString)
    listString = str(timeMachine.exhibitors)
    print("Exhibitors: " + listString)
    print("Nº of Artifacts: " + str(timeMachine.n_artifacts))
