package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessoryclient.common.api.AccessoryDataBeaconResult;
import com.amazon.alexa.accessoryclient.common.api.AccessoryInquiryResult;
import com.amazon.alexa.accessoryclient.common.api.AccessoryScanResult;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.IdentityRequest;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryDataBeaconResultResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryInquiryResultResponse;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryScanResultResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientAccessoryScanner.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientAccessoryScanner;", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessoryScanner;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "getClient", "()Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "observeOnBleAccessoryFoundNearby", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryScanResult;", "observeOnBleDataBeaconFoundNearby", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryDataBeaconResult;", "observeOnConnectedAccessoryFound", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryInquiryResult;", "observeOnConnectedAccessoryLost", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientAccessoryScanner implements AccessoryScanner {
    @NotNull
    private final RxIPCClient client;

    public ClientAccessoryScanner(@NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
    }

    @NotNull
    public final RxIPCClient getClient() {
        return this.client;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessoryScanner
    @NotNull
    public Observable<AccessoryScanResult> observeOnBleAccessoryFoundNearby() {
        Observable<AccessoryScanResult> map = this.client.create(ApiIdentifier.OBSERVE_BLE_ACCESSORY_FOUND_NEARBY, new IdentityRequest(), AccessoryScanResultResponse.Transformer.INSTANCE).map(ClientAccessoryScanner$observeOnBleAccessoryFoundNearby$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         … it.accessoryScanResult }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessoryScanner
    @NotNull
    public Observable<AccessoryDataBeaconResult> observeOnBleDataBeaconFoundNearby() {
        Observable<AccessoryDataBeaconResult> map = this.client.create(ApiIdentifier.OBSERVE_BLE_ACCESSORY_DATA_BEACON_FOUND_NEARBY, new IdentityRequest(), AccessoryDataBeaconResultResponse.Transformer.INSTANCE).map(ClientAccessoryScanner$observeOnBleDataBeaconFoundNearby$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         …cessoryDataBeaconResult }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessoryScanner
    @NotNull
    public Observable<AccessoryInquiryResult> observeOnConnectedAccessoryFound() {
        Observable<AccessoryInquiryResult> map = this.client.create(ApiIdentifier.OBSERVE_CONNECTED_ACCESSORY_FOUND, new IdentityRequest(), AccessoryInquiryResultResponse.Transformer.INSTANCE).map(ClientAccessoryScanner$observeOnConnectedAccessoryFound$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         ….accessoryInquiryResult }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.AccessoryScanner
    @NotNull
    public Observable<AccessoryInquiryResult> observeOnConnectedAccessoryLost() {
        Observable<AccessoryInquiryResult> map = this.client.create(ApiIdentifier.OBSERVE_CONNECTED_ACCESSORY_LOST, new IdentityRequest(), AccessoryInquiryResultResponse.Transformer.INSTANCE).map(ClientAccessoryScanner$observeOnConnectedAccessoryLost$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         ….accessoryInquiryResult }");
        return map;
    }
}
