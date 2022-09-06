package com.amazon.alexa.handsfree.notification;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class IntentActionParser {
    @VisibleForTesting
    static final String ACTION_ALEXA_CONNECTION_FAILED = "com.amazon.alexa.handsfree.notification.REPORT_ALEXA_CONNECTION_FAILURE";
    @VisibleForTesting
    static final String EXTRA_CONNECTION_FAILURE_REASON = "CONNECTION_FAILURE_REASON";
    @VisibleForTesting
    public static final String EXTRA_IS_UTTERANCE_ON_LOCK_SCREEN = "IS_UTTERANCE_ON_LOCK_SCREEN";
    private static final String UNSUPPORTED_UTTERANCE_FAIL = "Unsupported utterance fail";
    private final Intent mIntent;

    public IntentActionParser(@NonNull Intent intent) {
        this.mIntent = intent;
    }

    public String getUtteranceFailureReason() {
        return isUtteranceFailure() ? this.mIntent.getStringExtra(EXTRA_CONNECTION_FAILURE_REASON) : UNSUPPORTED_UTTERANCE_FAIL;
    }

    public boolean isUtteranceFailure() {
        return ACTION_ALEXA_CONNECTION_FAILED.equals(this.mIntent.getAction()) && this.mIntent.getStringExtra(EXTRA_CONNECTION_FAILURE_REASON) != null;
    }

    public boolean isUtteranceOnLockScreen() {
        return this.mIntent.getBooleanExtra(EXTRA_IS_UTTERANCE_ON_LOCK_SCREEN, false);
    }
}
