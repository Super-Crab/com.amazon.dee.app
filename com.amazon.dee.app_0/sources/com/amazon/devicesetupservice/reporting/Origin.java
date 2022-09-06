package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class Origin {
    public static final String PROVISIONER = "provisioner";
    public static final String PROVISIONABLE = "provisionable";
    public static final String DSS = "dss";
    private static final String[] values = {PROVISIONER, PROVISIONABLE, DSS};

    private Origin() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
