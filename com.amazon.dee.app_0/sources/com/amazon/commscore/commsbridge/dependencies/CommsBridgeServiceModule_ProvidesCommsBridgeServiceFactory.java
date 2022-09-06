package com.amazon.commscore.commsbridge.dependencies;

import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CommsBridgeServiceModule_ProvidesCommsBridgeServiceFactory implements Factory<CommsBridgeService> {
    private final CommsBridgeServiceModule module;

    public CommsBridgeServiceModule_ProvidesCommsBridgeServiceFactory(CommsBridgeServiceModule commsBridgeServiceModule) {
        this.module = commsBridgeServiceModule;
    }

    public static CommsBridgeServiceModule_ProvidesCommsBridgeServiceFactory create(CommsBridgeServiceModule commsBridgeServiceModule) {
        return new CommsBridgeServiceModule_ProvidesCommsBridgeServiceFactory(commsBridgeServiceModule);
    }

    public static CommsBridgeService provideInstance(CommsBridgeServiceModule commsBridgeServiceModule) {
        return proxyProvidesCommsBridgeService(commsBridgeServiceModule);
    }

    public static CommsBridgeService proxyProvidesCommsBridgeService(CommsBridgeServiceModule commsBridgeServiceModule) {
        return (CommsBridgeService) Preconditions.checkNotNull(commsBridgeServiceModule.providesCommsBridgeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsBridgeService mo10268get() {
        return provideInstance(this.module);
    }
}
