package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvidesRNCWebviewFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvidesRNCWebviewFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvidesRNCWebviewFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvidesRNCWebviewFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvidesRNCWebview(elementsModule);
    }

    public static ReactPackage proxyProvidesRNCWebview(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.providesRNCWebview(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
