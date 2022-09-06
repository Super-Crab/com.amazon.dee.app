package com.amazon.dee.app.dependencies;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.facebook.react.ReactPackage;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideMainReactPackageFactory implements Factory<ReactPackage> {
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final ElementsModule module;

    public ElementsModule_ProvideMainReactPackageFactory(ElementsModule elementsModule, Provider<FeatureServiceV2> provider) {
        this.module = elementsModule;
        this.featureServiceV2Provider = provider;
    }

    public static ElementsModule_ProvideMainReactPackageFactory create(ElementsModule elementsModule, Provider<FeatureServiceV2> provider) {
        return new ElementsModule_ProvideMainReactPackageFactory(elementsModule, provider);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule, Provider<FeatureServiceV2> provider) {
        return proxyProvideMainReactPackage(elementsModule, DoubleCheck.lazy(provider));
    }

    public static ReactPackage proxyProvideMainReactPackage(ElementsModule elementsModule, Lazy<FeatureServiceV2> lazy) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideMainReactPackage(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module, this.featureServiceV2Provider);
    }
}
