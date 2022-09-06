package com.amazon.alexa.voiceui.notifications;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class SmartLockNotificationPresenter {
    private final SmartLockNotificationView notificationView;
    private final SmartLockNotificationInteractor notificationsInteractor;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class LockscreenCallbacks implements LockscreenController.LockscreenCallbacks {
        LockscreenCallbacks() {
        }

        @Override // com.amazon.alexa.voiceui.lockscreen.LockscreenController.LockscreenCallbacks
        public void onUnlockFailure() {
        }

        @Override // com.amazon.alexa.voiceui.lockscreen.LockscreenController.LockscreenCallbacks
        public void onUnlockSuccess(boolean z, boolean z2) {
            if (SmartLockNotificationPresenter.this.notificationsInteractor.isUsingSecureLockscreen()) {
                SmartLockNotificationPresenter.this.notificationView.showNotification();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public SmartLockNotificationPresenter(SmartLockNotificationView smartLockNotificationView, SmartLockNotificationInteractor smartLockNotificationInteractor) {
        this.notificationsInteractor = smartLockNotificationInteractor;
        this.notificationView = smartLockNotificationView;
        smartLockNotificationInteractor.setLockscreenCallbacks(new LockscreenCallbacks());
    }
}
