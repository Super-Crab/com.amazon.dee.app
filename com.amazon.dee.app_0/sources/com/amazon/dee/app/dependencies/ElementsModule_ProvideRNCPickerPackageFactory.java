package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideRNCPickerPackageFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvideRNCPickerPackageFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideRNCPickerPackageFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideRNCPickerPackageFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvideRNCPickerPackage(elementsModule);
    }

    public static ReactPackage proxyProvideRNCPickerPackage(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideRNCPickerPackage(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
