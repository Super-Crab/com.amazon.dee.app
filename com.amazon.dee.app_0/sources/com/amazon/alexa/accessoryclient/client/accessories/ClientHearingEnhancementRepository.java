package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetAudiogramRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetMediaEnhancementCorrectionAmountRequest;
import com.amazon.alexa.accessoryclient.common.query.response.AudiogramResponse;
import com.amazon.alexa.accessoryclient.common.query.response.MediaEnhancementCorrectionAmountResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientHearingEnhancementRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientHearingEnhancementRepository;", "Lcom/amazon/alexa/accessory/repositories/hearing/HearingEnhancementRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "getAudiogram", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/protocol/Hearing$Audiogram;", "deviceId", "", "getMediaEnhancementCorrectionAmount", "Lcom/amazon/alexa/accessory/protocol/Hearing$MediaEnhancementCorrectionAmount;", "setAudiogram", "Lio/reactivex/rxjava3/core/Completable;", EventBusConstants.JSON_KEY_AUDIO_PROFILE, "setMediaEnhancementCorrectionAmount", "correctionAmount", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientHearingEnhancementRepository implements HearingEnhancementRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientHearingEnhancementRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    @NotNull
    public Single<Hearing.Audiogram> getAudiogram(int i) {
        Single<Hearing.Audiogram> map = this.client.createSingle(ApiIdentifier.GET_AUDIOGRAM, new QuerySessionIntRequest(this.identifier, i), AudiogramResponse.Transformer.INSTANCE).map(ClientHearingEnhancementRepository$getAudiogram$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …    .map { it.audiogram }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    @NotNull
    public Single<Hearing.MediaEnhancementCorrectionAmount> getMediaEnhancementCorrectionAmount(int i) {
        Single<Hearing.MediaEnhancementCorrectionAmount> map = this.client.createSingle(ApiIdentifier.GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT, new QuerySessionIntRequest(this.identifier, i), MediaEnhancementCorrectionAmountResponse.Transformer.INSTANCE).map(ClientHearingEnhancementRepository$getMediaEnhancementCorrectionAmount$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(\n   …ncementCorrectionAmount }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    @NotNull
    public Completable setAudiogram(@NotNull Hearing.Audiogram audiogram) {
        Intrinsics.checkParameterIsNotNull(audiogram, "audiogram");
        return this.client.createCompletable(ApiIdentifier.SET_AUDIOGRAM, new SetAudiogramRequest(this.identifier, audiogram));
    }

    @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository
    @NotNull
    public Completable setMediaEnhancementCorrectionAmount(@NotNull Hearing.MediaEnhancementCorrectionAmount correctionAmount) {
        Intrinsics.checkParameterIsNotNull(correctionAmount, "correctionAmount");
        return this.client.createCompletable(ApiIdentifier.SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT, new SetMediaEnhancementCorrectionAmountRequest(this.identifier, correctionAmount));
    }
}
