package com.quanmin.paresfile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Yangquanmin
 * @version 1.0.1
 * @since 2020-1-11
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class SwaggerConfig {

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quanmin.paresfile.modules"))
                .build()
                .apiInfo(testApiInfo());
    }

    /**
     * @return ApiInfo
     */
    private ApiInfo testApiInfo() {
        return new ApiInfo("文件解析", // 大标题
                "文件解析API", // 小标题
                "v1.0", // 版本
                "NO terms of server", "2659126621@qq.com", // 作者
                "The Apache License, Version 2.0", // 链接显示文字
                "http://www.apache.org/licenses/LICENSE-2.0.html"// 网站链接
        );
    }
}
