package com.amazon.alexa.handsfree.settings.voxsettings;

import android.content.Intent;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public interface VoxSettingsRequestHandler {
    public static final String SHARED_PREFS_FILENAME = "com.amazon.alexa.handsfree.voxconfig";
    public static final String SHOW_ON_LOCKSCREEN_PREF_KEY = "showOnLockscreen";
    public static final String SHOW_ON_LOCKSCREEN_PREF_VALUE_BLOCK_ALL = "BLOCK_ALL";
    public static final String SHOW_ON_LOCKSCREEN_PREF_VALUE_BLOCK_PERSONAL = "BLOCK_PERSONAL";
    public static final String SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY = "showOnLockscreenValue";

    boolean initialize(@NonNull Intent intent);

    String onConnectedToService();

    boolean onConnectedToServiceLegacy();

    void onRequestFailed(boolean z);

    void onRequestSucceeded(String str);

    void onRequestSucceeded(boolean z);
}
