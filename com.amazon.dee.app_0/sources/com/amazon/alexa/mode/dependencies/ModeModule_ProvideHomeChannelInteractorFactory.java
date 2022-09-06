package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.drive.HomeChannelInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideHomeChannelInteractorFactory implements Factory<HomeChannelInteractor> {
    private final ModeModule module;

    public ModeModule_ProvideHomeChannelInteractorFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvideHomeChannelInteractorFactory create(ModeModule modeModule) {
        return new ModeModule_ProvideHomeChannelInteractorFactory(modeModule);
    }

    public static HomeChannelInteractor provideInstance(ModeModule modeModule) {
        return proxyProvideHomeChannelInteractor(modeModule);
    }

    public static HomeChannelInteractor proxyProvideHomeChannelInteractor(ModeModule modeModule) {
        return (HomeChannelInteractor) Preconditions.checkNotNull(modeModule.provideHomeChannelInteractor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeChannelInteractor mo10268get() {
        return provideInstance(this.module);
    }
}
