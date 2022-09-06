package com.amazon.dee.app.dependencies;

import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvidesRNCNetInfoFactory implements Factory<ReactPackage> {
    private final ElementsModule module;

    public ElementsModule_ProvidesRNCNetInfoFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvidesRNCNetInfoFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvidesRNCNetInfoFactory(elementsModule);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule) {
        return proxyProvidesRNCNetInfo(elementsModule);
    }

    public static ReactPackage proxyProvidesRNCNetInfo(ElementsModule elementsModule) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.providesRNCNetInfo(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module);
    }
}
