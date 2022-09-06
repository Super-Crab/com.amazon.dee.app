package com.amazon.alexa.accessory.registration.deviceaccount;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public interface DeviceAccountStore {
    Completable delete(String str);

    Maybe<DeviceAccount> getDeviceAccount(String str, String str2);

    Maybe<DeviceAccount> getDeviceAccount(String str, String str2, String str3);

    Single<DeviceAccount> putDeviceAccount(String str, DeviceAccount deviceAccount);

    Observable<Map<String, Set<DeviceAccount>>> queryDeviceAccounts();
}
