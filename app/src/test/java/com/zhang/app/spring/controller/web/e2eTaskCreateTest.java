package com.zhang.app.spring.controller.web;


import com.zhang.app.spring.UmContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {UmContextConfig.class} , loader = AnnotationConfigContextLoader.class )
public class e2eTaskCreateTest {

    private static final String JSON = MediaType.APPLICATION_JSON.toString();

    @Test
    public void retrieveAllTasks200ok() {
        final String url = "http://localhost:8080/api/tasks";


    }


}
