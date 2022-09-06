package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.voice.model.VoiceService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideVoiceServiceFactory implements Factory<VoiceService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideVoiceServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideVoiceServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideVoiceServiceFactory(applicationModule);
    }

    public static VoiceService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideVoiceService(applicationModule);
    }

    public static VoiceService proxyProvideVoiceService(ApplicationModule applicationModule) {
        return (VoiceService) Preconditions.checkNotNull(applicationModule.provideVoiceService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceService mo10268get() {
        return provideInstance(this.module);
    }
}
