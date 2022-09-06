package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.fullscreentakeover.FullScreenTakeoverViewModel;
import com.facebook.react.ReactInstanceManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideFullScreenTakeoverViewModelFactory implements Factory<FullScreenTakeoverViewModel> {
    private final MainModule module;
    private final Provider<ReactInstanceManager> reactInstanceManagerProvider;

    public MainModule_ProvideFullScreenTakeoverViewModelFactory(MainModule mainModule, Provider<ReactInstanceManager> provider) {
        this.module = mainModule;
        this.reactInstanceManagerProvider = provider;
    }

    public static MainModule_ProvideFullScreenTakeoverViewModelFactory create(MainModule mainModule, Provider<ReactInstanceManager> provider) {
        return new MainModule_ProvideFullScreenTakeoverViewModelFactory(mainModule, provider);
    }

    public static FullScreenTakeoverViewModel provideInstance(MainModule mainModule, Provider<ReactInstanceManager> provider) {
        return proxyProvideFullScreenTakeoverViewModel(mainModule, provider.mo10268get());
    }

    public static FullScreenTakeoverViewModel proxyProvideFullScreenTakeoverViewModel(MainModule mainModule, ReactInstanceManager reactInstanceManager) {
        return (FullScreenTakeoverViewModel) Preconditions.checkNotNull(mainModule.provideFullScreenTakeoverViewModel(reactInstanceManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FullScreenTakeoverViewModel mo10268get() {
        return provideInstance(this.module, this.reactInstanceManagerProvider);
    }
}
