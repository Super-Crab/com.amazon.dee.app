package com.amazon.deecomms.conversation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class FireOSDirectiveHandlerService extends CommsJobIntentService {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FireOSDirectiveHandlerService.class);
    @Inject
    ConversationService commsConversationService;

    @Inject
    public FireOSDirectiveHandlerService() {
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        CommsDaggerWrapper.getComponent().inject(this);
    }

    @Override // androidx.core.app.JobIntentService
    public void onHandleWork(@NonNull Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        String string = extras.getString("name");
        String string2 = extras.getString("namespace");
        String string3 = extras.getString("payload");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return;
        }
        this.commsConversationService.passDirectivePayload(string, string2, string3);
    }

    public FireOSDirectiveHandlerService(ConversationService conversationService) {
        this.commsConversationService = conversationService;
    }
}
