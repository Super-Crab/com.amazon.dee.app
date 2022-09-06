package com.amazon.alexa.auto.navigation;

import android.content.pm.PackageManager;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class PreferredNavigationAppSettingManager_Factory implements Factory<PreferredNavigationAppSettingManager> {
    private final Provider<PackageManager> packageManagerProvider;
    private final Provider<PersistentStorage> persistentStorageProvider;

    public PreferredNavigationAppSettingManager_Factory(Provider<PackageManager> provider, Provider<PersistentStorage> provider2) {
        this.packageManagerProvider = provider;
        this.persistentStorageProvider = provider2;
    }

    public static PreferredNavigationAppSettingManager_Factory create(Provider<PackageManager> provider, Provider<PersistentStorage> provider2) {
        return new PreferredNavigationAppSettingManager_Factory(provider, provider2);
    }

    public static PreferredNavigationAppSettingManager newPreferredNavigationAppSettingManager(PackageManager packageManager, Lazy<PersistentStorage> lazy) {
        return new PreferredNavigationAppSettingManager(packageManager, lazy);
    }

    public static PreferredNavigationAppSettingManager provideInstance(Provider<PackageManager> provider, Provider<PersistentStorage> provider2) {
        return new PreferredNavigationAppSettingManager(provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreferredNavigationAppSettingManager mo10268get() {
        return provideInstance(this.packageManagerProvider, this.persistentStorageProvider);
    }
}
