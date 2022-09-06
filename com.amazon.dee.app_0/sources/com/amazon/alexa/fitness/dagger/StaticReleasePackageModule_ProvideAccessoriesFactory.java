package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideAccessoriesFactory implements Factory<Accessories> {
    private final Provider<Context> applicationContextProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideAccessoriesFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        this.module = staticReleasePackageModule;
        this.applicationContextProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideAccessoriesFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return new StaticReleasePackageModule_ProvideAccessoriesFactory(staticReleasePackageModule, provider);
    }

    public static Accessories provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return proxyProvideAccessories(staticReleasePackageModule, provider.mo10268get());
    }

    public static Accessories proxyProvideAccessories(StaticReleasePackageModule staticReleasePackageModule, Context context) {
        return (Accessories) Preconditions.checkNotNull(staticReleasePackageModule.provideAccessories(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Accessories mo10268get() {
        return provideInstance(this.module, this.applicationContextProvider);
    }
}
