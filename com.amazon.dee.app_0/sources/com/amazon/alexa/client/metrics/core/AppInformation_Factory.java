package com.amazon.alexa.client.metrics.core;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AppInformation_Factory implements Factory<AppInformation> {
    private final Provider<Context> contextProvider;
    private final Provider<PackageManager> packageManagerProvider;

    public AppInformation_Factory(Provider<Context> provider, Provider<PackageManager> provider2) {
        this.contextProvider = provider;
        this.packageManagerProvider = provider2;
    }

    public static AppInformation_Factory create(Provider<Context> provider, Provider<PackageManager> provider2) {
        return new AppInformation_Factory(provider, provider2);
    }

    public static AppInformation newAppInformation(Context context, PackageManager packageManager) {
        return new AppInformation(context, packageManager);
    }

    public static AppInformation provideInstance(Provider<Context> provider, Provider<PackageManager> provider2) {
        return new AppInformation(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppInformation mo10268get() {
        return provideInstance(this.contextProvider, this.packageManagerProvider);
    }
}
