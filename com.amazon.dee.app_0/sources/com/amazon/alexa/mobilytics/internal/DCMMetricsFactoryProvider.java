package com.amazon.alexa.mobilytics.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricsFactory;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DCMMetricsFactoryProvider {
    private static final String ALEXA_MOBILE_ANDROID_DEVICE_TYPE = "A2TF17PFR55MTB";
    private static final String TAG = Log.tag(DCMMetricsFactoryProvider.class);
    private final Context context;
    private final String deviceType;

    @Inject
    public DCMMetricsFactoryProvider(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        this.context = (Context) Preconditions.checkNotNull(mobilyticsConfiguration.context());
        if (!TextUtils.isEmpty(mobilyticsConfiguration.dcmDeviceType())) {
            this.deviceType = mobilyticsConfiguration.dcmDeviceType();
        } else {
            this.deviceType = "A2TF17PFR55MTB";
        }
    }

    public MetricsFactory getMetricsFactory() {
        MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(this.context);
        AndroidMetricsFactoryImpl.setDeviceType(this.context, this.deviceType);
        Log.i(TAG, "Using device type [%s] for logging metrics to DCM", this.deviceType);
        return androidMetricsFactoryImpl;
    }
}
