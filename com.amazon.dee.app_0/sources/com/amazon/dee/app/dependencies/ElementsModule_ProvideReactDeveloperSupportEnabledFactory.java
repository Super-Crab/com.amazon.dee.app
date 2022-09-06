package com.amazon.dee.app.dependencies;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactDeveloperSupportEnabledFactory implements Factory<Boolean> {
    private final ElementsModule module;

    public ElementsModule_ProvideReactDeveloperSupportEnabledFactory(ElementsModule elementsModule) {
        this.module = elementsModule;
    }

    public static ElementsModule_ProvideReactDeveloperSupportEnabledFactory create(ElementsModule elementsModule) {
        return new ElementsModule_ProvideReactDeveloperSupportEnabledFactory(elementsModule);
    }

    public static Boolean provideInstance(ElementsModule elementsModule) {
        return Boolean.valueOf(proxyProvideReactDeveloperSupportEnabled(elementsModule));
    }

    public static boolean proxyProvideReactDeveloperSupportEnabled(ElementsModule elementsModule) {
        return elementsModule.provideReactDeveloperSupportEnabled();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Boolean mo10268get() {
        return provideInstance(this.module);
    }
}
