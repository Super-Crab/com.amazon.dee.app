package com.amazon.deecomms.nativemodules;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
/* loaded from: classes12.dex */
public class MessagingManagerBridge extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MessagingManagerBridge.class);
    private final ContactsDataStoreUtil mContactsDataStoreUtil;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessagingManagerBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        ContactsDataStoreUtil contactsDataStoreUtil = new ContactsDataStoreUtil(CommsDaggerWrapper.getComponent().getContext());
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
    }

    @ReactMethod
    public void deleteConversation(String str, Promise promise) {
        if (this.mContactsDataStoreUtil.deleteConversation(str) > 0) {
            promise.resolve(null);
            return;
        }
        LOG.e("deleteConversation! No matching conversation_id in Conversations table");
        promise.reject((String) null, "deleteConversation failed to delete on local DB");
    }

    @ReactMethod
    public void getConversationInfoForCommsId(String str, Promise promise) {
        if (str == null) {
            LOG.w("commsId null for fetching conversation info. no-op");
            promise.resolve(null);
            return;
        }
        promise.resolve(this.mContactsDataStoreUtil.getConversationInfoByRecipientCommsId(str));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsMessagingManager";
    }

    public MessagingManagerBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactsDataStoreUtil contactsDataStoreUtil) {
        super(reactApplicationContext);
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
    }
}
