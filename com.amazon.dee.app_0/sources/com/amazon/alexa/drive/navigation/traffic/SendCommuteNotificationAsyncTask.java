package com.amazon.alexa.drive.navigation.traffic;

import android.os.AsyncTask;
import android.util.Log;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import okhttp3.Response;
/* loaded from: classes7.dex */
public class SendCommuteNotificationAsyncTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String TAG = SendCommuteNotificationAsyncTask.class.getSimpleName();
    private final NavigationMetrics mNavigationMetrics;
    private final SendCommuteNotificationRequest mSendCommuteNotificationRequest;
    private final TrafficNotificationManager.TrafficNotificationRequestCallback mTrafficNotificationCallback;

    public SendCommuteNotificationAsyncTask(TrafficNotificationManager.TrafficNotificationRequestCallback trafficNotificationRequestCallback, SendCommuteNotificationRequest sendCommuteNotificationRequest, NavigationMetrics navigationMetrics) {
        this.mTrafficNotificationCallback = trafficNotificationRequestCallback;
        this.mSendCommuteNotificationRequest = sendCommuteNotificationRequest;
        this.mNavigationMetrics = navigationMetrics;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(Void... voidArr) {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            Log.i(TAG, "doInBackground ");
            Response execute = coralService.request(this.mSendCommuteNotificationRequest.toUrl()).get().asRaw().execute();
            String str = TAG;
            Log.i(str, "code is: " + execute.code() + "response is: " + execute.body().string());
            if (execute.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("doInBackground() | IOException: ", e, TAG);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        if (!bool.booleanValue()) {
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.SENDCOMMUTENOTIFICATION, "Failure");
            this.mTrafficNotificationCallback.onSendCommuteNotificationError();
            return;
        }
        this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.SENDCOMMUTENOTIFICATION, "Success");
    }
}
