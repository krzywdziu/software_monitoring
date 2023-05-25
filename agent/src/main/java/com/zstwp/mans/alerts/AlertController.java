package com.zstwp.mans.alerts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AlertController {

    private final AlertService alertService;

//    init
    @PostMapping("/start")
    public void main(String[] args) {
        alertService.startScanning();
    }
}
