from Fucntionalities import Artifact


class Exhibitor:
    def __init__(self, name):
        self.n_artifacts = None
        self.artifacts = None

        self.id = id(self)
        self.name = name

    def define_artifacts(self, name):
        """
        define_artifacts() -> Defines the list of Artifacts from this Exhibitor
        Parameters -> None
        """
        self.artifacts = Artifact.exhibitorArtifacts(name)

    def define_n_artifacts(self, artifacts):
        """
        define_artifacts() -> Defines the list of Artifacts from this Exhibitor
        Parameters -> None
        """
        self.n_artifacts = len(artifacts)


def printExhibitor(ex_name):
    ex = Exhibitor(ex_name)
    ex.define_artifacts(ex.name)
    ex.define_n_artifacts(ex.artifacts)
    print("Exhibitor Name: " + ex.name)
    #print(" \n Id: " + ex.id)
    listString = str(ex.artifacts)
    print("Artifacts: " + listString)
    print("Nº of Artifacts: " + str(ex.n_artifacts))