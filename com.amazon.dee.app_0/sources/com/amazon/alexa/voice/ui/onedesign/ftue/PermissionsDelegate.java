package com.amazon.alexa.voice.ui.onedesign.ftue;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface PermissionsDelegate {
    boolean hasPermission(@NonNull String str);

    void requestPermissions(@NonNull String[] strArr);
}
