package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import java.util.Locale;
@Deprecated
/* loaded from: classes13.dex */
public interface DeviceDetails {
    String carrier();

    Locale locale();

    String manufacturer();

    String model();

    String platform();

    String platformVersion();
}
