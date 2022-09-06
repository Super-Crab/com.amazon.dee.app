package com.amazon.alexa.accessory.registration;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public interface DeviceRegistrationStore {
    Maybe<DeviceRegistration> getDeviceRegistration(String str, DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier);

    Single<DeviceRegistration> putDeviceRegistration(String str, DeviceRegistration deviceRegistration);

    Observable<Map<String, Set<DeviceRegistration>>> queryRegistrations();

    Completable removeDeviceRegistration(String str, DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier);
}
