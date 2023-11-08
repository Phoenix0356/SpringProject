//package com.demo.config;
//
//import com.demo.util.JwtInterceptor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//    @Configuration
//    @PropertySource("classpath:openUrl.yml")
//    public class WebConfig implements WebMvcConfigurer {
//
//        private final JwtInterceptor jwtInterceptor;
//        @Value("${excludePaths}")
//        String[] excludePaths;
//
//        public WebConfig(JwtInterceptor jwtInterceptor) {
//            this.jwtInterceptor = jwtInterceptor;
//        }
//
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(jwtInterceptor)
//                    .excludePathPatterns(excludePaths);
//
//        }
//    }
