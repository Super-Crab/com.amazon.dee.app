package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaMediaPlaybackMetadata;
import com.amazon.alexa.api.AlexaMediaPlaybackState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaMediaPlaybackListenerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListenerAdapter;", "Lcom/amazon/alexa/api/AlexaMediaPlaybackListener;", "compatListener", "Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;", "(Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;", "onMediaMetadata", "", "mediaPlaybackMetadata", "Lcom/amazon/alexa/api/AlexaMediaPlaybackMetadata;", "onMediaPlaybackStateUpdate", "mediaPlaybackState", "Lcom/amazon/alexa/api/AlexaMediaPlaybackState;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaMediaPlaybackListenerAdapter implements com.amazon.alexa.api.AlexaMediaPlaybackListener {
    @NotNull
    private final AlexaMediaPlaybackListener compatListener;

    public AlexaMediaPlaybackListenerAdapter(@NotNull AlexaMediaPlaybackListener compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlexaMediaPlaybackListener getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaMetadata(@NotNull AlexaMediaPlaybackMetadata mediaPlaybackMetadata) {
        Intrinsics.checkParameterIsNotNull(mediaPlaybackMetadata, "mediaPlaybackMetadata");
        this.compatListener.onMediaMetadata(mediaPlaybackMetadata);
    }

    @Override // com.amazon.alexa.api.AlexaMediaPlaybackListener
    public void onMediaPlaybackStateUpdate(@NotNull AlexaMediaPlaybackState mediaPlaybackState) {
        Intrinsics.checkParameterIsNotNull(mediaPlaybackState, "mediaPlaybackState");
        this.compatListener.onMediaPlaybackStateUpdate(mediaPlaybackState);
    }
}
