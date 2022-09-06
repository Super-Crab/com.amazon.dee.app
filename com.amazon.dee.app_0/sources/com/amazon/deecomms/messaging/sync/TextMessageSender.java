package com.amazon.deecomms.messaging.sync;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.AsyncResponseCallback;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.payload.TextMessagePayload;
import com.amazon.deecomms.messaging.model.response.SendMessagesResponse;
/* loaded from: classes12.dex */
public class TextMessageSender extends MessageSender {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TextMessageSender.class);

    public TextMessageSender(Context context, AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback, boolean z) {
        super(context, asyncResponseCallback, "message/text", z);
    }

    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    protected void handlePreProcessFailure(ClientMessage clientMessage) {
        LOG.e("PreProcess failed which is not expected!!!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    public <R> void handleSendFailure(@NonNull ClientMessage clientMessage, @NonNull R r) {
        super.handleSendFailure(clientMessage, r);
        recordSuccessAndFailMetrics(clientMessage, r, MetricKeys.MSG_TXT_SENT_ROOT, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    public void handleSendSuccess(@NonNull ClientMessage clientMessage, @NonNull SendMessagesResponse sendMessagesResponse) {
        super.handleSendSuccess(clientMessage, sendMessagesResponse);
        recordSuccessAndFailMetrics(clientMessage, sendMessagesResponse, MetricKeys.MSG_TXT_SENT_ROOT, null);
        TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.MSG_TXT_SENT_LATENCY);
        MessageSender.addCommsItemIdToMetric(generateClickstream, clientMessage);
        MessageSender.addMetadataToMetric(generateClickstream, sendMessagesResponse);
        MetricsHelper.stopTimerMetric(generateClickstream);
    }

    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    protected boolean preProcess(ClientMessage clientMessage, String str) {
        TextMessagePayload textMessagePayload = new TextMessagePayload();
        textMessagePayload.setText(str);
        clientMessage.setPayload(textMessagePayload);
        TimerMetric generateClickstream = TimerMetric.generateClickstream(MetricKeys.MSG_TXT_SENT_LATENCY);
        MessageSender.addCommsItemIdToMetric(generateClickstream, clientMessage);
        MetricsHelper.startTimerMetric(generateClickstream);
        return true;
    }

    @Override // com.amazon.deecomms.messaging.sync.MessageSender
    protected boolean preProcessFailedMessage(ClientMessage clientMessage) {
        return true;
    }
}
