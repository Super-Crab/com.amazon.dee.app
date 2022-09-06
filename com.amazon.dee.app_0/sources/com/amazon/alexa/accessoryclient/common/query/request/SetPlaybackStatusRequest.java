package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.PlaybackStatusTransformer;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SetPlaybackStatusRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/SetPlaybackStatusRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", VoiceBridgePayloadUtil.PayloadKey.MEDIA_PLAYER_STATUS, "Lcom/amazon/alexa/accessory/protocol/Media$PlaybackStatus;", "(Ljava/lang/String;Lcom/amazon/alexa/accessory/protocol/Media$PlaybackStatus;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "getPlaybackStatus", "()Lcom/amazon/alexa/accessory/protocol/Media$PlaybackStatus;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SetPlaybackStatusRequest implements Query.Request<SetPlaybackStatusRequest> {
    @NotNull
    private final BundleTransformer<SetPlaybackStatusRequest> bundleTransformer;
    @NotNull
    private final String identifier;
    @NotNull
    private final Media.PlaybackStatus playbackStatus;

    /* compiled from: SetPlaybackStatusRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/SetPlaybackStatusRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/SetPlaybackStatusRequest;", "()V", "IDENTIFIER_KEY", "", "PLAYBACK_STATUS_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "setPlaybackStatusRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<SetPlaybackStatusRequest> {
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();
        private static final String PLAYBACK_STATUS_KEY = "playback_status";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public SetPlaybackStatusRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            PlaybackStatusTransformer playbackStatusTransformer = PlaybackStatusTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(PLAYBACK_STATUS_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(PLAYBACK_STATUS_KEY)!!");
            return new SetPlaybackStatusRequest(string, playbackStatusTransformer.mo568fromBundle(bundle2));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull SetPlaybackStatusRequest setPlaybackStatusRequest) {
            Intrinsics.checkParameterIsNotNull(setPlaybackStatusRequest, "setPlaybackStatusRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", setPlaybackStatusRequest.getIdentifier());
            bundle.putBundle(PLAYBACK_STATUS_KEY, PlaybackStatusTransformer.INSTANCE.toBundle(setPlaybackStatusRequest.getPlaybackStatus()));
            return bundle;
        }
    }

    public SetPlaybackStatusRequest(@NotNull String identifier, @NotNull Media.PlaybackStatus playbackStatus) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(playbackStatus, "playbackStatus");
        this.identifier = identifier;
        this.playbackStatus = playbackStatus;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ SetPlaybackStatusRequest copy$default(SetPlaybackStatusRequest setPlaybackStatusRequest, String str, Media.PlaybackStatus playbackStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            str = setPlaybackStatusRequest.identifier;
        }
        if ((i & 2) != 0) {
            playbackStatus = setPlaybackStatusRequest.playbackStatus;
        }
        return setPlaybackStatusRequest.copy(str, playbackStatus);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    @NotNull
    public final Media.PlaybackStatus component2() {
        return this.playbackStatus;
    }

    @NotNull
    public final SetPlaybackStatusRequest copy(@NotNull String identifier, @NotNull Media.PlaybackStatus playbackStatus) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(playbackStatus, "playbackStatus");
        return new SetPlaybackStatusRequest(identifier, playbackStatus);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SetPlaybackStatusRequest)) {
                return false;
            }
            SetPlaybackStatusRequest setPlaybackStatusRequest = (SetPlaybackStatusRequest) obj;
            return Intrinsics.areEqual(this.identifier, setPlaybackStatusRequest.identifier) && Intrinsics.areEqual(this.playbackStatus, setPlaybackStatusRequest.playbackStatus);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<SetPlaybackStatusRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    @NotNull
    public final Media.PlaybackStatus getPlaybackStatus() {
        return this.playbackStatus;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Media.PlaybackStatus playbackStatus = this.playbackStatus;
        if (playbackStatus != null) {
            i = playbackStatus.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetPlaybackStatusRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", playbackStatus=");
        outline107.append(this.playbackStatus);
        outline107.append(")");
        return outline107.toString();
    }
}
