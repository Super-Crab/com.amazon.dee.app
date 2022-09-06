package com.amazon.alexa.mobilytics.dependencies;

import com.amazonaws.regions.Regions;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideCognitoPoolIdsFactory implements Factory<Map<Regions, String>> {
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideCognitoPoolIdsFactory(MobilyticsModule mobilyticsModule) {
        this.module = mobilyticsModule;
    }

    public static MobilyticsModule_ProvideCognitoPoolIdsFactory create(MobilyticsModule mobilyticsModule) {
        return new MobilyticsModule_ProvideCognitoPoolIdsFactory(mobilyticsModule);
    }

    public static Map<Regions, String> provideInstance(MobilyticsModule mobilyticsModule) {
        return proxyProvideCognitoPoolIds(mobilyticsModule);
    }

    public static Map<Regions, String> proxyProvideCognitoPoolIds(MobilyticsModule mobilyticsModule) {
        return (Map) Preconditions.checkNotNull(mobilyticsModule.provideCognitoPoolIds(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Map<Regions, String> mo10268get() {
        return provideInstance(this.module);
    }
}
