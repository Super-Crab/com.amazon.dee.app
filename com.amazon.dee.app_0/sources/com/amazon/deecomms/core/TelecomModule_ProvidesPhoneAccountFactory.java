package com.amazon.deecomms.core;

import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesPhoneAccountFactory implements Factory<PhoneAccount> {
    private final TelecomModule module;
    private final Provider<PhoneAccountHandle> phoneAccountHandleProvider;

    public TelecomModule_ProvidesPhoneAccountFactory(TelecomModule telecomModule, Provider<PhoneAccountHandle> provider) {
        this.module = telecomModule;
        this.phoneAccountHandleProvider = provider;
    }

    public static TelecomModule_ProvidesPhoneAccountFactory create(TelecomModule telecomModule, Provider<PhoneAccountHandle> provider) {
        return new TelecomModule_ProvidesPhoneAccountFactory(telecomModule, provider);
    }

    public static PhoneAccount provideInstance(TelecomModule telecomModule, Provider<PhoneAccountHandle> provider) {
        return (PhoneAccount) Preconditions.checkNotNull(telecomModule.providesPhoneAccount(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PhoneAccount proxyProvidesPhoneAccount(TelecomModule telecomModule, PhoneAccountHandle phoneAccountHandle) {
        return (PhoneAccount) Preconditions.checkNotNull(telecomModule.providesPhoneAccount(phoneAccountHandle), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhoneAccount mo10268get() {
        return provideInstance(this.module, this.phoneAccountHandleProvider);
    }
}
