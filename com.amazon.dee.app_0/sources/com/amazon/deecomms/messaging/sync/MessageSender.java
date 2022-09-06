package com.amazon.deecomms.messaging.sync;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.AsyncResponseCallback;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.TimePeriodHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.client.ClientConversation;
import com.amazon.deecomms.messaging.model.client.ClientMessage;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.messaging.model.payload.TextMessagePayload;
import com.amazon.deecomms.messaging.model.response.SendMessagesResponse;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.sqlcipher.database.SQLiteConstraintException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes12.dex */
public abstract class MessageSender {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessageSender.class);
    private AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback;
    private Context context;
    private ClientConversation currentConversation;
    private boolean isHgLoopback;
    private boolean isNewConversation;
    final String messageType;
    private MessagingProviderWrapper messagingProviderWrapper;
    private ACMSClient acmsClient = new ACMSClient(MetricKeys.OP_SEND_MESSAGES);
    private TimePeriodHelper timePeriodHelper = new TimePeriodHelper();
    private final CommsIdentityManager commsIdentityStoreMigrationHelper = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
    private IHttpClient.JSONConverter mJsonConverter = new JacksonJSONConverter();

    /* loaded from: classes12.dex */
    public static class IOExceptionWrapper extends MetricsHelper.IOExceptionWrapper {
        private final boolean isResend;

        public IOExceptionWrapper(@NonNull Throwable th, @Nullable String str, @Nullable Integer num, @Nullable Long l, boolean z) {
            super(th, str, num, l);
            this.isResend = z;
        }

        public boolean isResend() {
            return this.isResend;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageSender(Context context, AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback, String str, boolean z) {
        this.context = context;
        this.asyncResponseCallback = asyncResponseCallback;
        this.messageType = str;
        this.isHgLoopback = z;
        this.messagingProviderWrapper = new MessagingProviderWrapper(context, this.commsIdentityStoreMigrationHelper.getCommsId("MessageSender", false), this.commsIdentityStoreMigrationHelper.getHomeGroupId("MessageSender", false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addCommsItemIdToMetric(CommsMetric commsMetric, ClientMessage clientMessage) {
        Map<String, Object> metadata = commsMetric.getMetadata();
        metadata.put(MetricKeys.META_COMMS_ITEM_ID, clientMessage.getConversationId() + "/" + clientMessage.getMessageId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addMetadataToMetric(@NonNull CommsMetric commsMetric, @Nullable Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        commsMetric.getMetadata().putAll(map);
    }

    private void ensureClientMessageId(@NonNull ClientMessage clientMessage, @Nullable ContentValues contentValues) {
        if (clientMessage.getClientMessageId() == null) {
            clientMessage.setClientMessageId(UUID.randomUUID().toString());
            if (contentValues == null) {
                return;
            }
            MessagingProviderWrapper.addClientMessageId(clientMessage, contentValues);
        }
    }

    private String getFormattedCurrentDateTime(long j) {
        return ISODateTimeFormat.dateTime().print(new DateTime(j, DateTimeZone.UTC));
    }

    private <R> boolean handleResponse(@NonNull ClientMessage clientMessage, @NonNull R r) {
        if (r instanceof SendMessagesResponse) {
            SendMessagesResponse sendMessagesResponse = (SendMessagesResponse) r;
            if (isSendSuccess(sendMessagesResponse)) {
                handleSendSuccess(clientMessage, sendMessagesResponse);
                AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback = this.asyncResponseCallback;
                if (asyncResponseCallback == null) {
                    return true;
                }
                asyncResponseCallback.processSuccessfulResponse(sendMessagesResponse);
                return true;
            }
            LOG.e("Message sending failed");
            handleSendFailure(clientMessage, sendMessagesResponse);
            AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback2 = this.asyncResponseCallback;
            if (asyncResponseCallback2 != null) {
                asyncResponseCallback2.processFailure();
            }
            return false;
        }
        Exception exc = r instanceof Exception ? (Exception) r : new Exception("Unknown");
        LOG.e("Message sending failed", exc);
        handleSendFailure(clientMessage, exc);
        AsyncResponseCallback<SendMessagesResponse, Integer> asyncResponseCallback3 = this.asyncResponseCallback;
        if (asyncResponseCallback3 != null) {
            asyncResponseCallback3.processFailure();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSendSuccess(@Nullable SendMessagesResponse sendMessagesResponse) {
        return (sendMessagesResponse == null || sendMessagesResponse.getMessageIds() == null || sendMessagesResponse.getMessageIds().size() < 1) ? false : true;
    }

    private void updateConversationAndParticipants(ClientMessage clientMessage) {
        String senderCommsId = clientMessage.getSenderCommsId();
        if (this.isHgLoopback) {
            senderCommsId = this.commsIdentityStoreMigrationHelper.getHomeGroupId("updateConversationAndParticipants", false);
        }
        ClientConversation conversation = this.messagingProviderWrapper.getConversation(clientMessage.getConversationId(), clientMessage.getRecipientID(), senderCommsId);
        if (conversation != null) {
            this.isNewConversation = false;
            this.currentConversation = conversation;
            LOG.i("Updating existing conversation");
        } else {
            LOG.i("Creating new conversation");
            this.isNewConversation = true;
            this.currentConversation = new ClientConversation();
            this.currentConversation.setRecipientId(clientMessage.getRecipientID());
            this.currentConversation.setConversationId("");
            this.currentConversation.setLastMsgId(0L);
            this.currentConversation.setUnreadMsgCount(0);
            this.currentConversation.setUnreadNotificationCount(0);
            this.currentConversation.setSendAsCommsId(senderCommsId);
            this.currentConversation.setViewAsCommsId(senderCommsId);
            HashMap hashMap = new HashMap();
            hashMap.put(clientMessage.getRecipientID(), 0);
            hashMap.put(clientMessage.getSenderCommsId(), 0);
            this.currentConversation.setParticipants((String[]) hashMap.keySet().toArray(new String[hashMap.size()]));
            this.currentConversation.setLastReadStatusMap(hashMap);
        }
        this.currentConversation.setLastMsgType(this.messageType);
        this.currentConversation.setLastModifiedTimestamp(clientMessage.getTime());
        this.currentConversation.setLastMsg(clientMessage);
        MessagePayload payload = clientMessage.getPayload();
        if (payload != null) {
            this.currentConversation.setLastMsgStr(payload.getSummaryText(this.context));
        } else {
            this.currentConversation.setLastMsgStr(null);
        }
        this.currentConversation.setLastMsgSender(clientMessage.getSenderCommsId());
        if (this.isNewConversation) {
            LOG.d("Inserting new conversation into conversation and participants");
            this.currentConversation.setUniqueId(this.messagingProviderWrapper.insertClientConversationAndParticipants(this.currentConversation));
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("last_msg_type", this.currentConversation.getLastMsgType());
        contentValues.put("last_msg", this.currentConversation.getLastMsgStr());
        contentValues.put("last_msg_sender_id", this.currentConversation.getLastMsgSender());
        contentValues.put("last_modified_timestamp", Long.valueOf(this.timePeriodHelper.convertTimestampStringToMills(this.currentConversation.getLastModifiedTimestamp())));
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Updating conversation convId: ");
        outline1.append(this.currentConversation.getConversationId());
        commsLogger.d(outline1.toString());
        this.messagingProviderWrapper.updateClientConversationAndParticipants(this.currentConversation.getConversationId(), contentValues);
    }

    private void updateConversationPostSuccessResponse(SendMessagesResponse sendMessagesResponse) {
        ContentValues contentValues = new ContentValues();
        this.currentConversation.setConversationId(sendMessagesResponse.getConversationId());
        this.currentConversation.setLastModifiedTimestamp(sendMessagesResponse.getTime());
        this.currentConversation.setLastMsgId(sendMessagesResponse.getMessageIds().get(0).longValue());
        contentValues.put("conversation_id", this.currentConversation.getConversationId());
        contentValues.put("recipient_id", this.currentConversation.getRecipientId());
        contentValues.put("last_modified_timestamp", Long.valueOf(this.timePeriodHelper.convertTimestampStringToMills(this.currentConversation.getLastModifiedTimestamp())));
        contentValues.put("last_msg_id", Long.valueOf(this.currentConversation.getLastMsgId()));
        contentValues.put("last_sequence_id", Long.valueOf(this.currentConversation.getLastSequenceId()));
        if (this.isNewConversation) {
            LOG.i("Updating the participant list for a new conversation");
            contentValues.put(MessagingProviderContract.Conversations.PARTICIPANTS_LIST, TextUtils.join(Constants.GROUP_CONCAT_SEPARATOR, this.currentConversation.getParticipants()));
            contentValues.put(MessagingProviderContract.Conversations.LAST_READ_STATUS_LIST, this.mJsonConverter.toJson(this.currentConversation.getLastReadStatusMap()));
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Updating the conversation and participants with unique Id: ");
        outline1.append(this.currentConversation.getUniqueId());
        commsLogger.i(outline1.toString());
        try {
            this.messagingProviderWrapper.updateClientConversationAndParticipants(this.currentConversation.getUniqueId(), contentValues);
        } catch (SQLiteConstraintException unused) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Constraint exception seen while updating. Deleting the row: ");
            outline12.append(this.currentConversation.getUniqueId());
            commsLogger2.i(outline12.toString());
            int deleteConversation = this.messagingProviderWrapper.deleteConversation(this.currentConversation.getUniqueId());
            if (deleteConversation >= 0) {
                return;
            }
            CommsLogger commsLogger3 = LOG;
            commsLogger3.w("Deleting duplicate conversation failed. Result: " + deleteConversation);
        }
    }

    public ClientMessage createDirectReplyTextMessage(String str, String str2) {
        ClientMessage clientMessage = new ClientMessage();
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        long currentTimeMillis = System.currentTimeMillis();
        TextMessagePayload textMessagePayload = new TextMessagePayload();
        textMessagePayload.setText(str);
        clientMessage.setConversationId(str2);
        clientMessage.setSenderCommsId(this.commsIdentityStoreMigrationHelper.getCommsId("MessageSender.sendDirectReply", false));
        clientMessage.setType(this.messageType);
        clientMessage.setMessageId(0L);
        clientMessage.setClientID(dateTime.getMillis());
        clientMessage.setRawTimestamp(currentTimeMillis);
        clientMessage.setTime(getFormattedCurrentDateTime(currentTimeMillis));
        clientMessage.setSyncStatus(2);
        clientMessage.setPayload(textMessagePayload);
        return clientMessage;
    }

    protected ClientMessage getDefaultClientMessage(ClientConversation clientConversation) {
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setConversationId(clientConversation.getConversationId());
        clientMessage.setRecipientID(clientConversation.getRecipientId());
        if (this.isHgLoopback) {
            clientMessage.setSenderCommsId(this.commsIdentityStoreMigrationHelper.getCommsId("MessageSender.getDefaultClientMessage", false));
        } else {
            clientMessage.setSenderCommsId(clientConversation.getSendAsCommsId());
        }
        clientMessage.setType(this.messageType);
        clientMessage.setMessageId(0L);
        clientMessage.setClientID(new DateTime(DateTimeZone.UTC).getMillis());
        long currentTimeMillis = System.currentTimeMillis();
        clientMessage.setRawTimestamp(currentTimeMillis);
        clientMessage.setTime(getFormattedCurrentDateTime(currentTimeMillis));
        clientMessage.setSyncStatus(2);
        return clientMessage;
    }

    protected abstract void handlePreProcessFailure(ClientMessage clientMessage);

    /* JADX INFO: Access modifiers changed from: protected */
    public <R> void handleSendFailure(@NonNull ClientMessage clientMessage, @NonNull R r) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 1);
        this.messagingProviderWrapper.updateClientMessage(clientMessage.getUniqueID(), contentValues);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleSendSuccess(@NonNull ClientMessage clientMessage, @NonNull SendMessagesResponse sendMessagesResponse) {
        updateConversationPostSuccessResponse(sendMessagesResponse);
        ContentValues contentValues = new ContentValues();
        contentValues.put("_time", Long.valueOf(this.timePeriodHelper.convertTimestampStringToMills(sendMessagesResponse.getTime())));
        contentValues.put("conversation_id", sendMessagesResponse.getConversationId());
        contentValues.put("client_id", (Integer) 0);
        contentValues.put("message_id", sendMessagesResponse.getMessageIds().get(0));
        contentValues.put("sync_status", (Integer) 0);
        MessagingProviderWrapper.addClientMessageId(clientMessage, contentValues);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Updating the message with uniqueId: ");
        outline1.append(clientMessage.getUniqueID());
        commsLogger.i(outline1.toString());
        try {
            this.messagingProviderWrapper.updateClientMessage(clientMessage.getUniqueID(), contentValues);
        } catch (SQLiteConstraintException unused) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Constraint exception seen while updating. Deleting the row:");
            outline12.append(clientMessage.getUniqueID());
            commsLogger2.w(outline12.toString());
            int deleteClientMessage = this.messagingProviderWrapper.deleteClientMessage(clientMessage.getUniqueID());
            if (deleteClientMessage >= 0) {
                return;
            }
            CommsLogger commsLogger3 = LOG;
            commsLogger3.w("Delete failed. Result" + deleteClientMessage);
        }
    }

    protected abstract boolean preProcess(ClientMessage clientMessage, String str);

    protected abstract boolean preProcessFailedMessage(ClientMessage clientMessage);

    /* JADX INFO: Access modifiers changed from: package-private */
    public <R> void recordSuccessAndFailMetrics(@NonNull ClientMessage clientMessage, @NonNull R r, @NonNull String str, @Nullable Map<String, Object> map) {
        boolean isResend;
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        if (r instanceof SendMessagesResponse) {
            isResend = ((SendMessagesResponse) r).isResend();
        } else {
            isResend = r instanceof IOExceptionWrapper ? ((IOExceptionWrapper) r).isResend() : false;
        }
        if (isResend) {
            hashMap.put("source", MetricKeys.VALUE_SOURCE_RETRY_SEND);
        } else {
            hashMap.put("source", MetricKeys.VALUE_SOURCE_FIRST_SEND);
        }
        hashMap.put(MetricKeys.META_COMMS_ITEM_ID, clientMessage.getConversationId() + "/" + clientMessage.getMessageId());
        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.ClickStream, str, r, hashMap);
    }

    public boolean resendFailedMessage(ClientMessage clientMessage) {
        ContentValues contentValues = new ContentValues();
        if (clientMessage.getClientMessageId() == null) {
            LOG.w("Retry message is missing client message id!");
            ensureClientMessageId(clientMessage, contentValues);
        }
        contentValues.put("sync_status", (Integer) 2);
        this.messagingProviderWrapper.updateClientMessage(clientMessage.getUniqueID(), contentValues);
        if (clientMessage.getPayload() == null) {
            LOG.i("Payload not present. Start preProcess hook to set the payload.");
            if (!preProcessFailedMessage(clientMessage)) {
                LOG.i("Calling pre-process failure while resending already failed message");
                handlePreProcessFailure(clientMessage);
                return false;
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("_payload", this.mJsonConverter.toJson(clientMessage.getPayload()));
            this.messagingProviderWrapper.updateClientMessage(clientMessage.getUniqueID(), contentValues2);
        }
        clientMessage.setSyncStatus(2);
        long currentTimeMillis = System.currentTimeMillis();
        clientMessage.setRawTimestamp(currentTimeMillis);
        clientMessage.setTime(getFormattedCurrentDateTime(currentTimeMillis));
        updateConversationAndParticipants(clientMessage);
        try {
            return handleResponse(clientMessage, sendMessage(clientMessage, true));
        } catch (IOException e) {
            return handleResponse(clientMessage, e);
        }
    }

    public boolean send(ClientConversation clientConversation, String str) {
        ClientMessage defaultClientMessage = getDefaultClientMessage(clientConversation);
        boolean preProcess = preProcess(defaultClientMessage, str);
        GeneratedOutlineSupport.outline5("Pre-process step returned: ", preProcess, LOG);
        updateConversationAndParticipants(defaultClientMessage);
        ensureClientMessageId(defaultClientMessage, null);
        defaultClientMessage.setUniqueID(this.messagingProviderWrapper.insertClientMessage(defaultClientMessage, clientConversation.getViewAsCommsId()));
        if (!preProcess) {
            LOG.i("Calling handlePreProcessFailure");
            handlePreProcessFailure(defaultClientMessage);
            return false;
        }
        try {
            return handleResponse(defaultClientMessage, sendMessage(defaultClientMessage, false));
        } catch (IOException e) {
            return handleResponse(defaultClientMessage, e);
        }
    }

    public Boolean sendDirectReply(final String str, final String str2) throws IOException, InterruptedException {
        final Boolean[] boolArr = new Boolean[1];
        Thread thread = new Thread() { // from class: com.amazon.deecomms.messaging.sync.MessageSender.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    boolArr[0] = Boolean.valueOf(MessageSender.this.isSendSuccess(MessageSender.this.sendMessage(MessageSender.this.createDirectReplyTextMessage(str2, str), false)));
                } catch (IOException e) {
                    boolArr[0] = false;
                    MessageSender.LOG.e("Failed while calling sendMessage", e);
                }
            }
        };
        thread.start();
        thread.join();
        return boolArr[0];
    }

    protected SendMessagesResponse sendMessage(@NonNull ClientMessage clientMessage, boolean z) throws IOException {
        String format = MessageFormat.format("/users/{0}/conversations/{1}/messages", clientMessage.getSenderCommsId(), !TextUtils.isEmpty(clientMessage.getConversationId()) ? clientMessage.getConversationId() : clientMessage.getRecipientID());
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Sending message, API Suffix: ");
        outline1.append(LOG.sensitive(format));
        commsLogger.i(outline1.toString());
        try {
            IHttpClient.Response mo3640execute = this.acmsClient.request(format).addQueryParameter("deviceType", DeviceInfo.getDeviceTypeWithDeviceProperties(this.context)).authenticatedAsCurrentCommsUser().postJson(new Message[]{clientMessage}).mo3640execute();
            SendMessagesResponse sendMessagesResponse = (SendMessagesResponse) mo3640execute.convert(SendMessagesResponse.class);
            sendMessagesResponse.setHttpStatusCode(Integer.valueOf(mo3640execute.code()));
            sendMessagesResponse.setResend(z);
            sendMessagesResponse.setRequestId(mo3640execute.getCall().getRequestId());
            return sendMessagesResponse;
        } catch (ServiceException e) {
            LOG.e("Send a message failed.", e);
            if (!(e.getCause() instanceof IOException)) {
                SendMessagesResponse sendMessagesResponse2 = new SendMessagesResponse();
                sendMessagesResponse2.setHttpStatusCode(e.getHttpResponseCode());
                sendMessagesResponse2.setResend(z);
                sendMessagesResponse2.setRequestId(e.getRequestId());
                return sendMessagesResponse2;
            }
            throw new IOExceptionWrapper(e.getCause(), e.getRequestId(), e.getHttpResponseCode(), e.getCallDuration(), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addMetadataToMetric(@NonNull CommsMetric commsMetric, @NonNull SendMessagesResponse sendMessagesResponse) {
        Map<String, Object> metadata = commsMetric.getMetadata();
        MetricsHelper.addResponseMetadata(metadata, sendMessagesResponse);
        if (sendMessagesResponse.isResend()) {
            metadata.put("source", MetricKeys.VALUE_SOURCE_RETRY_SEND);
        } else {
            metadata.put("source", MetricKeys.VALUE_SOURCE_FIRST_SEND);
        }
    }
}
