package com.amazon.alexa.drive.navigation.traffic;

import android.os.AsyncTask;
import android.util.Log;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import okhttp3.Response;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class GetCommuteDetailsAsyncTask extends AsyncTask<Void, Integer, String> {
    private static final String TAG = GetCommuteDetailsAsyncTask.class.getSimpleName();
    private final GetCommuteDetailsRequest mGetCommuteDetailsRequest;
    private final NavigationMetrics mNavigationMetrics;
    private final TrafficNotificationManager.TrafficNotificationRequestCallback mTrafficNotificationRequestCallback;

    public GetCommuteDetailsAsyncTask(TrafficNotificationManager.TrafficNotificationRequestCallback trafficNotificationRequestCallback, GetCommuteDetailsRequest getCommuteDetailsRequest, NavigationMetrics navigationMetrics) {
        this.mTrafficNotificationRequestCallback = trafficNotificationRequestCallback;
        this.mGetCommuteDetailsRequest = getCommuteDetailsRequest;
        this.mNavigationMetrics = navigationMetrics;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Void... voidArr) {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            Log.i(TAG, "doInBackground ");
            Response execute = coralService.request(this.mGetCommuteDetailsRequest.toUrl()).get().asRaw().execute();
            if (execute.isSuccessful()) {
                return execute.body().string();
            }
            String str = TAG;
            Log.e(str, "Error contacting server for commute details: Error code: " + execute.code());
            return null;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("doInBackground() | IOException: ", e, TAG);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        if (str == null) {
            Log.e(TAG, "result string is null");
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.GETCOMMUTEDETAILS, "Failure");
            return;
        }
        try {
            GetCommuteDetailsResponse mo1239create = GetCommuteDetailsResponse.FACTORY.mo1239create(new JSONObject(str));
            String str2 = TAG;
            Log.i(str2, "getCommuteDestinationResponse | " + mo1239create);
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.GETCOMMUTEDETAILS, "Success");
            this.mTrafficNotificationRequestCallback.onCommuteDetailsReceived(mo1239create);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Error in getting commute details | e: ", e, TAG);
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.GETCOMMUTEDETAILS, "Failure");
        }
    }
}
