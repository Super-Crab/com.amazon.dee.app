package com.amazon.deecomms.calling.model;

import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class InitiateOutboundCallRequestBuilder {
    public List<String> callMediaStreams;
    public String deviceEndpoint;
    public String groupID;
    public String groupIDType;
    public boolean isDropin;
    public String targetCommsId;
    public String type;

    public InitiateOutboundCallRequestBuilder(List<String> list) {
        this.callMediaStreams = list;
    }

    public InitiateOutboundCallRequest build() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        InitiateOutboundCallRequest initiateOutboundCallRequest = new InitiateOutboundCallRequest();
        initiateOutboundCallRequest.callTarget = new InitiateOutboundCallRequest.CallTarget();
        InitiateOutboundCallRequest.CallTarget callTarget = initiateOutboundCallRequest.callTarget;
        callTarget.type = this.type;
        callTarget.groupId = this.groupID;
        callTarget.groupIdentityType = this.groupIDType;
        callTarget.targetCommsId = this.targetCommsId;
        callTarget.deviceEndpoint = this.deviceEndpoint;
        initiateOutboundCallRequest.isDropIn = this.isDropin;
        initiateOutboundCallRequest.callMediaStreams = this.callMediaStreams;
        initiateOutboundCallRequest.clientMetricsInfo = new InitiateOutboundCallRequest.ClientMetricsInfo(simpleDateFormat.format(new Date()));
        return initiateOutboundCallRequest;
    }

    public InitiateOutboundCallRequestBuilder withContactTarget(String str, boolean z) {
        this.type = "ContactTarget";
        this.isDropin = z;
        this.targetCommsId = str;
        return this;
    }

    public InitiateOutboundCallRequestBuilder withDeviceTarget(String str, boolean z) {
        this.type = "DeviceTarget";
        this.isDropin = z;
        this.deviceEndpoint = str;
        return this;
    }

    public InitiateOutboundCallRequestBuilder withGroupTarget(String str, String str2, boolean z) {
        this.type = "GroupTarget";
        this.groupIDType = str;
        this.groupID = str2;
        this.isDropin = z;
        return this;
    }
}
