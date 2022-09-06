package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeviceAccountSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH&J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u000f\u001a\u00020\u0005H&¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/DeviceAccountSupplier;", "", "deleteDeviceAccounts", "Lio/reactivex/rxjava3/core/Completable;", "directedCustomerId", "", "fetchAndStoreDeviceAccount", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/registration/deviceaccount/DeviceAccount;", "registration", "Lcom/amazon/alexa/accessory/registration/DeviceRegistration;", "getDeviceAccount", "deviceType", "deviceSerialNumber", "getDeviceIdentifier", "deviceAccountId", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface DeviceAccountSupplier {
    @NotNull
    Completable deleteDeviceAccounts(@NotNull String str);

    @NotNull
    Single<DeviceAccount> fetchAndStoreDeviceAccount(@NotNull DeviceRegistration deviceRegistration);

    @NotNull
    Single<DeviceAccount> getDeviceAccount(@NotNull String str, @NotNull String str2);

    @NotNull
    Single<String> getDeviceIdentifier(@NotNull String str);
}
