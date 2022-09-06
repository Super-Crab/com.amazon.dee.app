package com.amazon.alexa.handsfree.metrics.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.utils.AnonymousDeviceIdProvider;
/* loaded from: classes8.dex */
public class MobilyticsAnonymousDeviceIdProvider implements AnonymousDeviceIdProvider {
    @VisibleForTesting
    static final String ANONYMOUS_DEVICE_ID_KEY = "InstallationId";
    @VisibleForTesting
    static final String EMPTY_DEVICE_ID = "";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE = "mobilytics.installation-id";
    private final Context mContext;

    public MobilyticsAnonymousDeviceIdProvider(@NonNull Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.utils.AnonymousDeviceIdProvider
    public String getAnonymousDeviceId() {
        return this.mContext.getSharedPreferences(SHARED_PREFERENCES_FILE, 0).getString("InstallationId", "");
    }
}
