package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactNativeCoachMarkPackageFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvideReactNativeCoachMarkPackageFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideReactNativeCoachMarkPackageFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideReactNativeCoachMarkPackageFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvideReactNativeCoachMarkPackage(elementsModule);
    }

    public static ReactPackage proxyProvideReactNativeCoachMarkPackage(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideReactNativeCoachMarkPackage(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
