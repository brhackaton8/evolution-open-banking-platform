# import main Flask class and request object
from flask import Flask, request, render_template
from videorecorderv2 import start_and_record

# create the Flask app
app = Flask(__name__)

@app.route('/start-recording')
def query_example():
    start_and_record()
    return 'Recording is started'

@app.route('/page')
def form_example():
    pagetitle = "HomePage"

    return render_template("/index.html",
                            mytitle=pagetitle)


if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run(debug=True, port=5000)