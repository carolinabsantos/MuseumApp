from flask import Flask, render_template
from Visit import getVisitInfo, showArtifacts, listShowArtifacts, ArtifactInfo, ExhibitorPhase

app = Flask(__name__, template_folder="../templates")

visit_id = 162


@app.route('/')
def read_qrcode():
    return render_template('read-qrcode.html')


@app.route('/list-artifacts')
def list_artifacts(visit_id):
    artifacts = listShowArtifacts(visit_id)[0]
    exhibitorName = listShowArtifacts(visit_id)[1]
    print(artifacts)
    return render_template('list-artifacts.html', artifacts=artifacts, exhibitor_name=exhibitorName)


@app.route('/artifact-info')
def artifact_info():
    artifact_id = 7
    artifact = ArtifactInfo(artifact_id)[0]
    exhibitorName = ArtifactInfo(artifact_id)[1]
    print(exhibitorName)
    return render_template('artifact-info.html', artifact=artifact, exhibitor_name=exhibitorName)


@app.route('/error-exhibitor-not-in-visit')
def error_exhibitor_not_in_visit():
    artifact_id = 7
    rightExhibitorName = ExhibitorPhase(visit_id)[0]
    exhibitorName = ArtifactInfo(artifact_id)[1]
    error = ExhibitorPhase(visit_id)[1]
    if error == 0:
        message = "Este expositor não faz parte da sua visita."
    elif error == 1:
        message = "Ainda não está na altura certa de visitar este expositor."
    else:
        message = "Ups! Ocorreu um erro! \n Por favor dirija-se a alguém do museu. \n Pedimos desculpa pelo incómodo "
    print(exhibitorName)
    return render_template('error-exhibitor-not-in-visit.html', right_exhibitor_name=rightExhibitorName, exhibitor_name=exhibitorName, message=message)

