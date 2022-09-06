package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideSvgPackageFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvideSvgPackageFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideSvgPackageFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideSvgPackageFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvideSvgPackage(elementsModule);
    }

    public static ReactPackage proxyProvideSvgPackage(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideSvgPackage(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
