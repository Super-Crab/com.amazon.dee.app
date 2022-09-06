package com.amazon.alexa;

import android.app.KeyguardManager;
import android.os.PowerManager;
import com.amazon.alexa.client.alexaservice.ui.UnlockDeviceActivity;
import com.amazon.alexa.utils.TimeProvider;
import dagger.MembersInjector;
/* compiled from: UnlockDeviceActivity_MembersInjector.java */
/* loaded from: classes.dex */
public final class IBg implements MembersInjector<UnlockDeviceActivity> {
    public static void zZm(UnlockDeviceActivity unlockDeviceActivity, KeyguardManager keyguardManager) {
        unlockDeviceActivity.zyO = keyguardManager;
    }

    public static void zZm(UnlockDeviceActivity unlockDeviceActivity, PowerManager powerManager) {
        unlockDeviceActivity.jiA = powerManager;
    }

    public static void zZm(UnlockDeviceActivity unlockDeviceActivity, TimeProvider timeProvider) {
        unlockDeviceActivity.Qle = timeProvider;
    }
}
