package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideHomeCardViewModelFactory implements Factory<HomeCardViewModel> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideHomeCardViewModelFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideHomeCardViewModelFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideHomeCardViewModelFactory(staticReleasePackageModule);
    }

    public static HomeCardViewModel provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideHomeCardViewModel(staticReleasePackageModule);
    }

    public static HomeCardViewModel proxyProvideHomeCardViewModel(StaticReleasePackageModule staticReleasePackageModule) {
        return (HomeCardViewModel) Preconditions.checkNotNull(staticReleasePackageModule.provideHomeCardViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeCardViewModel mo10268get() {
        return provideInstance(this.module);
    }
}
