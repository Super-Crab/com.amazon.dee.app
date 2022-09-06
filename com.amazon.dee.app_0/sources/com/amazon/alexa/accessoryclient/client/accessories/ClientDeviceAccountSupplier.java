package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.DeviceAccountRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringRequest;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceAccountResponse;
import com.amazon.alexa.accessoryclient.common.query.response.StringResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDeviceAccountSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u0012\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDeviceAccountSupplier;", "Lcom/amazon/alexa/accessoryclient/client/accessories/DeviceAccountSupplier;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "deleteDeviceAccounts", "Lio/reactivex/rxjava3/core/Completable;", "directedCustomerId", "", "fetchAndStoreDeviceAccount", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/registration/deviceaccount/DeviceAccount;", "registration", "Lcom/amazon/alexa/accessory/registration/DeviceRegistration;", "getDeviceAccount", "deviceType", "deviceSerialNumber", "getDeviceIdentifier", "deviceAccountId", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientDeviceAccountSupplier implements DeviceAccountSupplier {
    private final RxIPCClient client;

    public ClientDeviceAccountSupplier(@NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.DeviceAccountSupplier
    @NotNull
    public Completable deleteDeviceAccounts(@NotNull String directedCustomerId) {
        Intrinsics.checkParameterIsNotNull(directedCustomerId, "directedCustomerId");
        return this.client.createCompletable(ApiIdentifier.DELETE_DEVICE_ACCOUNTS, new QuerySessionRequest(directedCustomerId));
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.DeviceAccountSupplier
    @NotNull
    public Single<DeviceAccount> fetchAndStoreDeviceAccount(@NotNull DeviceRegistration registration) {
        Intrinsics.checkParameterIsNotNull(registration, "registration");
        Single<DeviceAccount> map = this.client.createSingle(ApiIdentifier.FETCH_AND_STORE_DEVICE_ACCOUNT, new DeviceAccountRequest(registration), DeviceAccountResponse.Transformer.INSTANCE).map(ClientDeviceAccountSupplier$fetchAndStoreDeviceAccount$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI….map { it.deviceAccount }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.DeviceAccountSupplier
    @NotNull
    public Single<DeviceAccount> getDeviceAccount(@NotNull String deviceType, @NotNull String deviceSerialNumber) {
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        Intrinsics.checkParameterIsNotNull(deviceSerialNumber, "deviceSerialNumber");
        Single<DeviceAccount> map = this.client.createSingle(ApiIdentifier.GET_DEVICE_ACCOUNT, new QuerySessionStringRequest(deviceType, deviceSerialNumber), DeviceAccountResponse.Transformer.INSTANCE).map(ClientDeviceAccountSupplier$getDeviceAccount$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI….map { it.deviceAccount }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.DeviceAccountSupplier
    @NotNull
    public Single<String> getDeviceIdentifier(@NotNull String deviceAccountId) {
        Intrinsics.checkParameterIsNotNull(deviceAccountId, "deviceAccountId");
        Single<String> map = this.client.createSingle(ApiIdentifier.GET_DEVICE_IDENTIFIER, new QuerySessionRequest(deviceAccountId), StringResponse.Transformer.INSTANCE).map(ClientDeviceAccountSupplier$getDeviceIdentifier$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…  .map { it.stringValue }");
        return map;
    }
}
