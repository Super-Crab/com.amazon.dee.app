package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.navigation.NavigationRepo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class NavigationDataModule_ProvideNavigationRepoFactory implements Factory<NavigationRepo> {
    private final NavigationDataModule module;

    public NavigationDataModule_ProvideNavigationRepoFactory(NavigationDataModule navigationDataModule) {
        this.module = navigationDataModule;
    }

    public static NavigationDataModule_ProvideNavigationRepoFactory create(NavigationDataModule navigationDataModule) {
        return new NavigationDataModule_ProvideNavigationRepoFactory(navigationDataModule);
    }

    public static NavigationRepo provideInstance(NavigationDataModule navigationDataModule) {
        return proxyProvideNavigationRepo(navigationDataModule);
    }

    public static NavigationRepo proxyProvideNavigationRepo(NavigationDataModule navigationDataModule) {
        return (NavigationRepo) Preconditions.checkNotNull(navigationDataModule.provideNavigationRepo(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NavigationRepo mo10268get() {
        return provideInstance(this.module);
    }
}
