package com.amazon.whisperjoin.common.sharedtypes.radios;
/* loaded from: classes13.dex */
public enum ScanningMode {
    PROHIBITED(0),
    LOW_POWER_HIGH_LATENCY(1),
    BALANCED(2),
    HIGH_POWER_LOW_LATENCY(3);
    
    private final int mValue;

    ScanningMode(int i) {
        this.mValue = i;
    }

    public static ScanningMode getFromValue(int i) {
        ScanningMode[] values;
        for (ScanningMode scanningMode : values()) {
            if (scanningMode.mValue == i) {
                return scanningMode;
            }
        }
        return null;
    }
}
