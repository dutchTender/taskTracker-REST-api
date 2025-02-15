package com.zhang.um.spring.controller.web;

import com.zhang.details.spring.UmContextConfig;
import com.zhang.details.spring.UmPersistenceJpaConfig;
import com.zhang.details.spring.UmServiceConfig;
import com.zhang.details.spring.UmWebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UmPersistenceJpaConfig.class, UmContextConfig.class, UmServiceConfig.class, UmWebConfig.class })
@WebAppConfiguration
public class WebSpringIntegrationTest {

    @Test
    public final void whenContextIsBootstrapped_thenOk() {
        //
    }

}
