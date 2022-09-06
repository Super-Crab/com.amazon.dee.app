package com.amazon.alexa.sharing.repo.interfaces;

import com.amazon.alexa.sharing.repo.models.Result;
import com.amazon.alexa.sharing.repo.models.SortOrder;
import com.amazon.alexa.sharing.repo.models.acms.ACMSMessage;
/* loaded from: classes10.dex */
public interface Conversation {
    Conversation[] getAllConversations();

    String getConversationId(String[] strArr);

    ACMSMessage[] getMessagesForConversation(String str, String str2, int i, Boolean bool, SortOrder sortOrder);

    void openCommsHome(boolean z);

    void openConversation(String str);

    Result sendMessage(String str, String str2);
}
