package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public interface AccessoryScanRecord {
    byte getColor();

    short getProductId();

    byte[] getProductSpecificData();

    short getVendorId();

    boolean hasAnUpdateAvailable();

    boolean isDiscoverableOverBTClassic();

    boolean isInOOBEMode();

    boolean prefersRFCOMMConnection();
}
