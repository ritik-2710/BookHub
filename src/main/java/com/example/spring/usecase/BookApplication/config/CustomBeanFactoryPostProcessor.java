//package com.example.spring.usecase.BookApplication.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
////        String[] beanNames = beanFactory.getBeanDefinitionNames();
////
////        for (String beanName : beanNames) {
////            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
////            System.out.println("Bean Definition: " + beanName + " - " + beanDefinition.getBeanClassName());
////        }
//
//        System.out.println("Custom BeanFactoryPostProcessor is invoked");
//    }
//}
