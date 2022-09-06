package com.amazon.deecomms.calling.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DirectiveReceiverActivity extends AppCompatActivity {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DirectiveReceiverActivity.class);
    @Inject
    ConversationService commsConversationService;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate(bundle);
        LOG.d("Received FireOS Directive broadcast inside DirectiveReceiverActivity");
        passDirective(getIntent(), this.commsConversationService);
    }

    @VisibleForTesting
    void passDirective(Intent intent, ConversationService conversationService) {
        if (intent != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            String string = extras.getString("name");
            String string2 = extras.getString("namespace");
            String string3 = extras.getString("payload");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                LOG.i("Passing payload to commsConversationService");
                conversationService.passDirectivePayload(string, string2, string3);
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.CALL_DIRECTIVE_RECEIVER);
            }
        }
        finish();
    }
}
