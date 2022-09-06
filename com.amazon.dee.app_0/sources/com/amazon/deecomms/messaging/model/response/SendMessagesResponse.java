package com.amazon.deecomms.messaging.model.response;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.common.metrics.MetricsStatsProvider;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class SendMessagesResponse implements MetricsStatsProvider {
    private Long callDuration;
    @JsonProperty("conversationId")
    private String conversationId;
    @NonNull
    private HttpStatusCodeFamily family = HttpStatusCodeFamily.UNKNOWN;
    private Integer httpStatusCode;
    private boolean isResend;
    @JsonProperty("messageIds")
    private List<Long> messageIds;
    private String requestId;
    @JsonProperty("time")
    private String time;

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Long getCallDuration() {
        return this.callDuration;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFailureCount() {
        return this.family.getFailureCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getFaultCount() {
        return this.family.getFaultCount();
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public Integer getHttpResponseCode() {
        return this.httpStatusCode;
    }

    public List<Long> getMessageIds() {
        return this.messageIds;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public String getRequestId() {
        return this.requestId;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getSuccessCount() {
        return this.family.getSuccessCount();
    }

    public String getTime() {
        return this.time;
    }

    @Override // com.amazon.deecomms.common.metrics.MetricsStatsProvider
    public int getUnknownCount() {
        return this.family.getUnknownCount();
    }

    public boolean isResend() {
        return this.isResend;
    }

    public void setCallDuration(Long l) {
        this.callDuration = l;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public void setHttpStatusCode(Integer num) {
        this.httpStatusCode = num;
        this.family = HttpStatusCodeFamily.familyFromStatusCode(num);
    }

    public void setMessageIds(List<Long> list) {
        this.messageIds = list;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setResend(boolean z) {
        this.isResend = z;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
