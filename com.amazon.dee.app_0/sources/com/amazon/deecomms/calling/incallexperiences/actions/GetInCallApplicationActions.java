package com.amazon.deecomms.calling.incallexperiences.actions;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.incallexperiences.actions.runnables.ApplicationActionsRunnable;
import com.amazon.deecomms.calling.incallexperiences.model.ApplicationAction;
import com.amazon.deecomms.calling.incallexperiences.model.GetAvailableActionsResponse;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetAvailableActions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class GetInCallApplicationActions extends AsyncTask<Void, Void, List<ApplicationAction>> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetInCallApplicationActions.class);
    private GetAvailableActions availableActionsRetriever;
    private String callId;
    private String localParticipantID;
    private ApplicationActionsRunnable runnable;

    public GetInCallApplicationActions(@NonNull String str, @NonNull String str2, @NonNull ApplicationActionsRunnable applicationActionsRunnable) {
        this.localParticipantID = str;
        this.callId = str2;
        this.runnable = applicationActionsRunnable;
        this.availableActionsRetriever = new GetAvailableActions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public List<ApplicationAction> doInBackground(Void... voidArr) {
        GetAvailableActionsResponse getAvailableActionsResponse;
        try {
            getAvailableActionsResponse = this.availableActionsRetriever.getAvailableActions(this.localParticipantID, this.callId);
        } catch (ServiceException unused) {
            LOG.e("Error occurred while retrieving available actions for in-call experiences");
            getAvailableActionsResponse = null;
        }
        return getAvailableActionsResponse != null ? getAvailableActionsResponse.getApplicationActions() : new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<ApplicationAction> list) {
        this.runnable.run(list);
    }

    @VisibleForTesting
    public GetInCallApplicationActions(@NonNull String str, @NonNull String str2, @NonNull ApplicationActionsRunnable applicationActionsRunnable, @NonNull GetAvailableActions getAvailableActions) {
        this.localParticipantID = str;
        this.callId = str2;
        this.runnable = applicationActionsRunnable;
        this.availableActionsRetriever = getAvailableActions;
    }
}
