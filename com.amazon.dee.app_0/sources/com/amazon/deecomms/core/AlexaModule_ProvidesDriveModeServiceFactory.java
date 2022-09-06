package com.amazon.deecomms.core;

import com.amazon.alexa.drivemode.api.DriveModeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class AlexaModule_ProvidesDriveModeServiceFactory implements Factory<DriveModeService> {
    private final AlexaModule module;

    public AlexaModule_ProvidesDriveModeServiceFactory(AlexaModule alexaModule) {
        this.module = alexaModule;
    }

    public static AlexaModule_ProvidesDriveModeServiceFactory create(AlexaModule alexaModule) {
        return new AlexaModule_ProvidesDriveModeServiceFactory(alexaModule);
    }

    public static DriveModeService provideInstance(AlexaModule alexaModule) {
        return (DriveModeService) Preconditions.checkNotNull(alexaModule.providesDriveModeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DriveModeService proxyProvidesDriveModeService(AlexaModule alexaModule) {
        return (DriveModeService) Preconditions.checkNotNull(alexaModule.providesDriveModeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeService mo10268get() {
        return provideInstance(this.module);
    }
}
