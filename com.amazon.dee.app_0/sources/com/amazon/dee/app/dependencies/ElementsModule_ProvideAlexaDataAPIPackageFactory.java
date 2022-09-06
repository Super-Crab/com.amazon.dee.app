package com.amazon.dee.app.dependencies;

import com.dee.app.data.DefaultElementLocalStorage;
import com.dee.app.data.reactnative.ElementsDataService;
import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideAlexaDataAPIPackageFactory implements Factory<ReactPackage> {
    private final Provider<ElementsDataService> clientProvider;
    private final Provider<DefaultElementLocalStorage> dataCacheProvider;
    private final Provider<DefaultElementLocalStorage> dataStoreProvider;
    private final ElementsModule module;

    public ElementsModule_ProvideAlexaDataAPIPackageFactory(ElementsModule elementsModule, Provider<ElementsDataService> provider, Provider<DefaultElementLocalStorage> provider2, Provider<DefaultElementLocalStorage> provider3) {
        this.module = elementsModule;
        this.clientProvider = provider;
        this.dataCacheProvider = provider2;
        this.dataStoreProvider = provider3;
    }

    public static ElementsModule_ProvideAlexaDataAPIPackageFactory create(ElementsModule elementsModule, Provider<ElementsDataService> provider, Provider<DefaultElementLocalStorage> provider2, Provider<DefaultElementLocalStorage> provider3) {
        return new ElementsModule_ProvideAlexaDataAPIPackageFactory(elementsModule, provider, provider2, provider3);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule, Provider<ElementsDataService> provider, Provider<DefaultElementLocalStorage> provider2, Provider<DefaultElementLocalStorage> provider3) {
        return proxyProvideAlexaDataAPIPackage(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ReactPackage proxyProvideAlexaDataAPIPackage(ElementsModule elementsModule, ElementsDataService elementsDataService, DefaultElementLocalStorage defaultElementLocalStorage, DefaultElementLocalStorage defaultElementLocalStorage2) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideAlexaDataAPIPackage(elementsDataService, defaultElementLocalStorage, defaultElementLocalStorage2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module, this.clientProvider, this.dataCacheProvider, this.dataStoreProvider);
    }
}
