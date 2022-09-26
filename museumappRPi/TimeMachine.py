import Artifact as artifact
import DB
import Exhibitor as exhibitor


class TimeMachine:
    def __init__(self, id, name):
        self.id = id
        self.name = name
        self.define_artifacts(self)
        self.define_exhibitors(self)

    def define_artifacts(self):
        """
        define_exhibitors() -> Defines the list of Artifacts from this Time Machine
        Parameters -> None
        """
        artifacts_from_timeMachine = DB.timeMachineArtifacts_from_db(self.name)
        for a in range(len(artifacts_from_timeMachine)):
            ar = artifact.Artifact.__init__(artifacts_from_timeMachine[a][0], artifacts_from_timeMachine[a][2])
            self.artifacts.append(ar)

    def define_exhibitors(self):
        """
        define_exhibitors() -> Defines the list of names of Exhbitors from this Time Machine
        Parameters -> None
        """
        exhibitors_from_timeMachine = DB.listOfExhibitorsNames_from_db(self.name)
        for e in range(len(exhibitors_from_timeMachine)):
            ex = exhibitor.Exhibitor.__init__(exhibitors_from_timeMachine[e])
            self.exhibitors.append(ex)
