package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.storage.AlertNotificationDataStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class StorageModule_ProvideAlertNotificationDataStoreFactory implements Factory<AlertNotificationDataStore> {
    private final StorageModule module;

    public StorageModule_ProvideAlertNotificationDataStoreFactory(StorageModule storageModule) {
        this.module = storageModule;
    }

    public static StorageModule_ProvideAlertNotificationDataStoreFactory create(StorageModule storageModule) {
        return new StorageModule_ProvideAlertNotificationDataStoreFactory(storageModule);
    }

    public static AlertNotificationDataStore provideInstance(StorageModule storageModule) {
        return proxyProvideAlertNotificationDataStore(storageModule);
    }

    public static AlertNotificationDataStore proxyProvideAlertNotificationDataStore(StorageModule storageModule) {
        return (AlertNotificationDataStore) Preconditions.checkNotNull(storageModule.provideAlertNotificationDataStore(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertNotificationDataStore mo10268get() {
        return provideInstance(this.module);
    }
}
