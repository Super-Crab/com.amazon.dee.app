package com.amazon.dee.app.dependencies;

import com.amazon.alexa.dialog.api.DialogBuilderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideDialogBuilderProviderFactory implements Factory<DialogBuilderProvider> {
    private final ServiceModule module;

    public ServiceModule_ProvideDialogBuilderProviderFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideDialogBuilderProviderFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideDialogBuilderProviderFactory(serviceModule);
    }

    public static DialogBuilderProvider provideInstance(ServiceModule serviceModule) {
        return proxyProvideDialogBuilderProvider(serviceModule);
    }

    public static DialogBuilderProvider proxyProvideDialogBuilderProvider(ServiceModule serviceModule) {
        return (DialogBuilderProvider) Preconditions.checkNotNull(serviceModule.provideDialogBuilderProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DialogBuilderProvider mo10268get() {
        return provideInstance(this.module);
    }
}
