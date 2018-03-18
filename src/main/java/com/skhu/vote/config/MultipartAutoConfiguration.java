//package com.skhu.vote.config;
//
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.Servlet;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.web.MultipartProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;
//import org.springframework.web.servlet.DispatcherServlet;
//
///*
// * 이미지 업로드를 하지 않아서 상관없지만
// * 아무튼 이게 꼭 필요한지 모르겠음
// */
//
//@Configuration
//@ConditionalOnClass({Servlet.class, StandardServletMultipartResolver.class, MultipartConfigElement.class})
//@ConditionalOnProperty(prefix = "spring.http.multipart", name = "enabled" , matchIfMissing = true)
//@EnableConfigurationProperties(MultipartProperties.class)
//public class MultipartAutoConfiguration {
//
//    private final MultipartProperties multipartProperties;
//
//    public MultipartAutoConfiguration(MultipartProperties multipartProperties){
//        this.multipartProperties = multipartProperties;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public MultipartConfigElement multipartConfigElement(){
//        return this.multipartProperties.createMultipartConfig();
//    }
//
//    @Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
//    @ConditionalOnMissingBean(MultipartResolver.class)
//    public StandardServletMultipartResolver multipartResolver(){
//        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
//        multipartResolver.setResolveLazily(this.multipartProperties.isResolveLazily());
//        return multipartResolver;
//    }
//
//}