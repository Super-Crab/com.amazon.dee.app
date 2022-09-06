package com.amazon.deecomms.messaging.tracking;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes12.dex */
public class ConversationMessageTracker {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationMessageTracker.class);
    private Map<String, ConversationInfo> conversationInfoMap;
    private final ConversationInfoStorage storage;

    public ConversationMessageTracker(@NonNull ConversationInfoStorage conversationInfoStorage) {
        this.storage = conversationInfoStorage;
    }

    private void ensureConversationInfoInitialized() {
        if (this.conversationInfoMap == null) {
            try {
                this.conversationInfoMap = this.storage.read();
            } catch (Exception e) {
                LOG.e("Error while initializing conversation info map.", e);
                this.conversationInfoMap = this.storage.createEmptyConversationInfoMap();
            }
        }
    }

    private void saveConversationInfo() {
        try {
            this.storage.write(this.conversationInfoMap);
        } catch (Exception e) {
            LOG.e("Error while saving conversation info map.", e);
        }
    }

    public synchronized ConversationInfo deleteConversation(@NonNull String str) {
        ConversationInfo remove;
        ensureConversationInfoInitialized();
        remove = this.conversationInfoMap.remove(str);
        if (remove != null) {
            saveConversationInfo();
        }
        return remove;
    }

    public Map<String, ConversationInfo> getConversationInfoMapFromMessage(@NonNull Message message) {
        String payloadAsString = message.getPayloadAsString();
        if (payloadAsString.isEmpty()) {
            return null;
        }
        return (Map) new Gson().fromJson(payloadAsString, new TypeToken<Map<String, ConversationInfo>>() { // from class: com.amazon.deecomms.messaging.tracking.ConversationMessageTracker.1
        }.getType());
    }

    public synchronized boolean hasUnreadMessages() {
        boolean z;
        ensureConversationInfoInitialized();
        Iterator<ConversationInfo> it2 = this.conversationInfoMap.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            ConversationInfo next = it2.next();
            if (next.lastReceivedMessageId > next.lastReadMessageId) {
                z = true;
                break;
            }
        }
        return z;
    }

    public synchronized void markMessagesAsRead(@NonNull String str, long j) {
        ensureConversationInfoInitialized();
        ConversationInfo conversationInfo = this.conversationInfoMap.get(str);
        if (conversationInfo != null) {
            conversationInfo.lastReadMessageId = j;
            saveConversationInfo();
        }
    }

    public synchronized void setConversationInfo(@NonNull Map<String, ConversationInfo> map) {
        this.conversationInfoMap = map;
        saveConversationInfo();
    }
}
