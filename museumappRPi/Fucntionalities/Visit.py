import requests
import logger
import numpy as np

import Artifact as artifacts
import Exhibitor as Exhibitor
import DB
import __init__

import hello

###### Institiation of the exhibitor #####

exhibitor = Exhibitor.Exhibitor.__new__(Exhibitor.Exhibitor)
exhibitor.__init__("I Eletronica")

DB_URL = 'http://192.168.1.121:8081/'

def getExhibitorName():
    name = exhibitor.get_name()
    return name

def getVisitInfo(visit_id):
    """
        getVisitInfo() -> Shows all the information about a specific visit
        Parameters -> visit_id(int)
        """
    print("Visit id: " + str(visit_id))
    result = DB.getVisitInfo_from_db(visit_id)
    return result


def startVisit(visit_id):
    response = requests.get(DB_URL + 'started-visit?visit_id=' + str(visit_id))
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            visit = response.json()
            # print(visit)
        except:
            return None


def endExhibitor(visit_id):
    response = requests.get(DB_URL + 'endExhibitor?visit_id=' + str(visit_id))
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            visit = response.json()
            print(visit)
        except:
            return None


def checkExhibitorInVisit(visitInfo):
    # print(visitInfo)
    exhibitors_names = visitInfo.get('exhibitors_names')
    names_list = exhibitors_names.split("-")
    if exhibitor.get_name() in names_list:
        return True
    else:
        return False


def checkExhibitorPhase(visitInfo):
    exhibitor_counter = int(visitInfo.get('exhibitors_counter'))
    exhibitors_names = visitInfo.get('exhibitors_names')
    if "-" not in exhibitors_names:
        names_list = [exhibitors_names]
    else:
        names_list = exhibitors_names.split("-")
    print("Name in list: " + str(names_list))
    print("names_list[exhibitor_counter]: " + str(names_list[exhibitor_counter]))
    print("exhibitor.get_name(): " + str(exhibitor.get_name()))
    print(names_list[exhibitor_counter].__eq__(exhibitor.get_name()))
    if names_list[exhibitor_counter] == exhibitor.get_name():
        return True
    else:
        return False


def showArtifacts(timeMachine_name):
    artifactDict = artifacts.timeMachineExhibitorArtifacts(exhibitor.get_name(), timeMachine_name)
    # print(artifactDict)
    return artifactDict


def ArtifactInfo(artifact_id):
    artifactDict = artifacts.artifactInfo(artifact_id)
    # print(artifactDict)
    values = list(artifactDict.items())
    artifactList = []
    # print("\n Values: " + str(values) + "\n")
    for i in range(len(artifactDict.items())):
        artifactList.append(values[i][1])
    # print("\n artifactList: " + str(artifactList))
    return artifactDict, exhibitor.get_name()


def ExhibitorPhase(visit_id):
    names_list = []
    visitInstance = getVisitInfo(visit_id)
    names = (visitInstance["exhibitors_names"])
    counter = int(visitInstance["exhibitors_counter"])
    error = 0
    state = checkVisit(visitInstance)
    if state == "start":
        error = 2
    elif not checkExhibitorInVisit(visitInstance):
        error = 0
    elif not checkExhibitorPhase(visitInstance):
        error = 1
    if "-" not in names:
        print("Solo uno exhibitor: " + names)
        return names, error
    else:
        names_list = names.split("-")
    print("Name in list: " + str(names_list))
    return names_list[counter], error


def listShowArtifacts(visit_id):
    visitId = int(visit_id)
    visitInstance = getVisitInfo(visitId)
    print(visitInstance["timeMachine"])
    arts = showArtifacts(visitInstance["timeMachine"])
    print("Artifacts:" + str(arts))
    artifactList = []
    values = list(arts.items())
    # print("\n Values: " + str(values) + "\n")
    for i in range(len(arts.items())):
        artifactList.append(list(values[i][1].values()))
    return artifactList, exhibitor.get_name()


def checkVisit(visit):
    state = ""
    if ('state', 'TO_START') in visit.items():
        print("visit ready to start")
        state = "start"
    elif ('state', 'ONGOING') in visit.items():
        # condicao que ve se aquele expositor pertence aquela visita
        if checkExhibitorInVisit(visit):
            # condicao que ve se aquele e o expositor certo para aquele momento da visita
            print("checkExhibitorInVisit is True")
            if checkExhibitorPhase(visit):
                # passar info do artefacto e marcar como visitado o expositor
                # getAllArtifactsIds from exhibitor
                # artifactList, exhibitor_name = listShowArtifacts(visit)
                state = "exhibitor"
                print("checkExhibitorPhase is True")
            else:
                state = "not-in-phase"
                # passar pagina de "não está na altura certa da visita"
                print("checkExhibitorPhase is False")
        else:
            state = "not-in-visit"
            print("checkExhibitorInVisit is False")
    return state
    # passar página de "este expositor não faz parte da sua visita"
