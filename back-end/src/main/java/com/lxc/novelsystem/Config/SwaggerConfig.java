package com.lxc.novelsystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @description:
 * @author: Anthony
 * @time: 2022/1/24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置swagger信息
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "NovelSystem",
                "Anthony 的 Swagger API 文档",
                "1.0",
                "",
                new Contact("Anthony","","25887985@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());

    }

    @Bean
    public Docket docket()
    {
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //配置是否启动swagger
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lxc.novelsystem.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
