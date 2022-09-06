package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.storage.AlertNotificationDataStore;
import com.amazon.alexa.biloba.storage.UnseenAlertDataStore;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes6.dex */
public class StorageModule {
    @Provides
    public AlertNotificationDataStore provideAlertNotificationDataStore() {
        return new UnseenAlertDataStore();
    }
}
