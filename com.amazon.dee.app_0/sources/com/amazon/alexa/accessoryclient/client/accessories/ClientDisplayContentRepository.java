package com.amazon.alexa.accessoryclient.client.accessories;

import amazon.speech.model.DirectiveIntent;
import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDisplayContentRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDisplayContentRepository;", "Lcom/amazon/alexa/accessory/repositories/display/DisplayContentRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "setDisplayContentByChunks", "Lio/reactivex/rxjava3/core/Completable;", "renderData", "type", "Lcom/amazon/alexa/accessory/protocol/Cardrendering$DisplayContentType;", DirectiveIntent.INTENT_KEY_SEQUENCE_ID, "", "subSequenceId", "maxChunkSize", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientDisplayContentRepository implements DisplayContentRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientDisplayContentRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.display.DisplayContentRepository
    @NotNull
    public Completable setDisplayContentByChunks(@NotNull String renderData, @NotNull Cardrendering.DisplayContentType type, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(renderData, "renderData");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Completable error = Completable.error(new UnsupportedOperationException("setDisplayContentByChunks is not supported for IPC client."));
        Intrinsics.checkExpressionValueIsNotNull(error, "Completable.error(\n     …r IPC client.\")\n        )");
        return error;
    }
}
