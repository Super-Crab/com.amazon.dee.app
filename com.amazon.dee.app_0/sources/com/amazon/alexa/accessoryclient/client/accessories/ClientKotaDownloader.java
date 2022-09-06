package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.kota.KotaJobSchedulerService;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.DownloadPackageRequest;
import com.amazon.alexa.accessoryclient.common.query.request.InventoryUpdateRequest;
import com.amazon.alexa.accessoryclient.common.query.request.UpdateRequestRequest;
import com.amazon.alexa.accessoryclient.common.query.response.InventoryUpdateResponse;
import com.amazon.alexa.accessoryclient.common.query.response.UpdateRequestResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientKotaDownloader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientKotaDownloader;", "Lcom/amazon/alexa/accessory/kota/KotaDownloader;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "downloadPackage", "Lio/reactivex/rxjava3/core/Completable;", KotaJobSchedulerService.UPDATE_REQUEST_KEY, "Lcom/amazon/alexa/accessory/kota/UpdateRequest;", KotaJobSchedulerService.INVENTORY_UPDATE_KEY, "Lcom/amazon/alexa/accessory/kota/InventoryUpdate;", "hardUpdate", "", "generateUpdateRequest", "Lio/reactivex/rxjava3/core/Single;", "deviceInformation", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "firmwareInformation", "Lcom/amazon/alexa/accessory/protocol/Firmware$FirmwareInformation;", "getAvailableInventoryUpdate", "Lio/reactivex/rxjava3/core/Maybe;", "force", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientKotaDownloader implements KotaDownloader {
    private final RxIPCClient client;

    public ClientKotaDownloader(@NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
    }

    @Override // com.amazon.alexa.accessory.kota.KotaDownloader
    @NotNull
    public Completable downloadPackage(@NotNull UpdateRequest updateRequest, @NotNull InventoryUpdate inventoryUpdate, boolean z) {
        Intrinsics.checkParameterIsNotNull(updateRequest, "updateRequest");
        Intrinsics.checkParameterIsNotNull(inventoryUpdate, "inventoryUpdate");
        return this.client.createCompletable(ApiIdentifier.DOWNLOAD_PACKAGE_REQUEST, new DownloadPackageRequest(updateRequest, inventoryUpdate, z));
    }

    @Override // com.amazon.alexa.accessory.kota.KotaDownloader
    @NotNull
    public Single<UpdateRequest> generateUpdateRequest(@NotNull Device.DeviceInformation deviceInformation, @NotNull Firmware.FirmwareInformation firmwareInformation) {
        Intrinsics.checkParameterIsNotNull(deviceInformation, "deviceInformation");
        Intrinsics.checkParameterIsNotNull(firmwareInformation, "firmwareInformation");
        Single<UpdateRequest> map = this.client.createSingle(ApiIdentifier.GENERATE_UPDATE_REQUEST, new UpdateRequestRequest(deviceInformation, firmwareInformation), UpdateRequestResponse.Transformer.INSTANCE).map(ClientKotaDownloader$generateUpdateRequest$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   ….map { it.updateRequest }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.kota.KotaDownloader
    @NotNull
    public Maybe<InventoryUpdate> getAvailableInventoryUpdate(@NotNull UpdateRequest updateRequest, boolean z) {
        Intrinsics.checkParameterIsNotNull(updateRequest, "updateRequest");
        Maybe<InventoryUpdate> map = this.client.createMaybe(ApiIdentifier.GET_AVAILABLE_INVENTORY_UPDATE_REQUEST, new InventoryUpdateRequest(updateRequest, z), InventoryUpdateResponse.Transformer.INSTANCE).map(ClientKotaDownloader$getAvailableInventoryUpdate$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createMaybe(\n    …ap { it.inventoryUpdate }");
        return map;
    }
}
