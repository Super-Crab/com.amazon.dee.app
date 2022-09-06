package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesFeatureServiceV2Factory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesFeatureServiceV2Factory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesFeatureServiceV2Factory(alexaSharingModule);
    }

    public static FeatureServiceV2 provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesFeatureServiceV2(alexaSharingModule);
    }

    public static FeatureServiceV2 proxyProvidesFeatureServiceV2(AlexaSharingModule alexaSharingModule) {
        return (FeatureServiceV2) Preconditions.checkNotNull(alexaSharingModule.providesFeatureServiceV2(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module);
    }
}
