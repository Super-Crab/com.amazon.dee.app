package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesPhysicalPtzDirectiveFactoryFactory implements Factory<PhysicalPtzDirectiveFactory> {
    private final PtzModule module;

    public PtzModule_ProvidesPhysicalPtzDirectiveFactoryFactory(PtzModule ptzModule) {
        this.module = ptzModule;
    }

    public static PtzModule_ProvidesPhysicalPtzDirectiveFactoryFactory create(PtzModule ptzModule) {
        return new PtzModule_ProvidesPhysicalPtzDirectiveFactoryFactory(ptzModule);
    }

    public static PhysicalPtzDirectiveFactory provideInstance(PtzModule ptzModule) {
        return proxyProvidesPhysicalPtzDirectiveFactory(ptzModule);
    }

    public static PhysicalPtzDirectiveFactory proxyProvidesPhysicalPtzDirectiveFactory(PtzModule ptzModule) {
        return (PhysicalPtzDirectiveFactory) Preconditions.checkNotNull(ptzModule.providesPhysicalPtzDirectiveFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhysicalPtzDirectiveFactory mo10268get() {
        return provideInstance(this.module);
    }
}
