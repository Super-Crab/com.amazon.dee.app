package com.amazon.deecomms.calling.datachannel;
/* loaded from: classes12.dex */
public enum PayloadVersion {
    VERSION_1_0(1.0d),
    LATEST(1.0d);
    
    private final double value;

    PayloadVersion(double d) {
        this.value = d;
    }

    public double getValue() {
        return this.value;
    }
}
