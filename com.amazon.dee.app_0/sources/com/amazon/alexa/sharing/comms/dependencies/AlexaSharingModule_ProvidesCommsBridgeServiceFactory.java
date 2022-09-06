package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesCommsBridgeServiceFactory implements Factory<CommsBridgeService> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesCommsBridgeServiceFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesCommsBridgeServiceFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesCommsBridgeServiceFactory(alexaSharingModule);
    }

    public static CommsBridgeService provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesCommsBridgeService(alexaSharingModule);
    }

    public static CommsBridgeService proxyProvidesCommsBridgeService(AlexaSharingModule alexaSharingModule) {
        return (CommsBridgeService) Preconditions.checkNotNull(alexaSharingModule.providesCommsBridgeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsBridgeService mo10268get() {
        return provideInstance(this.module);
    }
}
