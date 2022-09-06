package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.Id;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.SDKInfo;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DeliveryClient;
import com.amazonaws.services.mobileanalytics.AmazonMobileAnalyticsClient;
@Deprecated
/* loaded from: classes13.dex */
public interface AnalyticsContext {
    Configuration getConfiguration();

    DeliveryClient getDeliveryClient();

    AmazonMobileAnalyticsClient getERSClient();

    String getNetworkType();

    SDKInfo getSDKInfo();

    System getSystem();

    Id getUniqueId();
}
