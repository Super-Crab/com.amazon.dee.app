package com.amazon.alexa.accessory.speechapi.listeners;

import com.amazon.devicesetup.common.v1.WifiConnectionState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/StateListener;", "", "onAlexaStateChanged", "", "alexaState", "Lcom/amazon/alexa/accessory/speechapi/listeners/StateListener$AlexaState;", "AlexaState", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface StateListener {

    /* compiled from: StateListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/StateListener$AlexaState;", "", "(Ljava/lang/String;I)V", WifiConnectionState.IDLE, "PREPARING_TO_LISTEN", "LISTENING", "FINISHING_LISTENING", "THINKING", "SPEAKING", "ERROR", "UNKNOWN", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum AlexaState {
        IDLE,
        PREPARING_TO_LISTEN,
        LISTENING,
        FINISHING_LISTENING,
        THINKING,
        SPEAKING,
        ERROR,
        UNKNOWN
    }

    void onAlexaStateChanged(@NotNull AlexaState alexaState);
}
