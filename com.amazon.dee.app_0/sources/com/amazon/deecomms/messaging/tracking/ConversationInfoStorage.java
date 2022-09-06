package com.amazon.deecomms.messaging.tracking;

import androidx.annotation.NonNull;
import java.util.Map;
/* loaded from: classes12.dex */
public interface ConversationInfoStorage {
    @NonNull
    Map<String, ConversationInfo> createEmptyConversationInfoMap();

    @NonNull
    Map<String, ConversationInfo> read();

    void write(Map<String, ConversationInfo> map);
}
