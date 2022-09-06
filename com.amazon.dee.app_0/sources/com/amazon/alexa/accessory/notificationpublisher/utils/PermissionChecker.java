package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
/* loaded from: classes.dex */
public final class PermissionChecker {
    private static final String TAG = "PermissionChecker";

    private PermissionChecker() {
    }

    public static boolean hasRecordAudioPermission() {
        Context context = DependencyProvider.getContext();
        if (context != null) {
            return ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") == 0;
        }
        Log.w(TAG, "hasRecordAudioPermission - Context is null");
        return false;
    }
}
