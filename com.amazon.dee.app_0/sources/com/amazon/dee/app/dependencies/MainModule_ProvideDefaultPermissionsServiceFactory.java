package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.permissions.DefaultPermissionsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideDefaultPermissionsServiceFactory implements Factory<DefaultPermissionsService> {
    private final Provider<Activity> activityProvider;
    private final MainModule module;

    public MainModule_ProvideDefaultPermissionsServiceFactory(MainModule mainModule, Provider<Activity> provider) {
        this.module = mainModule;
        this.activityProvider = provider;
    }

    public static MainModule_ProvideDefaultPermissionsServiceFactory create(MainModule mainModule, Provider<Activity> provider) {
        return new MainModule_ProvideDefaultPermissionsServiceFactory(mainModule, provider);
    }

    public static DefaultPermissionsService provideInstance(MainModule mainModule, Provider<Activity> provider) {
        return proxyProvideDefaultPermissionsService(mainModule, provider.mo10268get());
    }

    public static DefaultPermissionsService proxyProvideDefaultPermissionsService(MainModule mainModule, Activity activity) {
        return (DefaultPermissionsService) Preconditions.checkNotNull(mainModule.provideDefaultPermissionsService(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultPermissionsService mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
