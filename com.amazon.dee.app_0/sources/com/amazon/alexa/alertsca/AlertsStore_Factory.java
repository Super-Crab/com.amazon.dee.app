package com.amazon.alexa.alertsca;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsStore_Factory implements Factory<AlertsStore> {
    private final Provider<AlertsDatabaseHelper> alertsDatabaseHelperProvider;
    private final Provider<AssetDownloader> assetDownloaderProvider;

    public AlertsStore_Factory(Provider<AlertsDatabaseHelper> provider, Provider<AssetDownloader> provider2) {
        this.alertsDatabaseHelperProvider = provider;
        this.assetDownloaderProvider = provider2;
    }

    public static AlertsStore_Factory create(Provider<AlertsDatabaseHelper> provider, Provider<AssetDownloader> provider2) {
        return new AlertsStore_Factory(provider, provider2);
    }

    public static AlertsStore newAlertsStore(AlertsDatabaseHelper alertsDatabaseHelper, AssetDownloader assetDownloader) {
        return new AlertsStore(alertsDatabaseHelper, assetDownloader);
    }

    public static AlertsStore provideInstance(Provider<AlertsDatabaseHelper> provider, Provider<AssetDownloader> provider2) {
        return new AlertsStore(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsStore mo10268get() {
        return provideInstance(this.alertsDatabaseHelperProvider, this.assetDownloaderProvider);
    }
}
