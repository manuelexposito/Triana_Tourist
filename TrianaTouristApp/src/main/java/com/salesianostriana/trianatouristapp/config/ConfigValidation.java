package com.salesianostriana.trianatouristapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ConfigValidation {


    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(){

        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();

        validatorFactoryBean.setValidationMessageSource(this.messageSource());

        return validatorFactoryBean;
    }



}
