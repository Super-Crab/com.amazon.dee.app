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
public class GetCommuteDestinationAsyncTask extends AsyncTask<Void, Integer, String> {
    private static final String TAG = GetCommuteDestinationAsyncTask.class.getSimpleName();
    private final GetCommuteDestinationRequest mGetCommuteDestinationRequest;
    private final NavigationMetrics mNavigationMetrics;
    private final TrafficNotificationManager.TrafficNotificationRequestCallback mTrafficNotificationRequestCallback;

    public GetCommuteDestinationAsyncTask(TrafficNotificationManager.TrafficNotificationRequestCallback trafficNotificationRequestCallback, GetCommuteDestinationRequest getCommuteDestinationRequest, NavigationMetrics navigationMetrics) {
        this.mTrafficNotificationRequestCallback = trafficNotificationRequestCallback;
        this.mGetCommuteDestinationRequest = getCommuteDestinationRequest;
        this.mNavigationMetrics = navigationMetrics;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Void... voidArr) {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            Log.i(TAG, "doInBackground ");
            Response execute = coralService.request(this.mGetCommuteDestinationRequest.toUrl()).get().asRaw().execute();
            if (execute.isSuccessful()) {
                return execute.body().string();
            }
            String str = TAG;
            Log.e(str, "Error contacting server for commute destination: Error code: " + execute.code());
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
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.GETCOMMUTEDESTINATION, "Failure");
            return;
        }
        try {
            GetCommuteDestinationResponse mo1239create = GetCommuteDestinationResponse.FACTORY.mo1239create(new JSONObject(str));
            String str2 = TAG;
            Log.i(str2, "getCommuteDestinationResponse | " + mo1239create);
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.GETCOMMUTEDESTINATION, "Success");
            this.mTrafficNotificationRequestCallback.onCommuteDestinationReceived(mo1239create);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Exception occurred when processing response | e: ", e, TAG);
            this.mNavigationMetrics.logAwts(NavigationMetrics.AwtsApiInterface.GETCOMMUTEDESTINATION, "Failure");
        }
    }
}
