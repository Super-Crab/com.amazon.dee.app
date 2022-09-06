package com.amazon.deecomms.auth;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsSharedPreferences_Factory implements Factory<CommsSharedPreferences> {
    private final Provider<Context> contextProvider;

    public CommsSharedPreferences_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static CommsSharedPreferences_Factory create(Provider<Context> provider) {
        return new CommsSharedPreferences_Factory(provider);
    }

    public static CommsSharedPreferences newCommsSharedPreferences(Context context) {
        return new CommsSharedPreferences(context);
    }

    public static CommsSharedPreferences provideInstance(Provider<Context> provider) {
        return new CommsSharedPreferences(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsSharedPreferences mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
