package com.amazon.alexa.accessory.avsclient.context;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes.dex */
public interface SecurityStateSupplier {
    void activate();

    void deactivate();

    boolean isDeviceLocked();

    boolean isDeviceSecure();

    Observable<Boolean> queryDeviceLockedState();
}
