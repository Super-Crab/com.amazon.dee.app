package com.amazon.alexa.client.metrics.mobilytics;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class MetricsConfiguration {
    public static final MetricsConfiguration create(String str, String str2) {
        return new AutoValue_MetricsConfiguration(str, str2);
    }

    public abstract String applicationId();

    public abstract String serviceName();
}
