package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public final class UnavailableRegistrationSupplier implements RegistrationSupplier {
    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Completable deregister(String str) {
        return Completable.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Single<DeviceRegistration> getDeviceRegistration(String str) {
        return Single.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Single<DeviceRegistration> getOrCreateDeviceRegistration(AccessorySession accessorySession) {
        return Single.error(new UnsupportedOperationException("Unavailable!"));
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Observable<Set<DeviceRegistration>> queryRegistrations() {
        return Observable.never();
    }

    @Override // com.amazon.alexa.accessory.registration.RegistrationSupplier
    public Completable retainRegistrations(List<DeviceGroup> list) {
        return Completable.complete();
    }
}
