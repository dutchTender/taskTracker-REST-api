package com.zhang.app.spring;
import com.zhang.details.persistence.setup.MyApplicationContextInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
@SpringBootApplication

@ComponentScan({ "com.zhang" })
@Import({ // @formatter:off
    UmContextConfig.class,
    UmPersistenceJpaConfig.class,
    UmServiceConfig.class,
    UmWebConfig.class,
    UmAsyncConfig.class
}) // @formatter:on
public class UmApp {
    public static void main(final String... args) {
        new SpringApplicationBuilder(UmApp.class).initializers(new MyApplicationContextInitializer()).listeners().run(args);
    }

}
