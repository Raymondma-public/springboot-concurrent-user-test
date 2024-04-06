/*
 * SpringMockMvcWebControllerTest.java
 *
 * Created on 2018-05-16, 6:42
 */
package com.marcnuri.demo.springmockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-05-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {
//        SpringMockMvcWebController.class
//})
@SpringBootTest(classes = SpringMockMvcApplication.class)
public class SpringMockMvcWebControllerTest {

    //**************************************************************************************************
//  Fields
//**************************************************************************************************
    @Autowired
    SpringMockMvcWebController springMockMvcWebController;

    //        @Autowired
    MockMvc mockMvc;


    //**************************************************************************************************
//  Test preparation
//**************************************************************************************************
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(springMockMvcWebController)
                .build();
    }

//    @After
//    public void tearDown() {
//        mockMvc = null;
//    }

    //**************************************************************************************************
//  Tests
//**************************************************************************************************

    @Test
    public void testConcurrency() throws InterruptedException {

        ArrayList<Thread> threads = new ArrayList<>();
        int count = 1000;
        for (int i = 0; i < count; i++) {
            int finalI = i;
            threads.add(new Thread() {
                @Override
                public void run() {
                    try {
                        mockMvc.perform(post("/concurrency").header("testHeader", finalI).content("{\"id\":1}").contentType(MediaType.APPLICATION_JSON_VALUE));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < count; i++) {
            int finalI = i;
            threads.add(new Thread() {
                @Override
                public void run() {
                    try {
                        mockMvc.perform(post("/concurrency").header("testHeader", finalI).content("{\"id\":2}").contentType(MediaType.APPLICATION_JSON_VALUE));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        Map<User, Long> counterMap =  ConcurrencyService.mockDatabase.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<User, Long> entry : counterMap.entrySet()) {
            System.out.println("User " + entry.getKey().id + ": " + entry.getValue());
        }
//      System.out.println("count 1:"+springMockMvcWebController.concurrentHashMap.get(new User(1)));
//      System.out.println("count 2:"+springMockMvcWebController.concurrentHashMap.get(new User(2)));

//      assert springMockMvcWebController.sharedValue == 200;

    }

}

