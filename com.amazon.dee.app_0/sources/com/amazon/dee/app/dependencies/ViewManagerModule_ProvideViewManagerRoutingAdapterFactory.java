package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.impl.ViewManagerRoutingAdapter;
import com.amazon.alexa.viewmanagement.impl.ViewPresenter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideViewManagerRoutingAdapterFactory implements Factory<ViewManagerRoutingAdapter> {
    private final ViewManagerModule module;
    private final Provider<ViewPresenter> viewPresenterProvider;

    public ViewManagerModule_ProvideViewManagerRoutingAdapterFactory(ViewManagerModule viewManagerModule, Provider<ViewPresenter> provider) {
        this.module = viewManagerModule;
        this.viewPresenterProvider = provider;
    }

    public static ViewManagerModule_ProvideViewManagerRoutingAdapterFactory create(ViewManagerModule viewManagerModule, Provider<ViewPresenter> provider) {
        return new ViewManagerModule_ProvideViewManagerRoutingAdapterFactory(viewManagerModule, provider);
    }

    public static ViewManagerRoutingAdapter provideInstance(ViewManagerModule viewManagerModule, Provider<ViewPresenter> provider) {
        return proxyProvideViewManagerRoutingAdapter(viewManagerModule, provider.mo10268get());
    }

    public static ViewManagerRoutingAdapter proxyProvideViewManagerRoutingAdapter(ViewManagerModule viewManagerModule, ViewPresenter viewPresenter) {
        return (ViewManagerRoutingAdapter) Preconditions.checkNotNull(viewManagerModule.provideViewManagerRoutingAdapter(viewPresenter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ViewManagerRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.viewPresenterProvider);
    }
}
