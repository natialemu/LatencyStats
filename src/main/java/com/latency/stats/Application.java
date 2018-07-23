package com.latency.stats;

import com.latency.stats.config.EmbeddedTomcatConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import(EmbeddedTomcatConfiguration.class)
public class Application {

    protected Application() {
        // do nothing
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}