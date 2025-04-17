package org.springframework.boot.validation;

import jakarta.validation.MessageInterpolator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageSourceMessageInterpolatorFactory {

    public MessageInterpolator createMessageInterpolator(MessageSource messageSource, MessageInterpolator messageInterpolator) {
        return new MessageSourceMessageInterpolator(messageSource, messageInterpolator);
    }
}
