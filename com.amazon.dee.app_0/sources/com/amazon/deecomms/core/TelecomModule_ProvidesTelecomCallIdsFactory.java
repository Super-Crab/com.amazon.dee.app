package com.amazon.deecomms.core;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Queue;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesTelecomCallIdsFactory implements Factory<Queue<String>> {
    private final TelecomModule module;

    public TelecomModule_ProvidesTelecomCallIdsFactory(TelecomModule telecomModule) {
        this.module = telecomModule;
    }

    public static TelecomModule_ProvidesTelecomCallIdsFactory create(TelecomModule telecomModule) {
        return new TelecomModule_ProvidesTelecomCallIdsFactory(telecomModule);
    }

    public static Queue<String> provideInstance(TelecomModule telecomModule) {
        return (Queue) Preconditions.checkNotNull(telecomModule.providesTelecomCallIds(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Queue<String> proxyProvidesTelecomCallIds(TelecomModule telecomModule) {
        return (Queue) Preconditions.checkNotNull(telecomModule.providesTelecomCallIds(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Queue<String> mo10268get() {
        return provideInstance(this.module);
    }
}
