package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareUpdateStatus;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.FirmwareInformationSetResponse;
import com.amazon.alexa.accessoryclient.common.query.response.FirmwareUpdateStatusResponse;
import com.amazon.alexa.accessoryclient.common.query.response.InventoryUpdateBundleSetResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientFirmwareRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0016J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientFirmwareRepository;", "Lcom/amazon/alexa/accessory/repositories/firmware/FirmwareRepositoryV2;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "initiateFirmwareTransfer", "Lio/reactivex/rxjava3/core/Completable;", "queryInformationSet", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/amazon/alexa/accessory/protocol/Firmware$FirmwareInformation;", "queryInventoryUpdateSet", "Lcom/amazon/alexa/accessory/kota/InventoryUpdateBundle;", "forceUpdateRequired", "", "queryUpdateStatus", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/amazon/alexa/accessory/repositories/firmware/FirmwareUpdateStatus;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientFirmwareRepository implements FirmwareRepositoryV2 {
    private final RxIPCClient client;
    private final String identifier;

    public ClientFirmwareRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    @NotNull
    public Completable initiateFirmwareTransfer() {
        return this.client.createCompletable(ApiIdentifier.INITIATE_FIRMWARE_TRANSFER, new QuerySessionRequest(this.identifier));
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    @NotNull
    public Single<Set<Firmware.FirmwareInformation>> queryInformationSet() {
        Single<Set<Firmware.FirmwareInformation>> map = this.client.createSingle(ApiIdentifier.QUERY_FIRMWARE_INFORMATION_SET, new QuerySessionRequest(this.identifier), FirmwareInformationSetResponse.Transformer.INSTANCE).map(ClientFirmwareRepository$queryInformationSet$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI….firmwareInformationSet }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    @NotNull
    public Single<Set<InventoryUpdateBundle>> queryInventoryUpdateSet(boolean z) {
        Single<Set<InventoryUpdateBundle>> map = this.client.createSingle(ApiIdentifier.QUERY_INVENTORY_UPDATE_SET, new QuerySessionBooleanRequest(this.identifier, z), InventoryUpdateBundleSetResponse.Transformer.INSTANCE).map(ClientFirmwareRepository$queryInventoryUpdateSet$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…nventoryUpdateBundleSet }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2
    @NotNull
    public Flowable<FirmwareUpdateStatus> queryUpdateStatus() {
        Flowable<FirmwareUpdateStatus> map = this.client.create(ApiIdentifier.QUERY_FIRMWARE_UPDATE_STATUS, new QuerySessionRequest(this.identifier), FirmwareUpdateStatusResponse.Transformer.INSTANCE).toFlowable(BackpressureStrategy.BUFFER).map(ClientFirmwareRepository$queryUpdateStatus$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…it.firmwareUpdateStatus }");
        return map;
    }
}
