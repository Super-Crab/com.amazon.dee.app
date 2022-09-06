package com.amazon.alexa.voiceui.notifications;

import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import javax.inject.Inject;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes11.dex */
public class SmartLockNotificationInteractor {
    private final LockscreenController lockscreenController;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public SmartLockNotificationInteractor(LockscreenController lockscreenController) {
        this.lockscreenController = lockscreenController;
    }

    public boolean isUsingSecureLockscreen() {
        return this.lockscreenController.isKeyguardSecure();
    }

    public void setLockscreenCallbacks(LockscreenController.LockscreenCallbacks lockscreenCallbacks) {
        this.lockscreenController.addLockscreenCallbacks(lockscreenCallbacks);
    }
}
