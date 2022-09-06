package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes6.dex */
public interface MobilyticsDeviceSupplier {
    Observable<AccessoryMobilyticsDevice> queryMobilyticsDevice();
}
