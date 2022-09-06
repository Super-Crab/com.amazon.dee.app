package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideViewManagerEventNotifierFactory implements Factory<ViewManagerEventNotifier> {
    private final Provider<ViewManagerDelegate> delegateProvider;
    private final ViewManagerModule module;

    public ViewManagerModule_ProvideViewManagerEventNotifierFactory(ViewManagerModule viewManagerModule, Provider<ViewManagerDelegate> provider) {
        this.module = viewManagerModule;
        this.delegateProvider = provider;
    }

    public static ViewManagerModule_ProvideViewManagerEventNotifierFactory create(ViewManagerModule viewManagerModule, Provider<ViewManagerDelegate> provider) {
        return new ViewManagerModule_ProvideViewManagerEventNotifierFactory(viewManagerModule, provider);
    }

    public static ViewManagerEventNotifier provideInstance(ViewManagerModule viewManagerModule, Provider<ViewManagerDelegate> provider) {
        return proxyProvideViewManagerEventNotifier(viewManagerModule, provider.mo10268get());
    }

    public static ViewManagerEventNotifier proxyProvideViewManagerEventNotifier(ViewManagerModule viewManagerModule, ViewManagerDelegate viewManagerDelegate) {
        return (ViewManagerEventNotifier) Preconditions.checkNotNull(viewManagerModule.provideViewManagerEventNotifier(viewManagerDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewManagerEventNotifier mo10268get() {
        return provideInstance(this.module, this.delegateProvider);
    }
}
