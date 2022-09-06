package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.redesign.HomeViewDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class HomeModule_ProvideHomeViewDelegateFactory implements Factory<HomeViewDelegate> {
    private final Provider<Activity> activityProvider;
    private final HomeModule module;

    public HomeModule_ProvideHomeViewDelegateFactory(HomeModule homeModule, Provider<Activity> provider) {
        this.module = homeModule;
        this.activityProvider = provider;
    }

    public static HomeModule_ProvideHomeViewDelegateFactory create(HomeModule homeModule, Provider<Activity> provider) {
        return new HomeModule_ProvideHomeViewDelegateFactory(homeModule, provider);
    }

    public static HomeViewDelegate provideInstance(HomeModule homeModule, Provider<Activity> provider) {
        return proxyProvideHomeViewDelegate(homeModule, provider.mo10268get());
    }

    public static HomeViewDelegate proxyProvideHomeViewDelegate(HomeModule homeModule, Activity activity) {
        return (HomeViewDelegate) Preconditions.checkNotNull(homeModule.provideHomeViewDelegate(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeViewDelegate mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
