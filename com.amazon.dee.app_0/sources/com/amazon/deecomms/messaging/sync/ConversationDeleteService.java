package com.amazon.deecomms.messaging.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.messaging.model.client.ClientConversation;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.notifications.model.ConversationDeletePush;
/* loaded from: classes12.dex */
public class ConversationDeleteService extends CommsJobIntentService {
    private JacksonJSONConverter jsonConverter = new JacksonJSONConverter();
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationDeleteService.class);
    private static final int JOB_ID = CommsJobIntentService.generateJobId(ConversationDeleteService.class);

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, ConversationDeleteService.class, JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            LOG.w("Got a Delete Conversation push but no Bundle data was included.");
            return;
        }
        String string = extras.getString(Constants.AMP_KEY);
        if (TextUtils.isEmpty(string)) {
            LOG.w("Got a Delete Conversation push but no amznMessage was included.");
            return;
        }
        ConversationDeletePush conversationDeletePush = (ConversationDeletePush) this.jsonConverter.fromJson(string, ConversationDeletePush.class);
        ClientConversation conversation = new MessagingProviderWrapper(getApplicationContext(), CommsInternal.getInstance().getCommsId(), CommsInternal.getInstance().getHomeGroupId()).getConversation(conversationDeletePush.getConversationId(), null, null);
        if (conversation == null) {
            return;
        }
        String homeGroupId = CommsInternal.getInstance().getHomeGroupId();
        String commsId = CommsInternal.getInstance().getCommsId();
        String userCommsId = conversationDeletePush.getUserCommsId();
        if ((commsId.equalsIgnoreCase(userCommsId) || homeGroupId.equalsIgnoreCase(userCommsId)) && homeGroupId.equalsIgnoreCase(userCommsId) && !conversation.getViewAsCommsId().equalsIgnoreCase(userCommsId)) {
        }
    }
}
