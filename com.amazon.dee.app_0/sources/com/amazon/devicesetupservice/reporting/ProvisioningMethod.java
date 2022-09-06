package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class ProvisioningMethod {
    public static final String CHIP = "CHIP";
    public static final String MANUAL = "MANUAL";
    public static final String FFS = "FFS";
    public static final String WIFI_FFS = "WIFI_FFS";
    public static final String SH_PHILIPS = "SH_PHILIPS";
    private static final String[] values = {FFS, "MANUAL", WIFI_FFS, SH_PHILIPS, "CHIP"};

    private ProvisioningMethod() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
