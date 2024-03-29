import time
import DB


class Artifact:

    def __init__(self, id, exhibitor, time_machine):
        self.id = id
        self.exhibitor = exhibitor
        self.time_machine = time_machine


def exhibitorArtifacts(exhibitor_name):
    """
    exhibitorArtifacts() -> Shows the List of Artifacts according to the exhibitor chosen
    Parameters -> exhibitor_name(STRING)
    """
    result = DB.allExhibitorArtifacts_from_db(exhibitor_name)
    return result


def timeMachineArtifacts(timeMachine_name):
    """
    timeMachineArtifacts() -> Shows the List of Artifacts according to the exhibitor and the time machine chosen
    Parameters -> exhibitor_name(STRING), timeMachine_name(STRING)
    """
    result = DB.timeMachineArtifacts_from_db(timeMachine_name)
    return result


def timeMachineExhibitorArtifacts(exhibitor_name, timeMachine_name):
    """
    timeMachineArtifacts() -> Shows the List of Artifacts according to the exhibitor and the time machine chosen
    Parameters -> exhibitor_name(STRING), timeMachine_name(STRING)
    """
    result = DB.timeMachineExhibitorArtifacts_from_db(exhibitor_name, timeMachine_name)
    return result


def allArtifacts(exhibitor_name):
    """
    allArtifacts() -> Shows a List of All Artifacts
    Parameters -> None
    """
    result = DB.allExhibitorArtifacts_from_db(exhibitor_name)
    return result


def artifactInfo(artifact_id):
    """
    artifactInfo() -> Shows the information about one specific Artifact
    Parameters -> None
    """
    result = DB.artifact_from_db(artifact_id)
    return result
