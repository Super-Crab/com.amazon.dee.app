package com.amazon.alexa;

import android.app.KeyguardManager;
import android.util.Log;
import com.amazon.alexa.client.alexaservice.ui.UnlockDeviceActivity;
/* compiled from: UnlockDeviceActivity.java */
/* loaded from: classes.dex */
public class rtr extends KeyguardManager.KeyguardDismissCallback {
    public final /* synthetic */ UnlockDeviceActivity zZm;

    public rtr(UnlockDeviceActivity unlockDeviceActivity) {
        this.zZm = unlockDeviceActivity;
    }

    @Override // android.app.KeyguardManager.KeyguardDismissCallback
    public void onDismissCancelled() {
        Log.w(UnlockDeviceActivity.zZm, "Keyguard cancelled");
        this.zZm.zZm(false);
        UnlockDeviceActivity.zZm(this.zZm);
    }

    @Override // android.app.KeyguardManager.KeyguardDismissCallback
    public void onDismissError() {
        Log.w(UnlockDeviceActivity.zZm, "Keyguard dismiss error");
        this.zZm.zZm(false);
        UnlockDeviceActivity.zZm(this.zZm);
    }

    @Override // android.app.KeyguardManager.KeyguardDismissCallback
    public void onDismissSucceeded() {
        String str = UnlockDeviceActivity.zZm;
        this.zZm.BIo();
    }
}
