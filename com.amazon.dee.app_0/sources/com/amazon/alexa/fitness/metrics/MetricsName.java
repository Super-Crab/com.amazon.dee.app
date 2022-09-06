package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.devicesetup.common.v1.Event;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsName;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsName {
    @NotNull
    public static final String ACTIVE = "Active";
    @NotNull
    public static final String ALREADY_CONNECTED = "AlreadyConnected";
    @NotNull
    public static final String ALREADY_EXISTS = "AlreadyExists";
    @NotNull
    public static final String ATTEMPT = "Attempt";
    @NotNull
    public static final String AUTOSTOP = "AutoStop";
    @NotNull
    public static final String AUTOSTOP_RECOVERY_STALE = "AutoStopStale";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DISCONNECT = "Disconnect";
    @NotNull
    public static final String FAILED_TO_PROCESS_DATA = "FailedToProcessData";
    @NotNull
    public static final String FAILED_TO_RECEIVE_DATA = "FailedToReceiveData";
    @NotNull
    public static final String FAILED_TO_SUBSCRIBE = "FailedToSubscribe";
    @NotNull
    public static final String FAILURE = "Failure";
    @NotNull
    public static final String FAILURE_LATENCY = "FailureLatency";
    @NotNull
    public static final String INVALID = "Invalid";
    @NotNull
    public static final String LATENCY = "Latency";
    @NotNull
    public static final String MISSING = "Missing";
    @NotNull
    public static final String NOT_ACTIVE = "NotActive";
    @NotNull
    public static final String NULL = "Null";
    @NotNull
    public static final String PAUSED = "Paused";
    @NotNull
    public static final String RELEASED = "Released";
    @NotNull
    public static final String SUCCESS = "Success";
    @NotNull
    public static final String SUCCESS_LATENCY = "SuccessLatency";

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsName$Companion;", "", "()V", "ACTIVE", "", "ALREADY_CONNECTED", "ALREADY_EXISTS", "ATTEMPT", "AUTOSTOP", "AUTOSTOP_RECOVERY_STALE", "DISCONNECT", "FAILED_TO_PROCESS_DATA", "FAILED_TO_RECEIVE_DATA", "FAILED_TO_SUBSCRIBE", Event.FAILURE, "FAILURE_LATENCY", "INVALID", "LATENCY", "MISSING", CommsStore.EmergencyHelplineStatus.NOT_ACTIVE, AppInformation.NULL, "PAUSED", "RELEASED", "SUCCESS", "SUCCESS_LATENCY", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
