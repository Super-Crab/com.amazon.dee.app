package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.deecomms.calling.enums.AssistCspId;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import java.util.Objects;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CallPayloadValidator {
    private static final String COMMUNAL_URL_SUBSTRING = "sips:id.hg";
    private static final int DEFAULT_MAX_RETRY_COUNT = 5;
    public static final int INVALID = 1;
    private static final String METRICS_OWNER_IDENTIFIER = "ec2b8c3f-5736-4878-bf6f-70ee753a92b2";
    public static final int TOO_MANY_REQUESTS = -1;
    public static final int VALID = 0;
    private final int maxRetryCount;
    private final LazyComponent<Mobilytics> mobilytics;
    private String previousCalleeId;
    private String previousCallerId;
    private int retryCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    @VisibleForTesting
    public CallPayloadValidator(@NonNull LazyComponent<Mobilytics> lazyComponent, int i) {
        this.retryCount = 0;
        this.mobilytics = lazyComponent;
        this.maxRetryCount = i;
    }

    private boolean isAssistCall(@NonNull BeginCallPayload beginCallPayload) {
        BeginCallPayload.CspInfo cspInfo;
        BeginCallPayload.DisplayInfo displayInfo = beginCallPayload.displayInfo;
        if (displayInfo == null || (cspInfo = displayInfo.cspInfo) == null) {
            return false;
        }
        return AssistCspId.RAPID_RESPONSE.getCspId().equals(cspInfo.cspId);
    }

    private boolean isPersonalized(@NonNull BeginCallPayload beginCallPayload) {
        BeginCallPayload.SipPayload sipPayload;
        BeginCallPayload.Agent agent;
        String str;
        BeginCallPayload.SipCommand sipCommand = beginCallPayload.sipCommand;
        return (sipCommand == null || (sipPayload = sipCommand.payload) == null || (agent = sipPayload.caller) == null || (str = agent.uri) == null || str.contains(COMMUNAL_URL_SUBSTRING)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int isPayloadValid(@NonNull BeginCallPayload beginCallPayload) {
        if (!isPersonalized(beginCallPayload) && !isAssistCall(beginCallPayload)) {
            MetricsHelper.recordSingleOccurrenceOperational("comms.call.beginCall.payloadCommunal");
            BeginCallPayload.SipPayload sipPayload = beginCallPayload.sipCommand.payload;
            String str = sipPayload.caller.id;
            String str2 = sipPayload.callee.id;
            if (Objects.equals(str2, this.previousCalleeId) && Objects.equals(str, this.previousCallerId)) {
                this.retryCount++;
                if (this.retryCount >= this.maxRetryCount) {
                    MetricsHelper.recordSingleOccurrenceOperational("comms.call.beginCall.abortRetry");
                    resetCounter();
                    return -1;
                }
            } else {
                this.previousCalleeId = str2;
                this.previousCallerId = str;
                this.retryCount = 0;
            }
            return 1;
        }
        resetCounter();
        return 0;
    }

    public void resetCounter() {
        this.previousCalleeId = null;
        this.previousCallerId = null;
        this.retryCount = 0;
    }

    public CallPayloadValidator(@NonNull LazyComponent<Mobilytics> lazyComponent) {
        this(lazyComponent, 5);
    }
}
