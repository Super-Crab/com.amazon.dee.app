package com.amazon.deecomms.core;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesPhoneAccountHandleFactory implements Factory<PhoneAccountHandle> {
    private final Provider<Context> contextProvider;
    private final TelecomModule module;

    public TelecomModule_ProvidesPhoneAccountHandleFactory(TelecomModule telecomModule, Provider<Context> provider) {
        this.module = telecomModule;
        this.contextProvider = provider;
    }

    public static TelecomModule_ProvidesPhoneAccountHandleFactory create(TelecomModule telecomModule, Provider<Context> provider) {
        return new TelecomModule_ProvidesPhoneAccountHandleFactory(telecomModule, provider);
    }

    public static PhoneAccountHandle provideInstance(TelecomModule telecomModule, Provider<Context> provider) {
        return (PhoneAccountHandle) Preconditions.checkNotNull(telecomModule.providesPhoneAccountHandle(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PhoneAccountHandle proxyProvidesPhoneAccountHandle(TelecomModule telecomModule, Context context) {
        return (PhoneAccountHandle) Preconditions.checkNotNull(telecomModule.providesPhoneAccountHandle(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhoneAccountHandle mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
