package com.amazon.comms.calling;

import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.BeginCallPayload;
import com.dee.app.metrics.MetricsService;
import java.util.Objects;
/* loaded from: classes10.dex */
public class CallPayloadValidator {
    private static final String COMMUNAL_URL_SUBSTRING = "sips:id.hg";
    private static final int DEFAULT_MAX_RETRY_COUNT = 5;
    public static final int NEED_REQUEST = 1;
    public static final int NOT_NEEDED = 0;
    public static final int TOO_MANY_REQUEST = -1;
    private final int maxRetryCount;
    private final MetricsService metricsService;
    private String previousCalleeId;
    private String previousCallerId;
    private int retryCount;

    @VisibleForTesting
    CallPayloadValidator(MetricsService metricsService, int i) {
        this.retryCount = 0;
        this.metricsService = metricsService;
        this.maxRetryCount = i;
    }

    private boolean isPersonalized(BeginCallPayload beginCallPayload) {
        BeginCallPayload.SipPayload sipPayload;
        BeginCallPayload.Agent agent;
        String str;
        BeginCallPayload.SipCommand sipCommand = beginCallPayload.sipCommand;
        return (sipCommand == null || (sipPayload = sipCommand.payload) == null || (agent = sipPayload.caller) == null || (str = agent.uri) == null || str.contains(COMMUNAL_URL_SUBSTRING)) ? false : true;
    }

    public void resetCounter() {
        this.previousCalleeId = null;
        this.previousCallerId = null;
        this.retryCount = 0;
    }

    public int shouldRequestCall(BeginCallPayload beginCallPayload) {
        if (isPersonalized(beginCallPayload)) {
            resetCounter();
            return 0;
        }
        this.metricsService.recordOccurrence("comms.call.beginCall.payloadCommunal", "Comms", true, null);
        BeginCallPayload.SipPayload sipPayload = beginCallPayload.sipCommand.payload;
        String str = sipPayload.caller.id;
        String str2 = sipPayload.callee.id;
        if (Objects.equals(str2, this.previousCalleeId) && Objects.equals(str, this.previousCallerId)) {
            this.retryCount++;
            if (this.retryCount >= this.maxRetryCount) {
                this.metricsService.recordOccurrence("comms.call.beginCall.abortRetry", "Comms", true, null);
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

    public CallPayloadValidator(MetricsService metricsService) {
        this(metricsService, 5);
    }
}
