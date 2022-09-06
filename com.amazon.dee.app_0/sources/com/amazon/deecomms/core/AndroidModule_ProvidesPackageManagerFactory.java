package com.amazon.deecomms.core;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesPackageManagerFactory implements Factory<PackageManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesPackageManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesPackageManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesPackageManagerFactory(androidModule, provider);
    }

    public static PackageManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (PackageManager) Preconditions.checkNotNull(androidModule.providesPackageManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static PackageManager proxyProvidesPackageManager(AndroidModule androidModule, Context context) {
        return (PackageManager) Preconditions.checkNotNull(androidModule.providesPackageManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PackageManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
