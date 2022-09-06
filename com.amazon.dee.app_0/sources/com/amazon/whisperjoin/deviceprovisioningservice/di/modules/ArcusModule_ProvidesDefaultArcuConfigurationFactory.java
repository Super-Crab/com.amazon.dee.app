package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public final class ArcusModule_ProvidesDefaultArcuConfigurationFactory implements Factory<JSONObject> {
    private final ArcusModule module;

    public ArcusModule_ProvidesDefaultArcuConfigurationFactory(ArcusModule arcusModule) {
        this.module = arcusModule;
    }

    public static ArcusModule_ProvidesDefaultArcuConfigurationFactory create(ArcusModule arcusModule) {
        return new ArcusModule_ProvidesDefaultArcuConfigurationFactory(arcusModule);
    }

    public static JSONObject provideInstance(ArcusModule arcusModule) {
        return proxyProvidesDefaultArcuConfiguration(arcusModule);
    }

    public static JSONObject proxyProvidesDefaultArcuConfiguration(ArcusModule arcusModule) {
        return (JSONObject) Preconditions.checkNotNull(arcusModule.providesDefaultArcuConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public JSONObject mo10268get() {
        return provideInstance(this.module);
    }
}
