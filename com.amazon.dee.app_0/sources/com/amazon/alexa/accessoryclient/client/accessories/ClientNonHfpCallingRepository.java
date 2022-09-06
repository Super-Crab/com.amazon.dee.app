package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.CallInfo;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.CallInfoRequest;
import com.amazon.alexa.accessoryclient.common.query.response.ErrorCodeResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientNonHfpCallingRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientNonHfpCallingRepository;", "Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/NonHfpCallingRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "updateCallInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/protocol/Common$ErrorCode;", "callInfo", "Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/CallInfo;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientNonHfpCallingRepository implements NonHfpCallingRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientNonHfpCallingRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository
    @NotNull
    public Single<Common.ErrorCode> updateCallInfo(@NotNull CallInfo callInfo) {
        Intrinsics.checkParameterIsNotNull(callInfo, "callInfo");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.UPDATE_CALL_INFO, new CallInfoRequest(callInfo, this.identifier), ErrorCodeResponse.Transformer.INSTANCE).map(ClientNonHfpCallingRepository$updateCallInfo$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(UPDA…   ).map { it.errorCode }");
        return map;
    }
}
