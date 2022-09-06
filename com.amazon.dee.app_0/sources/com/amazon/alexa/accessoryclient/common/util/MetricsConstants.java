package com.amazon.alexa.accessoryclient.common.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/util/MetricsConstants;", "", "()V", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MetricsConstants {
    @NotNull
    public static final String CLIENT_CONNECTION = "AccessoryClientConnection";
    @NotNull
    public static final String CLIENT_CONNECTION_ACKED_WITHIN_60_SECONDS = "connectionAckedWithinSixtySeconds";
    @NotNull
    public static final String CLIENT_CONNECTION_BINDING_FAILED = "bindingFailed";
    @NotNull
    public static final String CLIENT_CONNECTION_ON_BINDING_DIED = "onBindingDied";
    @NotNull
    public static final String CLIENT_CONNECTION_ON_NULL_BINDING = "onNullBinding";
    @NotNull
    public static final String CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED = "onServiceDisconnected";
    @NotNull
    public static final String CLIENT_CONNECTION_RECONNECT_ATTEMPT = "reconnectAttempt";
    @NotNull
    public static final String CLIENT_CONNECTION_REQUEST = "connectionRequested";
    @NotNull
    public static final String CLIENT_CONNECTION_REQUEST_DISCONNECT = "disconnectRequested";
    @NotNull
    public static final String CLIENT_CONNECTION_SUCCESS = "connectionSuccess";
    @NotNull
    public static final String CLIENT_CONNECTION_UNBINDING_DISCONNECT_FAILED = "unbindingFailedDisconnect";
    @NotNull
    public static final String CLIENT_CONNECTION_UNBINDING_RECONNECT_FAILED = "unbindingFailedReconnect";
    @NotNull
    public static final String CLIENT_CONNECTION_UNEXPECTEDLY_DISCONNECTED_WITHIN_60_SECONDS = "connectionUnexpectedlyDisconnectedWithinSixtySeconds";
    @NotNull
    public static final String CLIENT_CONNECTION_UNSUPPORTED_VERSION = "clientVersionUnsupported";
    @NotNull
    public static final String CLIENT_OUTPUT_STREAM = "AccessoryClientOutputStream";
    @NotNull
    public static final String CLIENT_OUTPUT_STREAM_INITIALIZE_EXCEPTION = "outputStreamInitializationException";
    @NotNull
    public static final String CLIENT_OUTPUT_STREAM_WRITE_EXCEPTION = "outputStreamWriteException";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String SERVICE_CONNECTION = "AccessoryServiceConnection";
    @NotNull
    public static final String SERVICE_CONNECTION_DISCONNECT = "disconnectRequested";
    @NotNull
    public static final String SERVICE_CONNECTION_FAILED = "serviceConnectionFailed";
    @NotNull
    public static final String SERVICE_CONNECTION_INITIALIZE = "serviceConnectionInitialize";
    @NotNull
    public static final String SERVICE_CONNECTION_UNSUPPORTED_CLIENT = "clientVersionUnsupported";

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/util/MetricsConstants$Companion;", "", "()V", "CLIENT_CONNECTION", "", "CLIENT_CONNECTION_ACKED_WITHIN_60_SECONDS", "CLIENT_CONNECTION_BINDING_FAILED", "CLIENT_CONNECTION_ON_BINDING_DIED", "CLIENT_CONNECTION_ON_NULL_BINDING", "CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED", "CLIENT_CONNECTION_RECONNECT_ATTEMPT", "CLIENT_CONNECTION_REQUEST", "CLIENT_CONNECTION_REQUEST_DISCONNECT", "CLIENT_CONNECTION_SUCCESS", "CLIENT_CONNECTION_UNBINDING_DISCONNECT_FAILED", "CLIENT_CONNECTION_UNBINDING_RECONNECT_FAILED", "CLIENT_CONNECTION_UNEXPECTEDLY_DISCONNECTED_WITHIN_60_SECONDS", "CLIENT_CONNECTION_UNSUPPORTED_VERSION", "CLIENT_OUTPUT_STREAM", "CLIENT_OUTPUT_STREAM_INITIALIZE_EXCEPTION", "CLIENT_OUTPUT_STREAM_WRITE_EXCEPTION", "SERVICE_CONNECTION", "SERVICE_CONNECTION_DISCONNECT", "SERVICE_CONNECTION_FAILED", "SERVICE_CONNECTION_INITIALIZE", "SERVICE_CONNECTION_UNSUPPORTED_CLIENT", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
