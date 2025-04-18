package com.github.hondams.fw.validation;

import org.hibernate.validator.internal.engine.AbstractConfigurationImpl;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.validation.MessageSourceMessageInterpolatorFactory;
import org.springframework.context.MessageSource;

import jakarta.validation.MessageInterpolator;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageInterpolatorFactory implements ObjectFactory<MessageInterpolator> {

    private final MessageSource messageSource;


    @Override
    public MessageInterpolator getObject() {
        // org.springframework.boot.validation.MessageInterpolatorFactoryの実装を参考に、
        // 外側から、
        // org.springframework.boot.validation.MessageSourceMessageInterpolator
        // FieldTypeごとに、メッセージを切り替えるMessageInterpolator
        // Validation.byDefaultProvider().configure().getDefaultMessageInterpolator()
        // となるようにする。

        AbstractConfigurationImpl<?> configure = (AbstractConfigurationImpl) Validation.byDefaultProvider().configure();
        MessageInterpolator messageInterpolator =
            Validation.byDefaultProvider().configure().getDefaultMessageInterpolator();
        messageInterpolator = new FieldTypeMessageInterpolator(configure.getDefaultResourceBundleLocator(), messageInterpolator);
        messageInterpolator = MessageSourceMessageInterpolatorFactory.createMessageInterpolator(messageSource, messageInterpolator);
        return messageInterpolator;
    }

}
