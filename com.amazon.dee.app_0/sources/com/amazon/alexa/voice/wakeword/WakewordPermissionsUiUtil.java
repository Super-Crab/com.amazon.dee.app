package com.amazon.alexa.voice.wakeword;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
/* loaded from: classes11.dex */
final class WakewordPermissionsUiUtil {
    private WakewordPermissionsUiUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"NewApi"})
    public static void requestPermissionIfNeededOrFinish(Activity activity, int i) {
        int i2 = Build.VERSION.SDK_INT;
        activity.requestPermissions(VoicePermissionsAuthority.getMinimumPermissions(), i);
    }
}
