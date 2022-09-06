package com.amazon.devicesetupservice;
/* loaded from: classes12.dex */
public class DeviceAssociationMethod {
    public static final String INNER_BARCODE = "INNER_BARCODE";
    public static final String OUTER_BARCODE = "OUTER_BARCODE";
    private static final String[] values = {INNER_BARCODE, OUTER_BARCODE};

    private DeviceAssociationMethod() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
