package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.elements.AlexaNativeModuleCallExceptionHandler;
import com.amazon.dee.app.ui.main.RNLogPrinter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideAlexaNativeModuleCallExceptionHandlerFactory implements Factory<AlexaNativeModuleCallExceptionHandler> {
    private final ElementsModule module;
    private final Provider<RNLogPrinter> rnLogPrinterProvider;

    public ElementsModule_ProvideAlexaNativeModuleCallExceptionHandlerFactory(ElementsModule elementsModule, Provider<RNLogPrinter> provider) {
        this.module = elementsModule;
        this.rnLogPrinterProvider = provider;
    }

    public static ElementsModule_ProvideAlexaNativeModuleCallExceptionHandlerFactory create(ElementsModule elementsModule, Provider<RNLogPrinter> provider) {
        return new ElementsModule_ProvideAlexaNativeModuleCallExceptionHandlerFactory(elementsModule, provider);
    }

    public static AlexaNativeModuleCallExceptionHandler provideInstance(ElementsModule elementsModule, Provider<RNLogPrinter> provider) {
        return proxyProvideAlexaNativeModuleCallExceptionHandler(elementsModule, provider.mo10268get());
    }

    public static AlexaNativeModuleCallExceptionHandler proxyProvideAlexaNativeModuleCallExceptionHandler(ElementsModule elementsModule, RNLogPrinter rNLogPrinter) {
        return (AlexaNativeModuleCallExceptionHandler) Preconditions.checkNotNull(elementsModule.provideAlexaNativeModuleCallExceptionHandler(rNLogPrinter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaNativeModuleCallExceptionHandler mo10268get() {
        return provideInstance(this.module, this.rnLogPrinterProvider);
    }
}
