package com.amazon.alexa.auto.navigation;

import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import com.amazon.alexa.auto.navigation.PreferredNavigationAppSettingContentProvider;
import com.amazon.alexa.auto.storage.StorageModule;
import com.amazon.alexa.auto.storage.StorageModule_ProvideNavigationPreferenceStorageFactory;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DaggerPreferredNavigationAppSettingContentProvider_Injector implements PreferredNavigationAppSettingContentProvider.Injector {
    private Provider<PreferredNavigationAppSettingManager> preferredNavigationAppSettingManagerProvider;
    private Provider<Context> provideContextProvider;
    private Provider<PersistentStorage> provideNavigationPreferenceStorageProvider;
    private Provider<PackageManager> providePackageManagerProvider;
    private Provider<UriMatcher> providePreferredNavAppContentUriMatcherProvider;

    /* loaded from: classes6.dex */
    public static final class Builder {
        private PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule;
        private StorageModule storageModule;

        private Builder() {
        }

        public PreferredNavigationAppSettingContentProvider.Injector build() {
            Preconditions.checkBuilderRequirement(this.preferredNavigationAppContentProviderModule, PreferredNavigationAppContentProviderModule.class);
            if (this.storageModule == null) {
                this.storageModule = new StorageModule();
            }
            return new DaggerPreferredNavigationAppSettingContentProvider_Injector(this);
        }

        public Builder preferredNavigationAppContentProviderModule(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
            this.preferredNavigationAppContentProviderModule = (PreferredNavigationAppContentProviderModule) Preconditions.checkNotNull(preferredNavigationAppContentProviderModule);
            return this;
        }

        public Builder storageModule(StorageModule storageModule) {
            this.storageModule = (StorageModule) Preconditions.checkNotNull(storageModule);
            return this;
        }
    }

    private DaggerPreferredNavigationAppSettingContentProvider_Injector(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providePreferredNavAppContentUriMatcherProvider = DoubleCheck.provider(PreferredNavigationAppContentProviderModule_ProvidePreferredNavAppContentUriMatcherFactory.create(builder.preferredNavigationAppContentProviderModule));
        this.provideContextProvider = DoubleCheck.provider(PreferredNavigationAppContentProviderModule_ProvideContextFactory.create(builder.preferredNavigationAppContentProviderModule));
        this.providePackageManagerProvider = DoubleCheck.provider(PreferredNavigationAppContentProviderModule_ProvidePackageManagerFactory.create(builder.preferredNavigationAppContentProviderModule, this.provideContextProvider));
        this.provideNavigationPreferenceStorageProvider = DoubleCheck.provider(StorageModule_ProvideNavigationPreferenceStorageFactory.create(builder.storageModule, this.provideContextProvider));
        this.preferredNavigationAppSettingManagerProvider = DoubleCheck.provider(PreferredNavigationAppSettingManager_Factory.create(this.providePackageManagerProvider, this.provideNavigationPreferenceStorageProvider));
    }

    private PreferredNavigationAppSettingContentProvider injectPreferredNavigationAppSettingContentProvider(PreferredNavigationAppSettingContentProvider preferredNavigationAppSettingContentProvider) {
        PreferredNavigationAppSettingContentProvider_MembersInjector.injectUriMatcher(preferredNavigationAppSettingContentProvider, this.providePreferredNavAppContentUriMatcherProvider.mo10268get());
        PreferredNavigationAppSettingContentProvider_MembersInjector.injectPreferredNavAppManager(preferredNavigationAppSettingContentProvider, this.preferredNavigationAppSettingManagerProvider.mo10268get());
        return preferredNavigationAppSettingContentProvider;
    }

    @Override // com.amazon.alexa.auto.navigation.PreferredNavigationAppSettingContentProvider.Injector
    public void inject(PreferredNavigationAppSettingContentProvider preferredNavigationAppSettingContentProvider) {
        injectPreferredNavigationAppSettingContentProvider(preferredNavigationAppSettingContentProvider);
    }
}
