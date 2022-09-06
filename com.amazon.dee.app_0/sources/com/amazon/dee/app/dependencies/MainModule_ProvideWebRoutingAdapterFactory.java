package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.WebRoutingAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideWebRoutingAdapterFactory implements Factory<WebRoutingAdapter> {
    private final MainModule module;

    public MainModule_ProvideWebRoutingAdapterFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideWebRoutingAdapterFactory create(MainModule mainModule) {
        return new MainModule_ProvideWebRoutingAdapterFactory(mainModule);
    }

    public static WebRoutingAdapter provideInstance(MainModule mainModule) {
        return proxyProvideWebRoutingAdapter(mainModule);
    }

    public static WebRoutingAdapter proxyProvideWebRoutingAdapter(MainModule mainModule) {
        return (WebRoutingAdapter) Preconditions.checkNotNull(mainModule.provideWebRoutingAdapter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WebRoutingAdapter mo10268get() {
        return provideInstance(this.module);
    }
}
