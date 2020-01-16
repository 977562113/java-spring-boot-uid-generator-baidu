package com.foxwho.demo.controller;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UidController {

    @Resource(name = "cachedUidGenerator")
    private UidGenerator uidGenerator;

    @GetMapping("/id")
    public String UidGenerator() {
        return String.valueOf(uidGenerator.getUID());
    }

}
