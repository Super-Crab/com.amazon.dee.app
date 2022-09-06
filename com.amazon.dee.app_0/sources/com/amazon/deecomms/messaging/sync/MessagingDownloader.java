package com.amazon.deecomms.messaging.sync;

import android.content.ContentValues;
import android.content.Context;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.TimePeriodHelper;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.messaging.model.client.ClientConversation;
import com.amazon.deecomms.messaging.model.payload.MessagePayload;
import com.amazon.deecomms.messaging.model.response.GetMessagesResponse;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.messaging.util.UnreadMessageCounter;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class MessagingDownloader {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingDownloader.class);
    private Context mContext;
    private MessagingProviderWrapper mMessagingProvider;
    private String mRegisteredCommsId;
    private TimePeriodHelper mTimePeriodHelper = new TimePeriodHelper();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class GetMessagesCallback implements IHttpClient.Callback {
        private String mCommsId;
        private ClientConversation mConversation;

        public GetMessagesCallback(String str, ClientConversation clientConversation) {
            this.mCommsId = str;
            this.mConversation = clientConversation;
        }

        private List<Message> filterSupportedMessages(List<Message> list) {
            ArrayList arrayList = new ArrayList();
            for (Message message : list) {
                if (supportedMessageType(message)) {
                    arrayList.add(message);
                }
            }
            return arrayList;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private boolean supportedMessageType(Message message) {
            char c;
            String type = message.getType();
            switch (type.hashCode()) {
                case -1330413003:
                    if (type.equals("message/text")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 213629550:
                    if (type.equals("message/contact-invitation")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 849467995:
                    if (type.equals("event/missed-call")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 939518131:
                    if (type.equals("event/call")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1689780174:
                    if (type.equals("message/audio")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1846772201:
                    if (type.equals("message/contact-connection-success")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            return c == 0 || c == 1 || c == 2 || c == 3 || c == 4 || c == 5;
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onFailure(IHttpClient.Call call, ServiceException serviceException) {
            MessagingDownloader.LOG.e("Failure occurred while contacting service", serviceException);
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onResult(IHttpClient.Call call, IHttpClient.Response response) {
            try {
                String conversationId = this.mConversation.getConversationId();
                GetMessagesResponse getMessagesResponse = (GetMessagesResponse) response.convert(GetMessagesResponse.class);
                if (getMessagesResponse == null) {
                    MessagingDownloader.LOG.w("Successful response with empty body");
                    response.close();
                    return;
                }
                List<Message> list = getMessagesResponse.messages;
                if (list.isEmpty()) {
                    MessagingDownloader.LOG.w("No messages are returned in current batch for conversation");
                    response.close();
                    return;
                }
                int size = list.size();
                CommsLogger commsLogger = MessagingDownloader.LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Fetched messages, size: ");
                sb.append(size);
                commsLogger.i(sb.toString());
                if (size > 0) {
                    String recipientId = this.mConversation.getRecipientId();
                    long messageId = list.get(size - 1).getMessageId() + 1;
                    List<Message> filterSupportedMessages = filterSupportedMessages(list);
                    if (filterSupportedMessages.size() > 0) {
                        filterSupportedMessages.get(0);
                        MessagingDownloader.this.mMessagingProvider.insertMessages(filterSupportedMessages, recipientId, this.mCommsId);
                        MessagingDownloader.this.updateConversationWithLastMsg(conversationId, filterSupportedMessages.get(filterSupportedMessages.size() - 1), recipientId);
                    }
                    new UnreadMessageCounter(MessagingDownloader.this.mContext.getApplicationContext()).countUnreadMessagesAndNotify();
                    MessagingDownloader.this.downloadMessages(this.mCommsId, messageId, this.mConversation);
                }
                response.close();
            } catch (IOException e) {
                MessagingDownloader.LOG.e("Error occurred while closing the response object", e);
            }
        }
    }

    public MessagingDownloader(Context context, String str, String str2) {
        this.mContext = context;
        this.mRegisteredCommsId = str;
        this.mMessagingProvider = new MessagingProviderWrapper(context, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadMessages(String str, long j, ClientConversation clientConversation) {
        if (clientConversation.getServerLastMsgId() < j) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("All messages are synced for conversation: ");
            outline1.append(clientConversation.getConversationId());
            commsLogger.d(outline1.toString());
            return;
        }
        String format = MessageFormat.format("/users/{0}/conversations/{1}/messages", str, clientConversation.getConversationId());
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1(" API Suffix for getMessages ");
        outline12.append(LOG.sensitive(format));
        commsLogger2.d(outline12.toString());
        new ACMSClient(MetricKeys.OP_GET_MESSAGES).request(format).addQueryParameter("count", CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.MESSAGES_FETCH_BATCH_SIZE)).addQueryParameter(AppUrl.ACMS.QueryParam.Keys.MESSAGE_START_ID, Long.toString(j)).addQueryParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, AppUrl.ACMS.QueryParam.Values.ASCENDING).addQueryParameter("deviceType", DeviceInfo.getDeviceTypeWithDeviceProperties(this.mContext)).authenticatedAsCurrentCommsUser().get().enqueue(new GetMessagesCallback(str, clientConversation));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConversationWithLastMsg(String str, Message message, String str2) {
        ClientConversation conversation = this.mMessagingProvider.getConversation(str, str2, this.mRegisteredCommsId);
        if (conversation == null) {
            LOG.e("Conversation not found");
            return;
        }
        String time = message.getTime();
        ContentValues contentValues = new ContentValues();
        long convertTimestampStringToMills = this.mTimePeriodHelper.convertTimestampStringToMills(time);
        long convertTimestampStringToMills2 = this.mTimePeriodHelper.convertTimestampStringToMills(conversation.getLastModifiedTimestamp());
        long messageId = message.getMessageId();
        long sequenceId = message.getSequenceId();
        if (convertTimestampStringToMills2 < 0 || convertTimestampStringToMills2 < convertTimestampStringToMills) {
            MessagePayload payload = message.getPayload();
            if (payload != null) {
                String type = payload.getType();
                contentValues.put("last_msg", payload.getSummaryText(this.mContext));
                contentValues.put("last_msg_type", type);
            }
            contentValues.put("last_msg_id", Long.valueOf(messageId));
            contentValues.put("last_sequence_id", Long.valueOf(sequenceId));
            contentValues.put("last_modified_timestamp", Long.valueOf(convertTimestampStringToMills));
        }
        contentValues.put("last_sync_msg_id", Long.valueOf(messageId));
        this.mMessagingProvider.updateClientConversationAndParticipants(str, contentValues);
    }

    public void syncMessagesForConversationList(List<ClientConversation> list) {
        if (list != null && !list.isEmpty()) {
            GetOrCreateContact getOrCreateContact = new GetOrCreateContact("MessagingDownloader.syncMessagesForConversationList");
            for (ClientConversation clientConversation : list) {
                getOrCreateContact.getContactEntry(clientConversation.getRecipientId());
                downloadMessages(clientConversation.getViewAsCommsId(), clientConversation.getLastSyncMsgId() + 1, clientConversation);
            }
            return;
        }
        LOG.w("No conversations to fetch messages.");
    }
}
