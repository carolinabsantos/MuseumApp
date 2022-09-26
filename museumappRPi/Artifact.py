import datetime
import time

import mysql.connector

import DB

DATABASE = 'museumapp'
USER = 'root'
PASS = 'root'
AUTH = 'mysql_native_password'


class Artifact:
    def __init__(self, id, exhibitor):
        self.id = id
        self.exhibitor = exhibitor


def exhibitorArtifacts(exhibitor_name):
    """
    exhibitorArtifacts() -> Shows the List of Artifacts according to the exhibitor chosen
    Parameters -> exhibitor_name(STRING)
    """
    result = DB.exhibitorArtifacts_from_db(exhibitor_name)
    # print_results(result)
    DB.closes_db()
    return result


def timeMachineArtifacts(timeMachine_name):
    """
    timeMachineArtifacts() -> Shows the List of Artifacts according to the exhibitor and the time machine chosen
    Parameters -> exhibitor_name(STRING), timeMachine_name(STRING)
    """
    result = DB.timeMachineArtifacts_from_db(timeMachine_name)
    print_results(result)
    DB.closes_db()
    return result


def timeMachineExhibitorArtifacts(exhibitor_name, timeMachine_name):
    """
    timeMachineArtifacts() -> Shows the List of Artifacts according to the exhibitor and the time machine chosen
    Parameters -> exhibitor_name(STRING), timeMachine_name(STRING)
    """
    result = DB.timeMachineExhibitorArtifacts_from_db(exhibitor_name, timeMachine_name)
    print_results(result)
    DB.closes_db()
    return result


def print_results(result):
    time.sleep(1)

    if len(result) == 0:
        print("No Artifacts Available!")
    else:
        print(DB.head)
        print(" ")
        for x in result:
            print(x, "\n")


def allArtifacts():
    """
    allArtifacts() -> Shows a List of All Artifacts
    Parameters -> None
    """
    result = DB.allArtifacts_from_db()
    print_results(result)
    DB.closes_db()
    return result
