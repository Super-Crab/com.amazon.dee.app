package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvidesRNCCameraRollFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvidesRNCCameraRollFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvidesRNCCameraRollFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvidesRNCCameraRollFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvidesRNCCameraRoll(elementsModule);
    }

    public static ReactPackage proxyProvidesRNCCameraRoll(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.providesRNCCameraRoll(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
