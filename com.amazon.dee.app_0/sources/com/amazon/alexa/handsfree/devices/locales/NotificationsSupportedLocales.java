package com.amazon.alexa.handsfree.devices.locales;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.utils.ResourceFilesLoader;
/* loaded from: classes8.dex */
public class NotificationsSupportedLocales {
    private final int mResIdNotificationsSupportedLocaleList;
    private final ResourceFilesLoader mResourceFilesLoader;
    private String[] mSupportedLocalesArray;

    public NotificationsSupportedLocales(int i) {
        this(new ResourceFilesLoader(), i);
    }

    public boolean isLocaleSupported(@NonNull Context context, @NonNull String str) {
        if (this.mSupportedLocalesArray == null) {
            this.mSupportedLocalesArray = this.mResourceFilesLoader.getStringArrayResource(context, this.mResIdNotificationsSupportedLocaleList);
        }
        for (String str2 : this.mSupportedLocalesArray) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return this.mSupportedLocalesArray.length == 0;
    }

    @VisibleForTesting
    NotificationsSupportedLocales(@NonNull ResourceFilesLoader resourceFilesLoader, int i) {
        this.mResourceFilesLoader = resourceFilesLoader;
        this.mResIdNotificationsSupportedLocaleList = i;
    }
}
