package com.github.hondams.fw.validation;

import java.io.IOException;

import org.hibernate.validator.internal.engine.AbstractConfigurationImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.boot.validation.MessageSourceMessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.annotation.PostConstruct;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@AutoConfiguration
@RequiredArgsConstructor    
public class ValidationAutoConfiguration {

    private final ResourcePatternResolver resourcePatternResolver;

    @Bean
    LocalValidatorFactoryBean defaultValidator(MessageInterpolator messageInterpolator,
        ObjectProvider<ValidationConfigurationCustomizer> customizers) {

        // org.springframework.boot.autoconfigure.validation.ValidationAutoConfigurationとほぼ同じ実装で、
        // MessageInterpolatorを置き換える。
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setConfigurationInitializer(//
            configuration -> customizers.orderedStream().forEach(//
                customizer -> customizer.customize(configuration)));
        factoryBean.setMessageInterpolator(messageInterpolator);
        return factoryBean;
    }

    @Bean
    MessageInterpolatorFactory messageInterpolator(MessageSource messageSource) {
        return new MessageInterpolatorFactory(messageSource);  
    }

    @PostConstruct
    public void init() throws IOException {

        Resource[] resources = this.resourcePatternResolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/ValidationMessages.properties");
        
        switch (resources.length) {
            case 0:
                throw new IllegalStateException("ValidationMessages.properties not found in classpath.");
            case 1:
                // Only one resource found, no action needed.
                break;
            default:
                throw new IllegalStateException("Multiple ValidationMessages.properties found in classpath. " + resources);
        }
    }
}
