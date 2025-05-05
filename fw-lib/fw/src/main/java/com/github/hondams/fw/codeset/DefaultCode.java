package com.github.hondams.fw.codeset;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import lombok.Getter;

@Getter
public class DefaultCode implements Code {

    private final String code;
    private final String label;
    private final List<DefaultCodeLocaleLabel> localeLabels;

    public DefaultCode(String code, String label, List<DefaultCodeLocaleLabel> localeLabels) {
        this.code = code;
        this.label = label;
        this.localeLabels = Collections.unmodifiableList(localeLabels);
    }

    @Override
    public String getLabel(Locale locale) {
        for (DefaultCodeLocaleLabel localeLabel : this.localeLabels) {
            if (localeLabel.getLocale().equals(locale)) {
                return localeLabel.getLabel();
            }
        }
        return this.label;
    }
}
