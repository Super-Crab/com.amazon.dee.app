package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public interface RegistrationSupplier {
    Completable deregister(String str);

    Single<DeviceRegistration> getDeviceRegistration(String str);

    Single<DeviceRegistration> getOrCreateDeviceRegistration(AccessorySession accessorySession);

    Observable<Set<DeviceRegistration>> queryRegistrations();

    Completable retainRegistrations(List<DeviceGroup> list);
}
