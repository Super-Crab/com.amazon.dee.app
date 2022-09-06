package com.amazon.alexa.voiceui.chrome;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
/* loaded from: classes11.dex */
public class DefaultTypingPrimerDisplayer implements TypingPrimerDisplayer {
    @VisibleForTesting
    static final String ACTION_SHOW_TYPE_UI = "amazon.intent.action.SHOW_ALEXA_TYPE_UI";
    @VisibleForTesting
    static final String EXTRA_REFERRER = "referer";
    @VisibleForTesting
    static final String EXTRA_START_TIMESTAMP = "startTimestamp";
    @VisibleForTesting
    static final String REFERRER_SCRIM = "scrim";
    private static final String TAG = "DefaultTypingPrimerDisplayer";
    private final Activity activity;

    public DefaultTypingPrimerDisplayer(Activity activity) {
        this.activity = activity;
    }

    @VisibleForTesting
    long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer
    public void showTypingView() {
        Intent intent = new Intent();
        intent.setPackage(this.activity.getPackageName());
        intent.setAction(ACTION_SHOW_TYPE_UI);
        intent.addFlags(65536);
        intent.putExtra("referer", REFERRER_SCRIM);
        intent.putExtra("startTimestamp", getCurrentTimeMillis());
        try {
            this.activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Unable to show UI. Could not find UI Activity for typing", e);
        }
    }
}
