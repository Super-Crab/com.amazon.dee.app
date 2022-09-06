package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.IdentityRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.BooleanResponse;
import com.amazon.alexa.accessoryclient.common.query.response.StringResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientCompanionDeviceModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCompanionDeviceModule;", "Lcom/amazon/alexa/accessoryclient/client/accessories/CompanionDeviceModule;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "isCompanionDevice", "Lio/reactivex/rxjava3/core/Single;", "", "address", "", "queryNewCompanionDevices", "Lio/reactivex/rxjava3/core/Observable;", "queryRemovedCompanionDevices", "removeCompanionDevice", "Lio/reactivex/rxjava3/core/Completable;", "requestCompanionDevice", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientCompanionDeviceModule implements CompanionDeviceModule {
    private final RxIPCClient client;

    public ClientCompanionDeviceModule(@NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.client = client;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.CompanionDeviceModule
    @NotNull
    public Single<Boolean> isCompanionDevice(@NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Single<Boolean> map = this.client.createSingle(ApiIdentifier.IS_COMPANION_DEVICE, new QuerySessionRequest(address), BooleanResponse.Transformer.INSTANCE).map(ClientCompanionDeviceModule$isCompanionDevice$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …   ).map { it.boolValue }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.CompanionDeviceModule
    @NotNull
    public Observable<String> queryNewCompanionDevices() {
        Observable<String> map = this.client.create(ApiIdentifier.QUERY_NEW_COMPANION_DEVICES, new IdentityRequest(), StringResponse.Transformer.INSTANCE).map(ClientCompanionDeviceModule$queryNewCompanionDevices$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         … ).map { it.stringValue }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.CompanionDeviceModule
    @NotNull
    public Observable<String> queryRemovedCompanionDevices() {
        Observable<String> map = this.client.create(ApiIdentifier.QUERY_REMOVED_COMPANION_DEVICES, new IdentityRequest(), StringResponse.Transformer.INSTANCE).map(ClientCompanionDeviceModule$queryRemovedCompanionDevices$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         … ).map { it.stringValue }");
        return map;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.CompanionDeviceModule
    @NotNull
    public Completable removeCompanionDevice(@NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        return this.client.createCompletable(ApiIdentifier.REMOVE_COMPANION_DEVICE, new QuerySessionRequest(address));
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.CompanionDeviceModule
    @NotNull
    public Single<Boolean> requestCompanionDevice(@NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Single<Boolean> map = this.client.createSingle(ApiIdentifier.REQUEST_COMPANION_DEVICE, new QuerySessionRequest(address), BooleanResponse.Transformer.INSTANCE).map(ClientCompanionDeviceModule$requestCompanionDevice$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …   ).map { it.boolValue }");
        return map;
    }
}
