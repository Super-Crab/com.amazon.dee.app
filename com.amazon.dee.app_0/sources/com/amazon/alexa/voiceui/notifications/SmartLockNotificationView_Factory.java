package com.amazon.alexa.voiceui.notifications;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SmartLockNotificationView_Factory implements Factory<SmartLockNotificationView> {
    private final Provider<Context> contextProvider;

    public SmartLockNotificationView_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static SmartLockNotificationView_Factory create(Provider<Context> provider) {
        return new SmartLockNotificationView_Factory(provider);
    }

    public static SmartLockNotificationView newSmartLockNotificationView(Context context) {
        return new SmartLockNotificationView(context);
    }

    public static SmartLockNotificationView provideInstance(Provider<Context> provider) {
        return new SmartLockNotificationView(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SmartLockNotificationView mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
