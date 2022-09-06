package com.amazon.deecomms.calling.model;

import androidx.annotation.NonNull;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class InitiateOutboundCallRequest {
    @JsonProperty("callMediaStreams")
    public List<String> callMediaStreams;
    @JsonProperty("callTarget")
    public CallTarget callTarget;
    @JsonProperty("clientMetricsInfo")
    public ClientMetricsInfo clientMetricsInfo;
    @JsonProperty("isDropIn")
    public boolean isDropIn;

    /* loaded from: classes12.dex */
    public static class CallTarget {
        @JsonProperty("deviceEndpoint")
        public String deviceEndpoint;
        @JsonProperty("groupId")
        public String groupId;
        @JsonProperty("groupIdentityType")
        public String groupIdentityType;
        @JsonProperty("targetCommsId")
        public String targetCommsId;
        @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE)
        public String type;
    }

    /* loaded from: classes12.dex */
    public static class ClientMetricsInfo {
        @JsonProperty("callInitiationTime")
        String callInitiationTime;

        public ClientMetricsInfo(@NonNull String str) {
            this.callInitiationTime = str;
        }
    }
}
