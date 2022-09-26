import requests
import logger
import numpy as np

from Fucntionalities import Artifact as artifacts
from Fucntionalities import Exhibitor as Exhibitor
from Fucntionalities import DB

###### Institiation of the exhibitor #####

exhibitor = Exhibitor.Exhibitor.__new__(Exhibitor.Exhibitor)
exhibitor.__init__("ddd")


def getVisitInfo(visit_id):
    """
        getVisitInfo() -> Shows all the information about a specific visit
        Parameters -> visit_id(int)
        """
    result = DB.getVisitInfo_from_db(visit_id)
    print(result)
    checkVisit(result, visit_id)
    return result


def startVisit(visit_id):
    response = requests.get('http://localhost:8081/visit-info?visit_id=' + str(visit_id))
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            visit = response.json()
            print(visit)
        except:
            return None


def checkExhibitorInVisit(visitInfo):
    exhibitors_names = visitInfo.get('exhibitors_names')
    names_list = exhibitors_names.split("-")
    if exhibitor.get_name() in names_list:
        return True


def checkExhibitorPhase(visitInfo):
    exhibitor_counter = int(visitInfo.get('exhibitors_counter'))
    exhibitors_names = visitInfo.get('exhibitors_names')
    names_list = exhibitors_names.split("-")
    if names_list[exhibitor_counter] == exhibitor.get_name():
        return True


def showArtifacts():
    artifactDict = artifacts.exhibitorArtifacts(exhibitor.get_name())
    print(artifactDict)


def checkVisit(visit, visit_id):
    if ('state', 'TO_START') in visit.items():
        print("visit ready to start")
        startVisit(visit_id)
    elif ('state', 'ONGOING') in visit.items():
        # condicao que ve se aquele expositor pertence aquela visita
        if checkExhibitorInVisit(visit):
            # condicao que ve se aquele e o expositor certo para aquele momento da visita
            print("checkExhibitorInVisit is True")
            if checkExhibitorPhase(visit):
                # passar info do artefacto e marcar como visitado o expositor
                # getAllArtifactsIds from exhibitor
                showArtifacts()
                print("checkExhibitorPhase is True")
            else:
                # passar pagina de "não está na altura certa da visita"
                print("checkExhibitorPhase is False")
        else:
            print("checkExhibitorInVisit is False")
    # passar página de "este expositor não faz parte da sua visita"
