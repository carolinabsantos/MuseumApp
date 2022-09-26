import mysql.connector

DATABASE = 'museumapp'
USER = 'root'
PASS = 'root'
AUTH = 'mysql_native_password'

mn = mysql.connector.connect(host='localhost', database=DATABASE, user=USER, password=PASS, auth_plugin=AUTH)
cur = mn.cursor()


head = ["id", "name", "exhibitor", "time_machine"]

def allArtifacts_from_db():
    #print("Search all artifacts")

    cur.execute(
        'SELECT id, name, exhibitor, time_machine from artifact_entity;')
    result = cur.fetchall()
    return result


def exhibitorArtifacts_from_db(exhibitor_name):
    #print("Search artifacts by exhibitor: " + exhibitor_name)

    cur.execute(
        'SELECT id, name, exhibitor, time_machine from artifact_entity where exhibitor="{}";'.format(
            exhibitor_name))
    result = cur.fetchall()
    return result


def timeMachineArtifacts_from_db(timeMachine_name):
    #print("Search artifacts by time machine" + timeMachine_name)

    cur.execute(
        'SELECT id, name, exhibitor, time_machine from artifact_entity where time_machine="{}";'.format(
            timeMachine_name))
    result = cur.fetchall()
    return result


def timeMachineExhibitorArtifacts_from_db(exhibitor_name, timeMachine_name):
    #print("Search artifacts by exhibitor " + exhibitor_name + "and time machine" + timeMachine_name)

    cur.execute(
        'SELECT id, name, exhibitor, time_machine from artifact_entity where exhibitor="{}" and time_machine="{}";'.format(
            exhibitor_name, timeMachine_name))
    result = cur.fetchall()
    return result


def listOfExhibitorsNames_from_db(timeMachine_name):
    exhibitors = []
    artifacts_of_timeMachine = timeMachineArtifacts_from_db(timeMachine_name)
    for i in range(len(artifacts_of_timeMachine)):
        exhibitor = artifacts_of_timeMachine[i][2]
        if exhibitor not in exhibitors:  # se não estiver já lá
            exhibitors.append(exhibitor)  # adiciona o expositor
    return exhibitors


def closes_db():
    cur.close()
    mn.close()
