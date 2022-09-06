package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface DeviceAccountSupplier {
    Maybe<DeviceAccount> awaitDeviceAccount(String str, String str2, String str3);

    Completable deleteDeviceAccounts(String str);

    Single<DeviceAccount> fetchAndStoreDeviceAccount(DeviceRegistration deviceRegistration);

    Single<DeviceAccount> getDeviceAccount(String str, String str2);

    Single<String> getDeviceIdentifier(String str);
}
