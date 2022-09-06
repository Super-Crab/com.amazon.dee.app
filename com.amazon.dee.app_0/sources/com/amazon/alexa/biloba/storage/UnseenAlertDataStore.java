package com.amazon.alexa.biloba.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.model.AlertNotificationData;
import java.util.Set;
/* loaded from: classes6.dex */
public class UnseenAlertDataStore implements AlertNotificationDataStore {
    @Override // com.amazon.alexa.biloba.storage.AlertNotificationDataStore
    public void clearAlertNotificationDataStore() {
    }

    @Override // com.amazon.alexa.biloba.storage.AlertNotificationDataStore
    public void deleteAlertData(@NonNull AlertNotificationData alertNotificationData) {
    }

    @Override // com.amazon.alexa.biloba.storage.AlertNotificationDataStore
    @Nullable
    public Set<AlertNotificationData> getAlertData() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.storage.AlertNotificationDataStore
    public void putAlertData(@NonNull AlertNotificationData alertNotificationData) {
    }
}
