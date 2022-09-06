package com.amazon.alexa.handsfree.notification.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.locales.NotificationsSupportedLocales;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class SupportedLocales {
    private final Context mContext;
    private final NotificationsSupportedLocales mNotificationsSupportedLocales;

    public SupportedLocales(@NonNull Context context) {
        this(context, DeviceTypeInformationProvider.getInstance(context));
    }

    public boolean isCurrentLocaleSupported() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        int i = Build.VERSION.SDK_INT;
        return this.mNotificationsSupportedLocales.isLocaleSupported(this.mContext, configuration.getLocales().get(0).toLanguageTag());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public SupportedLocales(@NonNull Context context, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider) {
        this.mContext = context;
        this.mNotificationsSupportedLocales = deviceTypeInformationProvider.getDeviceInformationForCurrentDevice(this.mContext).getNotificationsSupportedLocales();
    }
}
