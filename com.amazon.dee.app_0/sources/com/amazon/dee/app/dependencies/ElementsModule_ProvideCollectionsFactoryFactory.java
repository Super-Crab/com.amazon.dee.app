package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.elements.CollectionsFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideCollectionsFactoryFactory implements Factory<CollectionsFactory> {
    private final ElementsModule module;

    public ElementsModule_ProvideCollectionsFactoryFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideCollectionsFactoryFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideCollectionsFactoryFactory(elementsModule);
    }

    public static CollectionsFactory provideInstance(ElementsModule elementsModule) {
        return proxyProvideCollectionsFactory(elementsModule);
    }

    public static CollectionsFactory proxyProvideCollectionsFactory(ElementsModule elementsModule) {
        return (CollectionsFactory) Preconditions.checkNotNull(elementsModule.provideCollectionsFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CollectionsFactory mo10268get() {
        return provideInstance(this.module);
    }
}
