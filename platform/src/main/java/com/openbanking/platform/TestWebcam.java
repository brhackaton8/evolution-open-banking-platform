package com.openbanking.platform;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;

public class TestWebcam {
    public static void main(String[] args) {
        Webcam webcam =  Webcam.getDefault();
        WebcamPanel panel = new WebcamPanel(webcam);

        JFrame windows = new JFrame("Smth");
        windows.add(panel);
        windows.setVisible(true);

    }
}
