package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.impl.ViewControllerFactoryProducer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideViewControllerFactoryProducerFactory implements Factory<ViewControllerFactoryProducer> {
    private final ViewManagerModule module;

    public ViewManagerModule_ProvideViewControllerFactoryProducerFactory(ViewManagerModule viewManagerModule) {
        this.module = viewManagerModule;
    }

    public static ViewManagerModule_ProvideViewControllerFactoryProducerFactory create(ViewManagerModule viewManagerModule) {
        return new ViewManagerModule_ProvideViewControllerFactoryProducerFactory(viewManagerModule);
    }

    public static ViewControllerFactoryProducer provideInstance(ViewManagerModule viewManagerModule) {
        return proxyProvideViewControllerFactoryProducer(viewManagerModule);
    }

    public static ViewControllerFactoryProducer proxyProvideViewControllerFactoryProducer(ViewManagerModule viewManagerModule) {
        return (ViewControllerFactoryProducer) Preconditions.checkNotNull(viewManagerModule.provideViewControllerFactoryProducer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewControllerFactoryProducer mo10268get() {
        return provideInstance(this.module);
    }
}
