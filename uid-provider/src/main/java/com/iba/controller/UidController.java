package com.iba.controller;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UidController {

    @Resource(name = "cachedUidGenerator")
//    @Resource(name = "defaultUidGenerator")
    private UidGenerator uidGenerator;

    @GetMapping("/id")
    public Long UidGenerator() {
        return uidGenerator.getUID();
    }

}
