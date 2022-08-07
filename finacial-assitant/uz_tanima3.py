import flask
from flask import Flask, render_template, Response, redirect, url_for
import cv2

from datetime import datetime

# datetime object containing current date and time


app = Flask(__name__)
camera = cv2.VideoCapture(0)
frame_width = int(camera.get(3))
frame_height = int(camera.get(4))
size = (frame_width, frame_height)
result = cv2.VideoWriter(
        '/Users/shamistanhuseynov/Desktop/evolution-open-banking-platform/platform/src/main/resources/videos/' + str(datetime.now()) + '.avi',
        cv2.VideoWriter_fourcc(*'MJPG'),
        10, size)

isExceeded = False
def generate_frames():
    cnt = 0
    while True:
        cnt = cnt + 1
        ## read the camera frame
        success, frame = camera.read()
        result.write(frame)
        if not success:
            break
        else:
            ret, buffer = cv2.imencode('.jpg', frame)
            frame = buffer.tobytes()


        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
        print(cnt)
        if(cnt > 200):
            print(cnt)
            isExceeded = True
            break


@app.route('/index')
def index():
    return render_template('index.html')


@app.route('/video')
def video():
    return Response(generate_frames(), mimetype='multipart/x-mixed-replace; boundary=frame')


if __name__ == "__main__":
    app.run(debug=True)