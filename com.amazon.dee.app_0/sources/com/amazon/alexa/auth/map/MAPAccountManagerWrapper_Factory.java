package com.amazon.alexa.auth.map;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MAPAccountManagerWrapper_Factory implements Factory<MAPAccountManagerWrapper> {
    private final Provider<Context> contextProvider;

    public MAPAccountManagerWrapper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static MAPAccountManagerWrapper_Factory create(Provider<Context> provider) {
        return new MAPAccountManagerWrapper_Factory(provider);
    }

    public static MAPAccountManagerWrapper newMAPAccountManagerWrapper(Context context) {
        return new MAPAccountManagerWrapper(context);
    }

    public static MAPAccountManagerWrapper provideInstance(Provider<Context> provider) {
        return new MAPAccountManagerWrapper(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAccountManagerWrapper mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
