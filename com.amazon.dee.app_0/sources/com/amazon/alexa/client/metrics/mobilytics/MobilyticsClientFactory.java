package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.MobilyticsFactory;
/* loaded from: classes6.dex */
public class MobilyticsClientFactory {
    public static final Mobilytics create(MobilyticsConfiguration mobilyticsConfiguration) {
        return MobilyticsFactory.getMobilytics(mobilyticsConfiguration);
    }
}
