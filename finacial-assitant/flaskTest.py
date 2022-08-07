# import main Flask class and request object
from flask import Flask, request, render_template
import cv2
from videorecorderv2 import start_and_record

# create the Flask app
app = Flask(__name__)


@app.route('/start-recording')
def query_example():
    video = cv2.VideoCapture(0)

    # We need to check if camera
    # is opened previously or not
    if (video.isOpened() == False):
        print("Error reading video file")

    # We need to set resolutions.
    # so, convert them from float to integer.
    frame_width = int(video.get(3))
    frame_height = int(video.get(4))

    size = (frame_width, frame_height)

    # Below VideoWriter object will create
    # a frame of above defined The output
    # is stored in 'filename.avi' file.
    result = cv2.VideoWriter(
        '/Users/shamistanhuseynov/Desktop/evolution-open-banking-platform/platform/src/main/resources/videos/filename.avi',
        cv2.VideoWriter_fourcc(*'MJPG'),
        10, size)

    while (True):
        ret, frame = video.read()

        if ret == True:

            # Write the frame into the
            # file 'filename.avi'
            result.write(frame)

            # Display the frame
            # saved in the file
            cv2.imshow('Frame', frame)

            # Press S on keyboard
            # to stop the process
            if cv2.waitKey(1) & 0xFF == ord('s'):
                break

        # Break the loop
        else:
            break

    # When everything done, release
    # the video capture and video
    # write objects
    video.release()
    result.release()

    # Closes all the frames
    cv2.destroyAllWindows()

    print("The video was successfully saved")

    return 'Recording is started'


@app.route('/page')
def form_example():
    pagetitle = "HomePage"

    return render_template("/index.html",
                           mytitle=pagetitle)


if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run(debug=True, port=5000)
