from flask import Flask, render_template, Response
import cv2

app = Flask(__name__)
camera = cv2.VideoCapture(0)

# def saveVideo():
#     if (camera.isOpened() == False):
#         print("Error reading video file")
#
#     # We need to set resolutions.
#     # so, convert them from float to integer.
#     frame_width = int(camera.get(3))
#     frame_height = int(camera.get(4))
#
#     size = (frame_width, frame_height)
#
#     # Below VideoWriter object will create
#     # a frame of above defined The output
#     # is stored in 'filename.avi' file.
#     result = cv2.VideoWriter(
#         '/Users/shamistanhuseynov/Desktop/evolution-open-banking-platform/platform/src/main/resources/videos/filename.avi',
#         cv2.VideoWriter_fourcc(*'MJPG'),
#         10, size)
#
#     while (True):
#         ret, frame = camera.read()
#
#         if ret == True:
#
#             # Write the frame into the
#             # file 'filename.avi'
#             result.write(frame)
#
#             # Display the frame
#             # saved in the file
#             cv2.imshow('Frame', frame)
#
#             # Press S on keyboard
#             # to stop the process
#             if cv2.waitKey(1) & 0xFF == ord('s'):
#                 break
#
#         # Break the loop
#         else:
#             break
#
#     # When everything done, release
#     # the video capture and video
#     # write objects
#     video.release()
#     result.release()
#
#     # Closes all the frames
#     cv2.destroyAllWindows()
#
#     print("The video was successfully saved")


def generate_frames():
    while True:
        ## read the camera frame
        success, frame = camera.read()

        if not success:
            break
        else:
            ret, buffer = cv2.imencode('.jpg', frame)
            frame = buffer.tobytes()

        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/video')
def video():
    # saveVideo()
    return Response(generate_frames(), mimetype='multipart/x-mixed-replace; boundary=frame')


if __name__ == "__main__":
    app.run(debug=True)