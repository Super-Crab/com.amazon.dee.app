package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class AccessoryModule_ProvidesConnectedAccessoryInquirerFactory implements Factory<ConnectedAccessoryInquirer> {
    private final AccessoryModule module;

    public AccessoryModule_ProvidesConnectedAccessoryInquirerFactory(AccessoryModule accessoryModule) {
        this.module = accessoryModule;
    }

    public static AccessoryModule_ProvidesConnectedAccessoryInquirerFactory create(AccessoryModule accessoryModule) {
        return new AccessoryModule_ProvidesConnectedAccessoryInquirerFactory(accessoryModule);
    }

    public static ConnectedAccessoryInquirer provideInstance(AccessoryModule accessoryModule) {
        return proxyProvidesConnectedAccessoryInquirer(accessoryModule);
    }

    public static ConnectedAccessoryInquirer proxyProvidesConnectedAccessoryInquirer(AccessoryModule accessoryModule) {
        return (ConnectedAccessoryInquirer) Preconditions.checkNotNull(accessoryModule.providesConnectedAccessoryInquirer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConnectedAccessoryInquirer mo10268get() {
        return provideInstance(this.module);
    }
}
