package com.amazon.alexa.handsfree.notification.api;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public interface UVRSettingsProvider {
    boolean isShowOnLockscreenEnabled(@NonNull Context context);

    boolean isUVRAvailable();

    boolean isUVREnabled();
}
