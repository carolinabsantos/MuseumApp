import json
import logger
import requests


def getVisitInfo_from_db(visit_id):
    response = requests.get('http://localhost:8081/visitState?visit_id=' + str(visit_id))
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None
    return json_data


def allExhibitorArtifacts_from_db(exhibitor_name):
    response = requests.get('http://localhost:8081/getAllArtifacts?exhibitor_name=' + exhibitor_name)
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None
    return json_data


def artifact_from_db(artifact_id):
    request = 'http://localhost:8081/getArtifact?artifact_id=' + str(artifact_id)
    response = requests.get(request)
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            artifact = response.json()
            dict = json.load(artifact)
            print(dict)
        except:
            return None


def timeMachineExhibitorArtifacts_from_db(exhibitor_name, timeMachine_name):
    request = 'http://localhost:8081/timeMachineExhibitorArtifacts?exhibitor_name=' + str(
        exhibitor_name) + '&timeMachine_name=' + str(timeMachine_name)
    print(request)
    response = requests.get(request)
    if response.status_code == 400:
        logger.error(response.request.url)
    elif response.status_code == 200:
        try:
            json_data = json.loads(response.text)
        except:
            return None

    print(json_data)
    return json_data