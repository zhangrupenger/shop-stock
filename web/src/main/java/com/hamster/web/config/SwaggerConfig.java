package com.hamster.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger 配置文件
 *
 * Configuration  申明此为配置类
 * EnableSwagger2  申明开启
 */
@Configuration
@EnableSwagger2
public  class SwaggerConfig {
    @Bean
    public Docket webApiConfig(Environment environment){
/*        //根据环境判断是否开启swagger
        Profiles profiles=Profiles.of("dev","test"); //dev test环境下开启
        boolean flag = environment.acceptsProfiles(profiles);*/

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
//                .enable(flag)   // 真实运行环境时要配置
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hamster.web.controller"))  // 扫描包的路径
                .paths(PathSelectors.any())  //权限
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("仓鼠库存api")
                .description("仓鼠库存api")
                .version("1.0.0") // 文档版本说明
                .build();
    }
}


