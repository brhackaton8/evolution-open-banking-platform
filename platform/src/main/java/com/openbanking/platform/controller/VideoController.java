package com.openbanking.platform.controller;

import com.openbanking.platform.client.VideoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VideoController {

    private final VideoClient videoClient;

    @GetMapping("/identity")
    public void identity(){
        log.info("Video client is called");
        videoClient.startVideoRecording();
    }



}
