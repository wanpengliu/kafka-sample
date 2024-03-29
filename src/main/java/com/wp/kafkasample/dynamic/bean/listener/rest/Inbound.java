package com.wp.kafkasample.dynamic.bean.listener.rest;

import com.wp.kafkasample.dynamic.bean.listener.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Inbound {

    private PackageService packageService;

    @GetMapping("/send")
    public void sendMessage(@RequestParam(name = "msg") String message) {

        packageService.sendPackage(message);
    }
}
