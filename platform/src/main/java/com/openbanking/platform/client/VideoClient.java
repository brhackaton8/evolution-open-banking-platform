package com.openbanking.platform.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(name = "VideoClient", url = "${video.client.link}")
public interface VideoClient {
    String WebhookCallbackService = "webhookCallbackService";

    @GetMapping(value = "/video")
    void startVideoRecording();

}