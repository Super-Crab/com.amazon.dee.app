package com.amazon.deecomms.messaging.sync;

import android.content.Context;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.messaging.model.Conversation;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import java.util.List;
/* loaded from: classes12.dex */
public class ConversationSyncHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationSyncHandler.class);
    private ConversationsDownloader mConversationsDownloader;
    private MessagingProviderWrapper mMessagingProvider;

    public ConversationSyncHandler(Context context, String str, String str2) {
        this.mConversationsDownloader = new ConversationsDownloader(str);
        this.mMessagingProvider = new MessagingProviderWrapper(context, str, str2);
    }

    public void syncConversations() {
        List<Conversation> conversationsDescending;
        long lastUpdatedServerTimeForConversations = this.mMessagingProvider.getLastUpdatedServerTimeForConversations();
        if (lastUpdatedServerTimeForConversations > 0) {
            conversationsDescending = this.mConversationsDownloader.getConversationsAscending(lastUpdatedServerTimeForConversations);
        } else {
            conversationsDescending = this.mConversationsDownloader.getConversationsDescending(0L);
        }
        if (conversationsDescending != null && conversationsDescending.size() > 0) {
            this.mMessagingProvider.syncConversations(conversationsDescending);
        } else {
            LOG.e("Conversation list to be updated is null, returning");
        }
    }
}
