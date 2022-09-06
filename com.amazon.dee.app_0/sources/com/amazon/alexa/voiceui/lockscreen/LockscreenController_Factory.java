package com.amazon.alexa.voiceui.lockscreen;

import android.app.Activity;
import android.app.KeyguardManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class LockscreenController_Factory implements Factory<LockscreenController> {
    private final Provider<Activity> activityProvider;
    private final Provider<KeyguardManager> keyguardManagerProvider;

    public LockscreenController_Factory(Provider<KeyguardManager> provider, Provider<Activity> provider2) {
        this.keyguardManagerProvider = provider;
        this.activityProvider = provider2;
    }

    public static LockscreenController_Factory create(Provider<KeyguardManager> provider, Provider<Activity> provider2) {
        return new LockscreenController_Factory(provider, provider2);
    }

    public static LockscreenController newLockscreenController(KeyguardManager keyguardManager, Activity activity) {
        return new LockscreenController(keyguardManager, activity);
    }

    public static LockscreenController provideInstance(Provider<KeyguardManager> provider, Provider<Activity> provider2) {
        return new LockscreenController(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LockscreenController mo10268get() {
        return provideInstance(this.keyguardManagerProvider, this.activityProvider);
    }
}
