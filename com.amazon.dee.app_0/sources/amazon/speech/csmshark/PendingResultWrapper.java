package amazon.speech.csmshark;

import android.content.BroadcastReceiver;
/* loaded from: classes.dex */
public class PendingResultWrapper {
    private final BroadcastReceiver.PendingResult mResult;

    public PendingResultWrapper(BroadcastReceiver.PendingResult pendingResult) {
        this.mResult = pendingResult;
    }

    public void finish() {
        this.mResult.finish();
    }

    public void setResultData(String str) {
        this.mResult.setResultData(str);
    }
}
