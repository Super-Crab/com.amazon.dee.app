package com.amazon.alexa.smarthomecameras.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewCapabilityAgentConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes10.dex */
public class LiveViewPayload implements Serializable {
    @SerializedName("description")
    private String description;
    @SerializedName(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY)
    private String sessionId;
    @SerializedName("status")
    private LiveViewCapabilityAgentConstants.StopLiveViewStatuses status;
    @NonNull
    @SerializedName("target")
    private LiveViewTarget target;

    public LiveViewPayload(LiveViewTarget liveViewTarget) {
        this.target = liveViewTarget;
    }

    @VisibleForTesting
    public String getDescription() {
        return this.description;
    }

    @VisibleForTesting
    public String getSessionId() {
        return this.sessionId;
    }

    @VisibleForTesting
    public LiveViewCapabilityAgentConstants.StopLiveViewStatuses getStatus() {
        return this.status;
    }

    @VisibleForTesting
    public LiveViewTarget getTarget() {
        return this.target;
    }

    public LiveViewPayload(String str, LiveViewTarget liveViewTarget) {
        this.sessionId = str;
        this.target = liveViewTarget;
    }

    public LiveViewPayload(String str, LiveViewTarget liveViewTarget, LiveViewCapabilityAgentConstants.StopLiveViewStatuses stopLiveViewStatuses, String str2) {
        this.sessionId = str;
        this.target = liveViewTarget;
        this.status = stopLiveViewStatuses;
        this.description = str2;
    }
}
