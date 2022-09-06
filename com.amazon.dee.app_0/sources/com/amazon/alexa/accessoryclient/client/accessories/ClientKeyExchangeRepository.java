package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.response.BooleanResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientKeyExchangeRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientKeyExchangeRepository;", "Lcom/amazon/alexa/accessory/repositories/crypto/KeyExchangeRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "queryIsAwaitingDerivedKeys", "Lio/reactivex/rxjava3/core/Observable;", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientKeyExchangeRepository implements KeyExchangeRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientKeyExchangeRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.crypto.KeyExchangeRepository
    @NotNull
    public Observable<Boolean> queryIsAwaitingDerivedKeys() {
        Observable<Boolean> map = this.client.create(ApiIdentifier.QUERY_IS_AWAITING_DERIVED_KEYS, new QuerySessionRequest(this.identifier), BooleanResponse.Transformer.INSTANCE).map(ClientKeyExchangeRepository$queryIsAwaitingDerivedKeys$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…    .map { it.boolValue }");
        return map;
    }
}
