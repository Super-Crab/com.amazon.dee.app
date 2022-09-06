package com.amazon.deecomms.messaging.sync;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.client.ClientConversation;
import com.amazon.deecomms.messaging.model.response.MarkMessageReadResponse;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.messaging.util.UnreadMessageCounter;
import com.amazon.deecomms.notifications.model.ReadReceiptPush;
import java.io.IOException;
import java.text.MessageFormat;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class MessageReadStatusMarkerService extends CommsJobIntentService {
    public static final String REMOTE_READ = "remote_read";
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsNotificationManager commsNotificationManager;
    private ACMSClient mAcmsClient;
    private JacksonJSONConverter mJsonConverter = new JacksonJSONConverter();
    private MessagingProviderWrapper mMessagingProviderWrapper;
    @Inject
    ProvisioningManager provisioningManager;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessageReadStatusMarkerService.class);
    private static final int JOB_ID = CommsJobIntentService.generateJobId(MessageReadStatusMarkerService.class);

    public MessageReadStatusMarkerService() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, MessageReadStatusMarkerService.class, JOB_ID, intent);
    }

    private void processLocalMarkRead(Bundle bundle) {
        LOG.d("Marking locally read message as read");
        String string = bundle.getString(Constants.BUNDLE_KEY_CONVERSATION_ID);
        String string2 = bundle.getString(Constants.BUNDLE_KEY_RECIPIENT_ID);
        String homeGroupId = CommsInternal.getInstance().getHomeGroupId();
        ClientConversation conversation = this.mMessagingProviderWrapper.getConversation(string, string2, null);
        if (conversation == null) {
            LOG.e("Failed to fetch valid conversation object from database.");
            return;
        }
        String conversationId = conversation.getConversationId();
        if ("".contentEquals(conversationId)) {
            return;
        }
        if (homeGroupId != null && homeGroupId.equalsIgnoreCase(conversation.getRecipientId()) && CommsInternal.getInstance().getCommsId().equalsIgnoreCase(conversation.getLastMsgSender())) {
            return;
        }
        long latestReadMessage = this.mMessagingProviderWrapper.getLatestReadMessage(conversationId);
        if (conversation.getUnreadMsgCount() <= 0 && (conversation.getLastReadMsgId() == -1 || conversation.getLastReadMsgId() >= latestReadMessage)) {
            LOG.i("No unread messages left to mark in the conversation.");
        } else if (latestReadMessage <= 0) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Unable to read messages db for max message ID, msgId: " + latestReadMessage);
        } else {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("unread_msg_count", (Integer) 0);
                if (!CommsDaggerWrapper.getComponent().getCommsConnectivityMonitor().isConnected()) {
                    contentValues.put("read_message_locally_only", (Integer) 1);
                    this.mMessagingProviderWrapper.updateClientConversationAndParticipants(conversation.getUniqueId(), contentValues);
                    LOG.e("There is no internet connection to make markMessageAsRead API call");
                    return;
                }
                MarkMessageReadResponse markMessageRead = markMessageRead(conversation.getViewAsCommsId(), conversationId, latestReadMessage);
                if (markMessageRead == null) {
                    contentValues.put("read_message_locally_only", (Integer) 1);
                    this.mMessagingProviderWrapper.updateClientConversationAndParticipants(conversation.getUniqueId(), contentValues);
                    LOG.e("There response from markMessageAsRead API call is null");
                    return;
                }
                if (latestReadMessage != markMessageRead.getMessageId()) {
                    LOG.e("Server returned a different ID during mark read message API call");
                }
                contentValues.put("last_read_msg_id", Long.valueOf(latestReadMessage));
                contentValues.put("read_message_locally_only", (Integer) 0);
                this.mMessagingProviderWrapper.updateClientConversationAndParticipants(conversation.getUniqueId(), contentValues);
            } catch (ServiceException e) {
                LOG.e("Error while attempting to mark message read status", e);
            }
        }
    }

    private void processRemoteMarkRead(Bundle bundle) {
        long j;
        ReadReceiptPush readReceiptPush = (ReadReceiptPush) this.mJsonConverter.fromJson(bundle.getString(Constants.AMP_KEY), ReadReceiptPush.class);
        ClientConversation conversation = this.mMessagingProviderWrapper.getConversation(readReceiptPush.getConversationId(), null, null);
        if (conversation == null) {
            LOG.e("Failed to fetch valid conversation object from database.");
        } else if (conversation.getUnreadMsgCount() == 0) {
            LOG.i("processRemoteMarkRead called for conversation with 0 messages unread");
        } else {
            long lastMsgId = conversation.getLastMsgId();
            if (readReceiptPush.getMessageId() < lastMsgId) {
                j = Math.min(lastMsgId - readReceiptPush.getMessageId(), conversation.getUnreadMsgCount());
                LOG.i("processRemoteMarkRead called for msgId previous to lastMsgId for conversation. Setting to " + j + " unread msgs.");
            } else {
                j = 0;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("unread_msg_count", Long.valueOf(j));
            this.mMessagingProviderWrapper.updateClientConversationAndParticipants(conversation.getUniqueId(), contentValues);
            if (j != 0) {
                return;
            }
            this.commsNotificationManager.removeNotificationMessageByConversationId(readReceiptPush.getConversationId());
        }
    }

    public MarkMessageReadResponse markMessageRead(String str, String str2, long j) throws ServiceException {
        try {
            IHttpClient.Response mo3640execute = this.mAcmsClient.request(MessageFormat.format("/users/{0}/conversations/{1}/messages", str, str2)).authenticatedAsCurrentCommsUser().addQueryParameter(AppUrl.PARAM_MOST_RECENT_MSG_ID, String.valueOf(j)).patch().mo3640execute();
            MarkMessageReadResponse markMessageReadResponse = (MarkMessageReadResponse) mo3640execute.convert(MarkMessageReadResponse.class);
            mo3640execute.close();
            return markMessageReadResponse;
        } catch (IOException e) {
            LOG.e("IO Exception while markMessageRead", e);
            return null;
        }
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mAcmsClient = new ACMSClient(MetricKeys.OP_MARK_ALL_MSG_READ);
        this.mMessagingProviderWrapper = new MessagingProviderWrapper(getApplicationContext(), CommsInternal.getInstance().getCommsId(), CommsInternal.getInstance().getHomeGroupId());
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        LOG.i("Read status updater triggered.");
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        if (!CommsProvisionStatus.DEPROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "MessageReadStatusMarkerService.onHandleWork", false)) && !this.provisioningManager.isDeprovisionInProgress()) {
            if (extras.getBoolean(REMOTE_READ, false)) {
                processRemoteMarkRead(extras);
            } else {
                processLocalMarkRead(extras);
            }
            new UnreadMessageCounter(getApplicationContext()).countUnreadMessagesAndNotify();
            return;
        }
        LOG.d("User is deprovisioned or in the progress of deprovision, ignore mark message as read intent");
    }
}
