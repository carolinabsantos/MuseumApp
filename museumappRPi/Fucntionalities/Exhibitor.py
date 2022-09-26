import Fucntionalities.Artifact as Artifact


class Exhibitor:
    def __init__(self, name):
        self.n_artifacts = None
        self.artifacts = None

        self.name = name

    def __new__(cls, *args, **kwargs):
        return super().__new__(cls)

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

    def get_name(self):
        return self.name

    def get_artifacts(self):
        return self.artifacts

    def get_n_artifacts(self):
        return self.n_artifacts


def printExhibitor(ex_name):
    ex = Exhibitor(ex_name)
    ex.define_artifacts(ex.name)
    ex.define_n_artifacts(ex.artifacts)
    print("Exhibitor Name: " + ex.name)
    listString = str(ex.artifacts)
    print("Artifacts: " + listString)
    print("Nº of Artifacts: " + str(ex.n_artifacts))
