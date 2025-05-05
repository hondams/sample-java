package com.github.hondams.fw.codeset;

import java.util.Locale;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DefaultCodeLocaleLabel {

    private final String label;
    private final Locale locale;
}
