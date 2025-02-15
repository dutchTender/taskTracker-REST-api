package com.zhang.details.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.zhang.details.service" })
public class UmServiceConfig {

    public UmServiceConfig() {
        super();
    }

    // beans

}
