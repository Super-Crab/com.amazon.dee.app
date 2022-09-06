package com.amazon.alexa.presence.bleconn.data;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DeveloperSettings_Factory implements Factory<DeveloperSettings> {
    private final Provider<Context> contextProvider;

    public DeveloperSettings_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static DeveloperSettings_Factory create(Provider<Context> provider) {
        return new DeveloperSettings_Factory(provider);
    }

    public static DeveloperSettings newDeveloperSettings(Context context) {
        return new DeveloperSettings(context);
    }

    public static DeveloperSettings provideInstance(Provider<Context> provider) {
        return new DeveloperSettings(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeveloperSettings mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
