package com.amazon.deecomms.core;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesTelecomLockFactory implements Factory<Object> {
    private final TelecomModule module;

    public TelecomModule_ProvidesTelecomLockFactory(TelecomModule telecomModule) {
        this.module = telecomModule;
    }

    public static TelecomModule_ProvidesTelecomLockFactory create(TelecomModule telecomModule) {
        return new TelecomModule_ProvidesTelecomLockFactory(telecomModule);
    }

    public static Object provideInstance(TelecomModule telecomModule) {
        return Preconditions.checkNotNull(telecomModule.providesTelecomLock(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Object proxyProvidesTelecomLock(TelecomModule telecomModule) {
        return Preconditions.checkNotNull(telecomModule.providesTelecomLock(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return provideInstance(this.module);
    }
}
