package com.amazon.comms.calling.model;

import androidx.annotation.Nullable;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes10.dex */
public class InitiateOutboundCallRequest {
    public static final String MEDIA_STREAM_TYPE_AUDIO = "AUDIO";
    public static final String MEDIA_STREAM_TYPE_VIDEO = "VIDEO";
    public static final String TARGET_TYPE_CONTACT = "ContactTarget";
    public static final String TARGET_TYPE_DEVICE = "DeviceTarget";
    public static final String TARGET_TYPE_GROUP = "GroupTarget";
    public List<String> callMediaStreams;
    public CallTarget callTarget;
    public boolean isDropIn;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE)
    public String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    /* loaded from: classes10.dex */
    public static class CallTarget {
        @Nullable
        public String deviceEndpoint;
        @Nullable
        public String groupId;
        @Nullable
        public String groupIdentityType;
        @Nullable
        public String targetCommsId;
    }
}
