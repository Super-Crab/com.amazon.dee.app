package com.amazon.deecomms.calling.impl;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.api.HistoricalCall;
import com.amazon.deecomms.calling.api.ResponseCallback;
import com.amazon.deecomms.calling.api.Result;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import java.util.List;
import org.joda.time.DateTimeConstants;
/* loaded from: classes12.dex */
public class CallHistoryRetriever extends AsyncTask<Void, Void, Void> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallHistoryRetriever.class);
    private final ResponseCallback<List<HistoricalCall>> callback;
    private final String commsID;
    private final ContactsDataStoreUtil contactDataStoreUtil;
    private final int numberOfEntries;

    public CallHistoryRetriever(int i, @NonNull ResponseCallback<List<HistoricalCall>> responseCallback, @NonNull String str, @NonNull ContactsDataStoreUtil contactsDataStoreUtil) {
        this.numberOfEntries = i;
        this.callback = responseCallback;
        this.commsID = str;
        this.contactDataStoreUtil = contactsDataStoreUtil;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        try {
            this.callback.onComplete(new Result.Success(new CallHistoryResponseMapper(new GetRecentCommunications().getRecentCommunications(this.commsID, this.numberOfEntries, DateTimeConstants.MINUTES_PER_DAY, new String[]{"OUTGOING_CALL", "OUTGOING_DROPIN", "INCOMING_CALL"}), this.commsID, this.contactDataStoreUtil).getCallHistory()));
            return null;
        } catch (Exception e) {
            LOG.e("Error occured while obtaining call history");
            this.callback.onComplete(new Result.Error(e));
            return null;
        }
    }
}
