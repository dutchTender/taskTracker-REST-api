package com.zhang.app.spring.controller.web;


import com.zhang.app.spring.UmContextConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {UmContextConfig.class} )
public class e2eTaskCreateTest {
    final String url = "http://localhost:8080/api/tasks";
}
