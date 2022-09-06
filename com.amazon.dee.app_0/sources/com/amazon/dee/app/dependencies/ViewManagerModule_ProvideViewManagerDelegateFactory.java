package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate;
import com.amazon.dee.app.ui.main.MainActivity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideViewManagerDelegateFactory implements Factory<ViewManagerDelegate> {
    private final Provider<MainActivity> activityProvider;
    private final ViewManagerModule module;

    public ViewManagerModule_ProvideViewManagerDelegateFactory(ViewManagerModule viewManagerModule, Provider<MainActivity> provider) {
        this.module = viewManagerModule;
        this.activityProvider = provider;
    }

    public static ViewManagerModule_ProvideViewManagerDelegateFactory create(ViewManagerModule viewManagerModule, Provider<MainActivity> provider) {
        return new ViewManagerModule_ProvideViewManagerDelegateFactory(viewManagerModule, provider);
    }

    public static ViewManagerDelegate provideInstance(ViewManagerModule viewManagerModule, Provider<MainActivity> provider) {
        return proxyProvideViewManagerDelegate(viewManagerModule, provider.mo10268get());
    }

    public static ViewManagerDelegate proxyProvideViewManagerDelegate(ViewManagerModule viewManagerModule, MainActivity mainActivity) {
        return (ViewManagerDelegate) Preconditions.checkNotNull(viewManagerModule.provideViewManagerDelegate(mainActivity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewManagerDelegate mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
