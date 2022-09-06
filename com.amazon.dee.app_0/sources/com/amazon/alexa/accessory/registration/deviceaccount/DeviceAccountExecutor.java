package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface DeviceAccountExecutor {
    Single<DeviceAccount> fetchDeviceAccount(DeviceAccountRequest deviceAccountRequest, User user);
}
