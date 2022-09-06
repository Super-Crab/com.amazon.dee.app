package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideAutomotiveAccessoryConnectivityObserverFactory implements Factory<AutomotiveAccessoryConnectivityObserver> {
    private final ModeModule module;

    public ModeModule_ProvideAutomotiveAccessoryConnectivityObserverFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvideAutomotiveAccessoryConnectivityObserverFactory create(ModeModule modeModule) {
        return new ModeModule_ProvideAutomotiveAccessoryConnectivityObserverFactory(modeModule);
    }

    public static AutomotiveAccessoryConnectivityObserver provideInstance(ModeModule modeModule) {
        return proxyProvideAutomotiveAccessoryConnectivityObserver(modeModule);
    }

    public static AutomotiveAccessoryConnectivityObserver proxyProvideAutomotiveAccessoryConnectivityObserver(ModeModule modeModule) {
        return (AutomotiveAccessoryConnectivityObserver) Preconditions.checkNotNull(modeModule.provideAutomotiveAccessoryConnectivityObserver(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutomotiveAccessoryConnectivityObserver mo10268get() {
        return provideInstance(this.module);
    }
}
