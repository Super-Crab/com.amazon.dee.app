package com.amazon.alexa.voiceui.notifications;

import com.amazon.alexa.voiceui.lockscreen.LockscreenController;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SmartLockNotificationInteractor_Factory implements Factory<SmartLockNotificationInteractor> {
    private final Provider<LockscreenController> lockscreenControllerProvider;

    public SmartLockNotificationInteractor_Factory(Provider<LockscreenController> provider) {
        this.lockscreenControllerProvider = provider;
    }

    public static SmartLockNotificationInteractor_Factory create(Provider<LockscreenController> provider) {
        return new SmartLockNotificationInteractor_Factory(provider);
    }

    public static SmartLockNotificationInteractor newSmartLockNotificationInteractor(LockscreenController lockscreenController) {
        return new SmartLockNotificationInteractor(lockscreenController);
    }

    public static SmartLockNotificationInteractor provideInstance(Provider<LockscreenController> provider) {
        return new SmartLockNotificationInteractor(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SmartLockNotificationInteractor mo10268get() {
        return provideInstance(this.lockscreenControllerProvider);
    }
}
