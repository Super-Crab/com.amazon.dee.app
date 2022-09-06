package com.bugsnag.android;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public interface Delivery {
    void deliver(@NonNull Report report, @NonNull Configuration configuration) throws DeliveryFailureException;

    void deliver(@NonNull SessionTrackingPayload sessionTrackingPayload, @NonNull Configuration configuration) throws DeliveryFailureException;
}
