package com.amazon.alexa.accessory.engagement;
/* loaded from: classes.dex */
interface EnvironmentAttributes {
    String getApplicationDeviceSerialNumber();

    String getApplicationDeviceType();

    String getApplicationVersionCode();

    String getCountryOfResidence();

    String getDirectedCustomerId();

    String getPreferredMarketplaceId();

    boolean isApplicationOnFireOsDevice();

    boolean isUserSignedIn();
}
