package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessory.repositories.cbl.CblRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.CblInformationResponse;
import com.amazon.alexa.accessoryclient.common.query.response.CblLoginStateResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientCblRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCblRepository;", "Lcom/amazon/alexa/accessory/repositories/cbl/CblRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "queryCblLoginState", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/alexa/accessory/protocol/Cbl$CblLoginState;", "requestCblInformation", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/protocol/Cbl$CblInformation;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientCblRepository implements CblRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientCblRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.cbl.CblRepository
    @NotNull
    public Observable<Cbl.CblLoginState> queryCblLoginState() {
        Observable<Cbl.CblLoginState> map = this.client.create(ApiIdentifier.QUERY_CBL_LOGIN_STATE, new QuerySessionRequest(this.identifier), CblLoginStateResponse.Transformer.INSTANCE).map(ClientCblRepository$queryCblLoginState$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…   .map { it.loginState }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.cbl.CblRepository
    @NotNull
    public Single<Cbl.CblInformation> requestCblInformation() {
        Single<Cbl.CblInformation> map = this.client.createSingle(ApiIdentifier.REQUEST_CBL_INFORMATION, new QuerySessionRequest(this.identifier), CblInformationResponse.Transformer.INSTANCE).map(ClientCblRepository$requestCblInformation$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…map { it.cblInformation }");
        return map;
    }
}
