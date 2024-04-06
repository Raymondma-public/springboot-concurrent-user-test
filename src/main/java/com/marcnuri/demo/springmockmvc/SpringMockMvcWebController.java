/*
 * SpringMockMvcWebController.java
 *
 * Created on 2018-05-14, 7:25
 */
package com.marcnuri.demo.springmockmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-05-14.
 */
@Controller
@RequestMapping(value = "/")
public class SpringMockMvcWebController extends BaseController {

    @Autowired
    ConcurrencyService concurrencyService;

    @PostMapping(value = "/concurrency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String testConcurrency(@RequestBody User _) {
//        System.out.println("user: " + user);

//        User sharedValueInt = user;
//      this.sharedValue = sharedValueInt;
//        this.setValue(sharedValueInt);
        concurrencyService.test();
        return "test result";
    }
}
