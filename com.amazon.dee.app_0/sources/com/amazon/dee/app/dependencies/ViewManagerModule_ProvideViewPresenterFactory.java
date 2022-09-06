package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.impl.ViewPresenter;
import com.amazon.dee.app.ui.main.MainActivity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideViewPresenterFactory implements Factory<ViewPresenter> {
    private final Provider<MainActivity> activityProvider;
    private final ViewManagerModule module;

    public ViewManagerModule_ProvideViewPresenterFactory(ViewManagerModule viewManagerModule, Provider<MainActivity> provider) {
        this.module = viewManagerModule;
        this.activityProvider = provider;
    }

    public static ViewManagerModule_ProvideViewPresenterFactory create(ViewManagerModule viewManagerModule, Provider<MainActivity> provider) {
        return new ViewManagerModule_ProvideViewPresenterFactory(viewManagerModule, provider);
    }

    public static ViewPresenter provideInstance(ViewManagerModule viewManagerModule, Provider<MainActivity> provider) {
        return proxyProvideViewPresenter(viewManagerModule, provider.mo10268get());
    }

    public static ViewPresenter proxyProvideViewPresenter(ViewManagerModule viewManagerModule, MainActivity mainActivity) {
        return (ViewPresenter) Preconditions.checkNotNull(viewManagerModule.provideViewPresenter(mainActivity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewPresenter mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
