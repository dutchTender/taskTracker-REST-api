package com.zhang.app.spring.controller.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ // @formatter:off
    TaskIntegrationTest.class,
    UserIntegrationTest.class,
    UserTasksIntegrationTest.class,
    ServiceSpringIntegrationTest.class,
    WebSpringIntegrationTest.class
}) // @formatter:on
public class IntegrationTestSuite {
    //
}
