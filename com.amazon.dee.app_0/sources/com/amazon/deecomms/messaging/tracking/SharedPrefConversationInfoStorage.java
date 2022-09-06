package com.amazon.deecomms.messaging.tracking;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.deecomms.common.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class SharedPrefConversationInfoStorage implements ConversationInfoStorage {
    private final String CONVERSATION_MESSAGES_INFO_PREF_NAME = "CONVERSATION_MESSAGES_INFO";
    private final Context context;

    public SharedPrefConversationInfoStorage(@NonNull Context context) {
        this.context = context;
    }

    private static Map<String, ConversationInfo> parseConversationInfo(@NonNull String str) {
        return (Map) new Gson().fromJson(str, new TypeToken<Map<String, ConversationInfo>>() { // from class: com.amazon.deecomms.messaging.tracking.SharedPrefConversationInfoStorage.1
        }.getType());
    }

    @Override // com.amazon.deecomms.messaging.tracking.ConversationInfoStorage
    @NonNull
    public Map<String, ConversationInfo> createEmptyConversationInfoMap() {
        return new HashMap();
    }

    @Override // com.amazon.deecomms.messaging.tracking.ConversationInfoStorage
    @NonNull
    public Map<String, ConversationInfo> read() {
        String stringPreferenceFromSharedPrefs = Utils.getStringPreferenceFromSharedPrefs(this.context, "CONVERSATION_MESSAGES_INFO", null);
        if (stringPreferenceFromSharedPrefs == null) {
            return createEmptyConversationInfoMap();
        }
        return parseConversationInfo(stringPreferenceFromSharedPrefs);
    }

    @Override // com.amazon.deecomms.messaging.tracking.ConversationInfoStorage
    public void write(@NonNull Map<String, ConversationInfo> map) {
        Utils.writeStringPreferenceToSharedPrefs(this.context, "CONVERSATION_MESSAGES_INFO", new Gson().toJson(map));
    }
}
