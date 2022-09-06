package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaAudioChannel;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaAudioPlaybackStatusListenerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListenerAdapter;", "Lcom/amazon/alexa/api/AlexaAudioPlaybackStatusListener;", "compatListener", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "(Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "onAudioPlaybackStatusChanged", "", "audioPlaybackStatus", "", "Lcom/amazon/alexa/api/AlexaAudioChannel;", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaAudioPlaybackStatusListenerAdapter implements com.amazon.alexa.api.AlexaAudioPlaybackStatusListener {
    @NotNull
    private final AlexaAudioPlaybackStatusListener compatListener;

    public AlexaAudioPlaybackStatusListenerAdapter(@NotNull AlexaAudioPlaybackStatusListener compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlexaAudioPlaybackStatusListener getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaAudioPlaybackStatusListener
    public void onAudioPlaybackStatusChanged(@NotNull Map<AlexaAudioChannel, Boolean> audioPlaybackStatus) {
        Intrinsics.checkParameterIsNotNull(audioPlaybackStatus, "audioPlaybackStatus");
        this.compatListener.onAudioPlaybackStatusChanged(audioPlaybackStatus);
    }
}
