package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.main.MainRoutingAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideMainRoutingAdapterFactory implements Factory<MainRoutingAdapter> {
    private final MainModule module;

    public MainModule_ProvideMainRoutingAdapterFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideMainRoutingAdapterFactory create(MainModule mainModule) {
        return new MainModule_ProvideMainRoutingAdapterFactory(mainModule);
    }

    public static MainRoutingAdapter provideInstance(MainModule mainModule) {
        return proxyProvideMainRoutingAdapter(mainModule);
    }

    public static MainRoutingAdapter proxyProvideMainRoutingAdapter(MainModule mainModule) {
        return (MainRoutingAdapter) Preconditions.checkNotNull(mainModule.provideMainRoutingAdapter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainRoutingAdapter mo10268get() {
        return provideInstance(this.module);
    }
}
