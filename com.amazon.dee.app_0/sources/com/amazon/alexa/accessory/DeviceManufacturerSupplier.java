package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public interface DeviceManufacturerSupplier {
    default boolean allowMultipleAccounts(String str) {
        return true;
    }

    boolean isFirstParty(String str);

    boolean isFwUpdateSupported(String str);
}
