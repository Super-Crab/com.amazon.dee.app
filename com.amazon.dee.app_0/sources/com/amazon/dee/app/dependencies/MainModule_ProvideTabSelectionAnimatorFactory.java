package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.ui.main.TabSelectionAnimator;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideTabSelectionAnimatorFactory implements Factory<TabSelectionAnimator> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final MainModule module;

    public MainModule_ProvideTabSelectionAnimatorFactory(MainModule mainModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<IdentityService> provider3) {
        this.module = mainModule;
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
        this.identityServiceProvider = provider3;
    }

    public static MainModule_ProvideTabSelectionAnimatorFactory create(MainModule mainModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<IdentityService> provider3) {
        return new MainModule_ProvideTabSelectionAnimatorFactory(mainModule, provider, provider2, provider3);
    }

    public static TabSelectionAnimator provideInstance(MainModule mainModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<IdentityService> provider3) {
        return proxyProvideTabSelectionAnimator(mainModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static TabSelectionAnimator proxyProvideTabSelectionAnimator(MainModule mainModule, Context context, Lazy<EventBus> lazy, Lazy<IdentityService> lazy2) {
        return (TabSelectionAnimator) Preconditions.checkNotNull(mainModule.provideTabSelectionAnimator(context, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TabSelectionAnimator mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventBusProvider, this.identityServiceProvider);
    }
}
