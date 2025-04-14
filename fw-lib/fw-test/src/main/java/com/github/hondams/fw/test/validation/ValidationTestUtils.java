package com.github.hondams.fw.test.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationTestUtils {

    private String messageBasenames = "messages";
    private String messageDefaultEncoding = "UTF-8";

    private MessageSource messageSource;

    private LocalValidatorFactoryBean validator;

    public void setMessageBasenames(String messageBasenames) {
        if (!Objects.equals(ValidationTestUtils.messageBasenames, messageBasenames)) {
            ValidationTestUtils.messageBasenames = messageBasenames;
            ValidationTestUtils.messageSource = null;
            ValidationTestUtils.validator = null;
        }
    }

    private MessageSource getMessageSource() {
        if (messageSource == null) {
            ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
            resourceBundleMessageSource.setBasename(messageBasenames);
            resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
            resourceBundleMessageSource.setDefaultEncoding(messageDefaultEncoding);
            messageSource = resourceBundleMessageSource;
        }
        return messageSource;
    }

    private LocalValidatorFactoryBean getValidator() {
        if (validator == null) {
            // org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration
            LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
            MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory(getMessageSource());
            validatorFactoryBean.setMessageInterpolator(interpolatorFactory.getObject());
            validatorFactoryBean.afterPropertiesSet();
            validator = validatorFactoryBean;
        }
        return validator;
    }

    public BindingResult validate(Object form) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(form, form.getClass().getSimpleName());
        getValidator().validate(form, bindingResult);
        return bindingResult;
    }

    public BindingResult validate(Object form, Class<?>... groups) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(form, form.getClass().getSimpleName());
        getValidator().validate(form, bindingResult, (Object[]) groups);
        return bindingResult;
    }

    public List<String> getValidatedMessages(Object form) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(form, form.getClass().getSimpleName());
        getValidator().validate(form, bindingResult);
        List<String> messages = new ArrayList<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            messages.add(getMessageSource().getMessage(error, Locale.JAPAN));
        }
        return messages;
    }
}
