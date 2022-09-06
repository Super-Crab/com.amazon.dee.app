package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesTelecomCallAudioRouteObservableFactory implements Factory<TelecomCallAudioRouteObservable> {
    private final TelecomModule module;

    public TelecomModule_ProvidesTelecomCallAudioRouteObservableFactory(TelecomModule telecomModule) {
        this.module = telecomModule;
    }

    public static TelecomModule_ProvidesTelecomCallAudioRouteObservableFactory create(TelecomModule telecomModule) {
        return new TelecomModule_ProvidesTelecomCallAudioRouteObservableFactory(telecomModule);
    }

    public static TelecomCallAudioRouteObservable provideInstance(TelecomModule telecomModule) {
        return (TelecomCallAudioRouteObservable) Preconditions.checkNotNull(telecomModule.providesTelecomCallAudioRouteObservable(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static TelecomCallAudioRouteObservable proxyProvidesTelecomCallAudioRouteObservable(TelecomModule telecomModule) {
        return (TelecomCallAudioRouteObservable) Preconditions.checkNotNull(telecomModule.providesTelecomCallAudioRouteObservable(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TelecomCallAudioRouteObservable mo10268get() {
        return provideInstance(this.module);
    }
}
