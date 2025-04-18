package org.springframework.boot.validation;

import org.springframework.context.MessageSource;

import jakarta.annotation.Nonnull;
import jakarta.validation.MessageInterpolator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageSourceMessageInterpolatorFactory {

    @Nonnull
    public MessageInterpolator createMessageInterpolator(MessageSource messageSource, MessageInterpolator messageInterpolator) {
        return new MessageSourceMessageInterpolator(messageSource, messageInterpolator);
    }
}
