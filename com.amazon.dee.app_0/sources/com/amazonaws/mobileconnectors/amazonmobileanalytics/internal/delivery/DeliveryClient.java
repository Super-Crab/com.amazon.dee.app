package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.EventObserver;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent;
@Deprecated
/* loaded from: classes13.dex */
public interface DeliveryClient extends EventObserver {
    void attemptDelivery();

    void enqueueEventForDelivery(InternalEvent internalEvent);
}
