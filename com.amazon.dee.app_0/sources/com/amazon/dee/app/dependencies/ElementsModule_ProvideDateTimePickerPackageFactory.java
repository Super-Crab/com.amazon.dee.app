package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideDateTimePickerPackageFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvideDateTimePickerPackageFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideDateTimePickerPackageFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideDateTimePickerPackageFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvideDateTimePickerPackage(elementsModule);
    }

    public static ReactPackage proxyProvideDateTimePickerPackage(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideDateTimePickerPackage(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
