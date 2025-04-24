package com.github.hondams.fw.validation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.hibernate.validator.messageinterpolation.HibernateMessageInterpolatorContext;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import jakarta.validation.MessageInterpolator;
import jakarta.validation.Path;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FieldTypeMessageInterpolator implements MessageInterpolator {

    private final ResourceBundleLocator resourceBundleLocator;
    private final MessageInterpolator messageInterpolator;

    @Override
    public String interpolate(String messageTemplate, Context context) {
        throw new UnsupportedOperationException();
    }


    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        String messageKey = getMessageKey(messageTemplate, context, locale);
        return this.messageInterpolator.interpolate(messageKey, context, locale);
    }

    private String getMessageKey(String messageTemplate, Context context, Locale locale) {
        Class<?> fieldType = getFieldType(context);
        if (fieldType == null) {
            return messageTemplate;
        }
        String messageKey = getAnnotationType(context).getName() + "@" + fieldType.getName() + ".message";
        if (!containsMessageKey(messageKey, locale)) {
            return messageTemplate;
        }
        return "{"+ messageKey + "}";
    }
    private Class<?> getAnnotationType(Context context) {
        return context.getConstraintDescriptor().getAnnotation().annotationType();
    }
    
    private Class<?> getFieldType(Context context) {
        if (context instanceof HibernateMessageInterpolatorContext messageInterpolatorContext) {
            // messageInterpolatorContextから、データクラスに定義されているデータ項目のデータ型を取得する。
            // ただし、Hibernate Validatorのバージョンによっては、取得できない場合がある。
            // その場合は、nullを返す。
            // 取得できない場合は、Hibernate Validatorのバージョンを確認すること。            
            Path propertyPath = messageInterpolatorContext.getPropertyPath();
            Class<?> rootBeanClass = messageInterpolatorContext.getRootBeanType();

            propertyPath.
            if (propertyPath != null && rootBeanClass != null) {
                try {
                    String fieldName = propertyPath.toString(); // Assumes simple property paths
                    return rootBeanClass.getDeclaredField(fieldName).getType();
                } catch (NoSuchFieldException | SecurityException e) {
                    // Log or handle the exception if necessary
                    return null;
                }
            }
        }

        }
        // if (context.getValidatedValue() == null) {
        //     return null;
        // }
        // Class<?> fieldType = context.getValidatedValue().getClass();
        // if (!supportedFieldTypes.contains(fieldType)) {
        //     return null;
        // }
        return fieldType;
    }

    private boolean containsMessageKey(String messageKey, Locale locale) {
        ResourceBundle resourceBundle = this.resourceBundleLocator.getResourceBundle(locale);
        if (resourceBundle == null) {
            return false;
        }
        return resourceBundle.containsKey(messageKey);
    }

    private static Set<Class<?>> supportedFieldTypes = Set.of(
        String.class, Integer.class, Long.class, Boolean.class,
        LocalDate.class, LocalDateTime.class, LocalTime.class, 
        BigDecimal.class, BigInteger.class
    );
}
