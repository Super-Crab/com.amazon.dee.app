package com.amazon.alexa.accessoryclient.common.api;

import com.amazon.devicesetupservice.reporting.NetworkState;
import kotlin.Metadata;
/* compiled from: ConnectionStatus.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/api/ConnectionStatus;", "", "(Ljava/lang/String;I)V", "NONEXISTENT", "DISCONNECTED", NetworkState.CONNECTING, "CONNECTED", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum ConnectionStatus {
    NONEXISTENT,
    DISCONNECTED,
    CONNECTING,
    CONNECTED
}
