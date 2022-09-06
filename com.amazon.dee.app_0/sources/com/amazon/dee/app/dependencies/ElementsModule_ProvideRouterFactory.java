package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideRouterFactory implements Factory<Router> {
    private final Provider<ReactFeatureManager> featureManagerProvider;
    private final ElementsModule module;

    public ElementsModule_ProvideRouterFactory(ElementsModule elementsModule, Provider<ReactFeatureManager> provider) {
        this.module = elementsModule;
        this.featureManagerProvider = provider;
    }

    public static ElementsModule_ProvideRouterFactory create(ElementsModule elementsModule, Provider<ReactFeatureManager> provider) {
        return new ElementsModule_ProvideRouterFactory(elementsModule, provider);
    }

    public static Router provideInstance(ElementsModule elementsModule, Provider<ReactFeatureManager> provider) {
        return proxyProvideRouter(elementsModule, provider.mo10268get());
    }

    public static Router proxyProvideRouter(ElementsModule elementsModule, ReactFeatureManager reactFeatureManager) {
        return (Router) Preconditions.checkNotNull(elementsModule.provideRouter(reactFeatureManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Router mo10268get() {
        return provideInstance(this.module, this.featureManagerProvider);
    }
}
