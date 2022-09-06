package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideViewManagerLoadingDelegateFactory implements Factory<ViewManagerLoadingDelegate> {
    private final Provider<ViewManagerEventNotifier> eventNotifierProvider;
    private final ViewManagerModule module;

    public ViewManagerModule_ProvideViewManagerLoadingDelegateFactory(ViewManagerModule viewManagerModule, Provider<ViewManagerEventNotifier> provider) {
        this.module = viewManagerModule;
        this.eventNotifierProvider = provider;
    }

    public static ViewManagerModule_ProvideViewManagerLoadingDelegateFactory create(ViewManagerModule viewManagerModule, Provider<ViewManagerEventNotifier> provider) {
        return new ViewManagerModule_ProvideViewManagerLoadingDelegateFactory(viewManagerModule, provider);
    }

    public static ViewManagerLoadingDelegate provideInstance(ViewManagerModule viewManagerModule, Provider<ViewManagerEventNotifier> provider) {
        return proxyProvideViewManagerLoadingDelegate(viewManagerModule, provider.mo10268get());
    }

    public static ViewManagerLoadingDelegate proxyProvideViewManagerLoadingDelegate(ViewManagerModule viewManagerModule, ViewManagerEventNotifier viewManagerEventNotifier) {
        return (ViewManagerLoadingDelegate) Preconditions.checkNotNull(viewManagerModule.provideViewManagerLoadingDelegate(viewManagerEventNotifier), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewManagerLoadingDelegate mo10268get() {
        return provideInstance(this.module, this.eventNotifierProvider);
    }
}
