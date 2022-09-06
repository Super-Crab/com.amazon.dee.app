package com.amazon.deecomms.calling.incallexperiences.actions;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.incallexperiences.actions.runnables.TriggerActionRunnable;
import com.amazon.deecomms.calling.incallexperiences.model.TriggerActionResponse;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.TriggerAction;
/* loaded from: classes12.dex */
public class TriggerInCallApplicationAction extends AsyncTask<Void, Void, TriggerActionResponse.TriggerStatusCode> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TriggerInCallApplicationAction.class);
    private String actionId;
    private String callId;
    private String localParticipantID;
    private TriggerActionRunnable runnable;
    private TriggerAction triggerAction;

    public TriggerInCallApplicationAction(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull TriggerActionRunnable triggerActionRunnable) {
        this.localParticipantID = str;
        this.callId = str2;
        this.actionId = str3;
        this.runnable = triggerActionRunnable;
        this.triggerAction = new TriggerAction();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public TriggerActionResponse.TriggerStatusCode doInBackground(Void... voidArr) {
        TriggerActionResponse triggerActionResponse;
        try {
            triggerActionResponse = this.triggerAction.triggerAction(this.localParticipantID, this.callId, this.actionId);
        } catch (ServiceException unused) {
            LOG.e("Error occurred while retrieving available actions for in-call experiences");
            triggerActionResponse = null;
        }
        return triggerActionResponse != null ? triggerActionResponse.getTriggerStatusCode() : TriggerActionResponse.TriggerStatusCode.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(TriggerActionResponse.TriggerStatusCode triggerStatusCode) {
        this.runnable.run(triggerStatusCode);
    }

    @VisibleForTesting
    public TriggerInCallApplicationAction(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull TriggerActionRunnable triggerActionRunnable, @NonNull TriggerAction triggerAction) {
        this.localParticipantID = str;
        this.callId = str2;
        this.actionId = str3;
        this.runnable = triggerActionRunnable;
        this.triggerAction = triggerAction;
    }
}
