package com.amazon.alexa.voice.tta.typing;

import android.os.Handler;
/* loaded from: classes11.dex */
public class ConversationSessionTimer {
    private static final int CLEAR_MESSAGE_DELAY = 300000;
    private static final Handler HANDLER = new Handler();
    private static final String TAG = "ConversationSessionTimer";
    private Runnable clearMessageHistoryTask;

    public void initialize(Runnable runnable) {
        this.clearMessageHistoryTask = runnable;
        HANDLER.removeCallbacksAndMessages(null);
        HANDLER.postDelayed(this.clearMessageHistoryTask, 300000L);
    }

    public void resetTimer() {
        HANDLER.removeCallbacks(this.clearMessageHistoryTask);
        HANDLER.postDelayed(this.clearMessageHistoryTask, 300000L);
    }
}
