package com.github.hondams.fw.codeset;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.MessageSource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumCodeSetUtils {

    @Getter
    @Setter
    private MessageSource messageSource;

    @Getter
    @Setter
    private Locale defaultLocale;

    private Map<Class<? extends EnumCode>, String> codeSetTypeMap = new ConcurrentHashMap<>();

    public String getCodeSetType(Class<? extends EnumCode> enumCodeClass) {
        String codeSetType = codeSetTypeMap.get(enumCodeClass);
        if (codeSetType != null) {
            return codeSetType;
        }
        EnumCodeSetType enumCodeSetType = enumCodeClass.getAnnotation(EnumCodeSetType.class);
        if (enumCodeSetType == null) {
            codeSetType = enumCodeClass.getClass().getSimpleName();
        } else {
            codeSetType = enumCodeSetType.value();
        }
        codeSetTypeMap.put(enumCodeClass, codeSetType);
        return codeSetType;
    }

    public List<Code> getCodes(Class<? extends EnumCode> enumCodeClass) {
        return List.of(enumCodeClass.getEnumConstants());
    }

    public String getLabel(EnumCode enumCode) {
        return getLabel(enumCode, defaultLocale);
    }

    public String getLabel(EnumCode enumCode, Locale locale) {
        String messageKey =
                "EnumCodeLabel." + getCodeSetType(enumCode.getClass()) + "." + enumCode.getCode();
        return messageSource.getMessage(messageKey, null, locale);
    }
}
