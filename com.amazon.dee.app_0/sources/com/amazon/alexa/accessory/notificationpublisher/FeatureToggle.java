package com.amazon.alexa.accessory.notificationpublisher;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes.dex */
public interface FeatureToggle {
    Observable<Boolean> queryNotificationForwardingStatus();
}
