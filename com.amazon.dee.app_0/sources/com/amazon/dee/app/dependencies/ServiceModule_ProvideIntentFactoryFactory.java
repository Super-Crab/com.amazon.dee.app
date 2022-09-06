package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.ui.main.IntentFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideIntentFactoryFactory implements Factory<IntentFactory> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideIntentFactoryFactory(ServiceModule serviceModule, Provider<Context> provider) {
        this.module = serviceModule;
        this.contextProvider = provider;
    }

    public static ServiceModule_ProvideIntentFactoryFactory create(ServiceModule serviceModule, Provider<Context> provider) {
        return new ServiceModule_ProvideIntentFactoryFactory(serviceModule, provider);
    }

    public static IntentFactory provideInstance(ServiceModule serviceModule, Provider<Context> provider) {
        return proxyProvideIntentFactory(serviceModule, provider.mo10268get());
    }

    public static IntentFactory proxyProvideIntentFactory(ServiceModule serviceModule, Context context) {
        return (IntentFactory) Preconditions.checkNotNull(serviceModule.provideIntentFactory(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IntentFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
