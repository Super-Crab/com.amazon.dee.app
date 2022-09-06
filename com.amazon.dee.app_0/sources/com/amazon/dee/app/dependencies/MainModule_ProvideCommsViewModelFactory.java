package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.ui.comms.CommsViewModel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideCommsViewModelFactory implements Factory<CommsViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<MainActivityLifecycleService> mainActivityLifecycleServiceProvider;
    private final MainModule module;

    public MainModule_ProvideCommsViewModelFactory(MainModule mainModule, Provider<Context> provider, Provider<MainActivityLifecycleService> provider2) {
        this.module = mainModule;
        this.contextProvider = provider;
        this.mainActivityLifecycleServiceProvider = provider2;
    }

    public static MainModule_ProvideCommsViewModelFactory create(MainModule mainModule, Provider<Context> provider, Provider<MainActivityLifecycleService> provider2) {
        return new MainModule_ProvideCommsViewModelFactory(mainModule, provider, provider2);
    }

    public static CommsViewModel provideInstance(MainModule mainModule, Provider<Context> provider, Provider<MainActivityLifecycleService> provider2) {
        return proxyProvideCommsViewModel(mainModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static CommsViewModel proxyProvideCommsViewModel(MainModule mainModule, Context context, MainActivityLifecycleService mainActivityLifecycleService) {
        return (CommsViewModel) Preconditions.checkNotNull(mainModule.provideCommsViewModel(context, mainActivityLifecycleService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsViewModel mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.mainActivityLifecycleServiceProvider);
    }
}
