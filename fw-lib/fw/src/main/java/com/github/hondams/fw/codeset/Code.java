package com.github.hondams.fw.codeset;

import java.util.Locale;

public interface Code {

    String getCode();

    String getLabel();

    String getLabel(Locale locale);
}
