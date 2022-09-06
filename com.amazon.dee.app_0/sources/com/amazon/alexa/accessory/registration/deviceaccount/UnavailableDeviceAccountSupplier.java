package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public class UnavailableDeviceAccountSupplier implements DeviceAccountSupplier {
    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Maybe<DeviceAccount> awaitDeviceAccount(String str, String str2, String str3) {
        return Maybe.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Completable deleteDeviceAccounts(String str) {
        return Completable.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Single<DeviceAccount> fetchAndStoreDeviceAccount(DeviceRegistration deviceRegistration) {
        return Single.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Single<DeviceAccount> getDeviceAccount(String str, String str2) {
        return Single.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier
    public Single<String> getDeviceIdentifier(String str) {
        return Single.error(new UnsupportedOperationException("Unavailable!"));
    }
}
