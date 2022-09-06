package com.amazon.alexa.voice.handsfree;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class HandsFreePermissionsSettings {
    @VisibleForTesting
    static final String KEY_PERMISSION_GRANTED = "primer";
    @VisibleForTesting
    static final String KEY_PERMISSION_SHOWN = "shown";
    @VisibleForTesting
    static final String SHARED_PREFS_FILE_NAME = "voice";
    private final Context context;
    private SharedPreferences preferences = null;

    public HandsFreePermissionsSettings(@NonNull Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        if (this.preferences == null) {
            this.preferences = this.context.getSharedPreferences("voice", 0);
        }
        return this.preferences;
    }

    public void setPermissionGranted() {
        GeneratedOutlineSupport1.outline143(getSharedPreferences(), KEY_PERMISSION_GRANTED, true);
    }

    public void setPermissionShown() {
        GeneratedOutlineSupport1.outline143(getSharedPreferences(), KEY_PERMISSION_SHOWN, true);
    }

    public boolean wasPermissionShown() {
        return getSharedPreferences().getBoolean(KEY_PERMISSION_SHOWN, false) || getSharedPreferences().getBoolean(KEY_PERMISSION_GRANTED, false);
    }
}
