package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.DeviceGroupRequest;
import com.amazon.alexa.accessoryclient.common.query.request.IdentityRequest;
import com.amazon.alexa.accessoryclient.common.query.request.LongRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.BooleanResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceGroupListResponse;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceGroupResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDeviceSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00100\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDeviceSupplier;", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceSupplierV2;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "addDeviceGroup", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceGroup;", "deviceGroup", "getDeviceGroup", "identifier", "", "hasDeviceGroup", "", "queryDeviceGroups", "Lio/reactivex/rxjava3/core/Observable;", "", "removeDeviceGroup", "Lio/reactivex/rxjava3/core/Completable;", "id", "", "updateDeviceGroup", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientDeviceSupplier implements DeviceSupplierV2 {
    private final RxIPCClient client;

    public ClientDeviceSupplier(@NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Single<DeviceGroup> addDeviceGroup(@NotNull DeviceGroup deviceGroup) {
        Intrinsics.checkParameterIsNotNull(deviceGroup, "deviceGroup");
        Single<DeviceGroup> map = this.client.createSingle(ApiIdentifier.ADD_DEVICE_GROUP, new DeviceGroupRequest(deviceGroup), DeviceGroupResponse.Transformer.INSTANCE).map(ClientDeviceSupplier$addDeviceGroup$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   … ).map { it.deviceGroup }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Single<DeviceGroup> getDeviceGroup(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Single<DeviceGroup> map = this.client.createSingle(ApiIdentifier.GET_DEVICE_GROUP, new QuerySessionRequest(identifier), DeviceGroupResponse.Transformer.INSTANCE).map(ClientDeviceSupplier$getDeviceGroup$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   … ).map { it.deviceGroup }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Single<Boolean> hasDeviceGroup(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Single<Boolean> map = this.client.createSingle(ApiIdentifier.HAS_DEVICE_GROUP, new QuerySessionRequest(identifier), BooleanResponse.Transformer.INSTANCE).map(ClientDeviceSupplier$hasDeviceGroup$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …   ).map { it.boolValue }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Observable<List<DeviceGroup>> queryDeviceGroups() {
        Observable<List<DeviceGroup>> map = this.client.create(ApiIdentifier.QUERY_DEVICE_GROUPS, new IdentityRequest(), DeviceGroupListResponse.Transformer.INSTANCE).map(ClientDeviceSupplier$queryDeviceGroups$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         …).map { it.deviceGroups }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Completable removeDeviceGroup(long j) {
        return this.client.createCompletable(ApiIdentifier.REMOVE_DEVICE_GROUP, new LongRequest(j));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Completable updateDeviceGroup(@NotNull DeviceGroup deviceGroup) {
        Intrinsics.checkParameterIsNotNull(deviceGroup, "deviceGroup");
        return this.client.createCompletable(ApiIdentifier.UPDATE_DEVICE_GROUP, new DeviceGroupRequest(deviceGroup));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    @NotNull
    public Completable removeDeviceGroup(@NotNull DeviceGroup deviceGroup) {
        Intrinsics.checkParameterIsNotNull(deviceGroup, "deviceGroup");
        return this.client.createCompletable(ApiIdentifier.REMOVE_DEVICE_GROUP_BY_DEVICE_GROUP, new DeviceGroupRequest(deviceGroup));
    }
}
