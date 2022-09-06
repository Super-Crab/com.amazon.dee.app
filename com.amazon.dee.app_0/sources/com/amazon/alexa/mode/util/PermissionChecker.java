package com.amazon.alexa.mode.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
/* loaded from: classes9.dex */
public class PermissionChecker {
    private final Context mContext;

    public PermissionChecker(@NonNull Context context) {
        this.mContext = context;
    }

    public boolean hasMicrophonePermission() {
        return ContextCompat.checkSelfPermission(this.mContext, "android.permission.RECORD_AUDIO") == 0;
    }
}
