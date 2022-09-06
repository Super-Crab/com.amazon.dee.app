package com.amazon.alexa.accessory.speechapi.listeners;

import com.amazon.alexa.smarthomecameras.constants.ErrorConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ConnectionListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\nJ\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener;", "", "onConnected", "", "onConnectingFailed", "connectingFailedReason", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener$ConnectingFailedReason;", "message", "", "onDisconnected", "ConnectingFailedReason", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface ConnectionListener {

    /* compiled from: ConnectionListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener$ConnectingFailedReason;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "TIMEOUT", "NO_ALEXA_SERVICES_TO_CONNECT_TO", "NO_ALEXA_SERVICES_ACCOUNT_REGISTERED", "ALEXA_SERVICES_DISABLED", "CONNECTION_OBJECT_RELEASED", ErrorConstants.UNAUTHORIZED, "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ConnectingFailedReason {
        UNKNOWN,
        TIMEOUT,
        NO_ALEXA_SERVICES_TO_CONNECT_TO,
        NO_ALEXA_SERVICES_ACCOUNT_REGISTERED,
        ALEXA_SERVICES_DISABLED,
        CONNECTION_OBJECT_RELEASED,
        UNAUTHORIZED
    }

    void onConnected();

    void onConnectingFailed(@NotNull ConnectingFailedReason connectingFailedReason, @NotNull String str);

    void onDisconnected();
}
