package com.amazon.alexa.voice.handsfree.utils;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakeWordStateValidator_Factory implements Factory<WakeWordStateValidator> {
    private final Provider<Context> contextProvider;

    public WakeWordStateValidator_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static WakeWordStateValidator_Factory create(Provider<Context> provider) {
        return new WakeWordStateValidator_Factory(provider);
    }

    public static WakeWordStateValidator newWakeWordStateValidator(Context context) {
        return new WakeWordStateValidator(context);
    }

    public static WakeWordStateValidator provideInstance(Provider<Context> provider) {
        return new WakeWordStateValidator(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WakeWordStateValidator mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
