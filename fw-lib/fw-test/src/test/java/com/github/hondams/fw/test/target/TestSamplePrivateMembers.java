package com.github.hondams.fw.test.target;

public class TestSamplePrivateMembers {

    private static String staticField;

    private String instanceField;

    public static void reset() {
        staticField = null;
    }

    @SuppressWarnings("unused")
    private static String invokeStaticMethod(String arg1) {
        return staticField + ":" + arg1;
    }

    @SuppressWarnings("unused")
    private String invokeInstanceMethod(String arg1) {
        return instanceField + ":" + arg1;
    }
}
