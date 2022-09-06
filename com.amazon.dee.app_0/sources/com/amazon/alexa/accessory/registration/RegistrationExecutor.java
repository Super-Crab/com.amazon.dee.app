package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface RegistrationExecutor {
    Completable deregister(DeviceDeregistrationRequest deviceDeregistrationRequest, User user);

    Completable disassociate(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, User user);

    Single<DeviceRegistration> register(DeviceRegistrationRequest deviceRegistrationRequest, User user);
}
