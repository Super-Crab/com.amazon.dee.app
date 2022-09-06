package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideAppearancePackageFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvideAppearancePackageFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideAppearancePackageFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideAppearancePackageFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvideAppearancePackage(elementsModule);
    }

    public static ReactPackage proxyProvideAppearancePackage(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideAppearancePackage(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
