package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.repositories.media.MediaRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.MediaControlRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.SetPlaybackStatusRequest;
import com.amazon.alexa.accessoryclient.common.query.response.MediaControlResponse;
import com.amazon.alexa.accessoryclient.common.query.response.RegisterForMediaEventsResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientMediaRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientMediaRepository;", "Lcom/amazon/alexa/accessory/repositories/media/MediaRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "issueMediaControl", "Lio/reactivex/rxjava3/core/Completable;", "mediaControl", "Lcom/amazon/alexa/accessory/protocol/Media$MediaControl;", "queryMediaControls", "Lio/reactivex/rxjava3/core/Observable;", "queryRegisterForMediaEvents", "Lcom/amazon/alexa/accessory/protocol/Media$RegisterForMediaEvents;", "setPlaybackStatus", "status", "Lcom/amazon/alexa/accessory/protocol/Media$PlaybackStatus;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientMediaRepository implements MediaRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientMediaRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    @NotNull
    public Completable issueMediaControl(@NotNull Media.MediaControl mediaControl) {
        Intrinsics.checkParameterIsNotNull(mediaControl, "mediaControl");
        return this.client.createCompletable(ApiIdentifier.ISSUE_MEDIA_CONTROL, new MediaControlRequest(this.identifier, mediaControl));
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    @NotNull
    public Observable<Media.MediaControl> queryMediaControls() {
        Observable<Media.MediaControl> map = this.client.create(ApiIdentifier.QUERY_MEDIA_CONTROL, new QuerySessionRequest(this.identifier), MediaControlResponse.Transformer.INSTANCE).map(ClientMediaRepository$queryMediaControls$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         …).map { it.mediaControl }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    @NotNull
    public Observable<Media.RegisterForMediaEvents> queryRegisterForMediaEvents() {
        Observable<Media.RegisterForMediaEvents> map = this.client.create(ApiIdentifier.QUERY_REGISTER_FOR_MEDIA_EVENTS, new QuerySessionRequest(this.identifier), RegisterForMediaEventsResponse.Transformer.INSTANCE).map(ClientMediaRepository$queryRegisterForMediaEvents$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(\n         … { it.registerForEvents }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.media.MediaRepository
    @NotNull
    public Completable setPlaybackStatus(@NotNull Media.PlaybackStatus status) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        return this.client.createCompletable(ApiIdentifier.SET_PLAYBACK_STATUS, new SetPlaybackStatusRequest(this.identifier, status));
    }
}
