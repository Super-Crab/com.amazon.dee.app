package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class AccessoryModule_ProvidesScoPrioritizerFactory implements Factory<ScoPrioritizer> {
    private final AccessoryModule module;

    public AccessoryModule_ProvidesScoPrioritizerFactory(AccessoryModule accessoryModule) {
        this.module = accessoryModule;
    }

    public static AccessoryModule_ProvidesScoPrioritizerFactory create(AccessoryModule accessoryModule) {
        return new AccessoryModule_ProvidesScoPrioritizerFactory(accessoryModule);
    }

    public static ScoPrioritizer provideInstance(AccessoryModule accessoryModule) {
        return proxyProvidesScoPrioritizer(accessoryModule);
    }

    public static ScoPrioritizer proxyProvidesScoPrioritizer(AccessoryModule accessoryModule) {
        return (ScoPrioritizer) Preconditions.checkNotNull(accessoryModule.providesScoPrioritizer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ScoPrioritizer mo10268get() {
        return provideInstance(this.module);
    }
}
