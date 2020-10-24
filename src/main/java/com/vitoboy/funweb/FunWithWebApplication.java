package com.vitoboy.funweb;

import com.vitoboy.funweb.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AppConfig.class})
@SpringBootApplication(scanBasePackages = {"com.vitoboy.funweb.*"})
public class FunWithWebApplication {

    private static Logger logger = LoggerFactory.getLogger(FunWithWebApplication.class);

    public static void main(String[] args) {
        logger.info("FunWithWebApplication run 开始");
        SpringApplication application = new SpringApplication(FunWithWebApplication.class);
        application.setBannerMode(Banner.Mode.LOG);
        application.run(args);
        logger.info("FunWithWebApplication run 结束");
        logger.info("app config username is : [{}]", AppConfig.username);
        logger.info("app config password is : [{}]", AppConfig.password);
        logger.info("app config email is : [{}]", AppConfig.email);
    }

}
