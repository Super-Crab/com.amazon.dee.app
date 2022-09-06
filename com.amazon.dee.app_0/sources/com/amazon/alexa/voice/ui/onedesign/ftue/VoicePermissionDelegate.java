package com.amazon.alexa.voice.ui.onedesign.ftue;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
/* loaded from: classes11.dex */
public abstract class VoicePermissionDelegate implements PermissionsDelegate {
    private final Context context;

    public VoicePermissionDelegate(Context context) {
        this.context = context;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.PermissionsDelegate
    public boolean hasPermission(@NonNull String str) {
        return ContextCompat.checkSelfPermission(this.context, str) == 0;
    }
}
