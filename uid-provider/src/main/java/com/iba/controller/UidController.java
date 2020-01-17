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

    @GetMapping("/test")
    public void test() {
        //570万每秒
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000 * 1000; i++) {
            uidGenerator.getUID();
        }
        System.out.println("time filter 耗时:"+ (System.currentTimeMillis() - start));
    }

}
