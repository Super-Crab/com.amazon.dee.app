package com.amazon.alexa.biloba.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.model.AlertNotificationData;
import java.util.Set;
/* loaded from: classes6.dex */
public interface AlertNotificationDataStore {
    void clearAlertNotificationDataStore();

    void deleteAlertData(@NonNull AlertNotificationData alertNotificationData);

    @Nullable
    Set<AlertNotificationData> getAlertData();

    void putAlertData(@NonNull AlertNotificationData alertNotificationData);
}
