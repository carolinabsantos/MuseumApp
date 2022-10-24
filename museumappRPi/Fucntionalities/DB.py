import json
import logger
import requests

DB_URL = 'http://192.168.1.121:8081/'


def getVisitInfo_from_db(visit_id):
    response = requests.get(DB_URL + 'visitState?visit_id=' + str(visit_id))
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None
    return json_data


def allExhibitorArtifacts_from_db(exhibitor_name):
    response = requests.get(DB_URL + 'getAllArtifacts?exhibitor_name=' + exhibitor_name)
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None
    return json_data


def artifact_from_db(artifact_id):
    response = requests.get(DB_URL + 'getArtifact?artifact_id=' + str(artifact_id))
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None
    return json_data


def timeMachineExhibitorArtifacts_from_db(exhibitor_name, timeMachine_name):
    request = DB_URL + 'timeMachineExhibitorArtifacts?exhibitor_name=' + str(
        exhibitor_name) + '&timeMachine_name=' + str(timeMachine_name)
    # print(request)
    response = requests.get(request)
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None

    # print(json_data)
    return json_data
