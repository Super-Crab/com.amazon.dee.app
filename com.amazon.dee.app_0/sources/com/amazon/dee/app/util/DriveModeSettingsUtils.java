package com.amazon.dee.app.util;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.mode.ModeService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class DriveModeSettingsUtils {
    public static final String DRIVE_MODE_NOTIFICATION_INTENT = "com.amazon.alexa.mode.START_DRIVE_MODE";

    private DriveModeSettingsUtils() {
    }

    public static void handleDriveModeNotification() {
        new Handler(Looper.getMainLooper()).post($$Lambda$DriveModeSettingsUtils$lJ168edv4QusVxoWRKv6fnWRKE.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleDriveModeNotification$0() {
        ModeService modeService = (ModeService) GeneratedOutlineSupport1.outline21(ModeService.class);
        if (modeService != null) {
            modeService.startDriveMode(2);
        }
    }
}
