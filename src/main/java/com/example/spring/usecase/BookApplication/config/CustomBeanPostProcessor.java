//package com.example.spring.usecase.BookApplication.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomBeanPostProcessor implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("Before Initialization - Bean: " + beanName);
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("After Initialization - Bean: " + beanName);
//        return bean;
//    }
//}
