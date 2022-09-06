package com.amazon.alexa.accessory.speechapi.listeners;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AudioPlaybackStatusListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\bJ\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener;", "", "onAudioPlaybackStatusChanged", "", "audioPlaybackStatus", "", "Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener$AudioType;", "", "AudioType", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AudioPlaybackStatusListener {

    /* compiled from: AudioPlaybackStatusListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener$AudioType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "IMPORTANT", "DIALOG", "CALLS", "ALERTS", "CONTENT", "UNKNOWN", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum AudioType {
        IMPORTANT(0),
        DIALOG(1),
        CALLS(2),
        ALERTS(3),
        CONTENT(4),
        UNKNOWN(5);
        
        private final int value;

        AudioType(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    void onAudioPlaybackStatusChanged(@NotNull Map<AudioType, Boolean> map);
}
