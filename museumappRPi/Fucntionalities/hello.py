from flask import Flask, render_template
from Visit import getVisitInfo, showArtifacts

app = Flask(__name__, template_folder="../templates")

visit_id = 162

@app.route('/')
def read_qrcode():
    return render_template('read-qrcode.html')

@app.route('/list-artifacts')
def list_artifacts():
    visitInstance = getVisitInfo(visit_id)
    arts = showArtifacts(visitInstance['timeMachine'])
    print(arts)
    return render_template('list-artifacts.html', artifacts=arts)

