package com.amazon.alexa.featureservice.recordTrigger;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.alexa.featureservice.util.JsonUtils;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.google.gson.JsonSyntaxException;
import dagger.Lazy;
import java.io.IOException;
import java.util.List;
import okhttp3.Response;
/* loaded from: classes7.dex */
public class RecordTriggerNetworkClient {
    private static final String TAG = "RecordTriggerNetworkClient";
    private final Lazy<CoralService> coralService;
    private final Listener listener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface Listener {
        void onFailure(List<RequestTreatment> list, int i, Exception exc);

        void onSuccess(@NonNull String str);
    }

    public RecordTriggerNetworkClient(@NonNull Lazy<CoralService> lazy, @NonNull Listener listener) {
        this.coralService = lazy;
        this.listener = listener;
    }

    @NonNull
    @VisibleForTesting
    CoralService.Callback<Response> createNetworkCallback(final List<RequestTreatment> list) {
        return new CoralService.Callback<Response>() { // from class: com.amazon.alexa.featureservice.recordTrigger.RecordTriggerNetworkClient.1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(CoralService.Call<Response> call, CoralServiceException coralServiceException) {
                call.cancel();
                Log.e(RecordTriggerNetworkClient.TAG, String.format("Server call to %s with status code %d", FeatureConstants.RECORD_TRIGGER_ENDPOINT, Integer.valueOf(coralServiceException.getStatusCode())), coralServiceException);
                RecordTriggerNetworkClient.this.listener.onFailure(list, coralServiceException.getStatusCode(), coralServiceException);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(CoralService.Call<Response> call, Response response) {
                if (response != null) {
                    try {
                        if (response.body() != null) {
                            RecordTriggerNetworkClient.this.listener.onSuccess(response.body().string());
                        }
                    } catch (IOException e) {
                        Log.e(RecordTriggerNetworkClient.TAG, "Exception caught while reading response body for record trigger request.", e);
                        return;
                    }
                }
                String unused = RecordTriggerNetworkClient.TAG;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void makeCall(@NonNull List<RequestTreatment> list, @NonNull RecordTriggerServiceHelper recordTriggerServiceHelper) {
        if (list.isEmpty()) {
            Log.w(TAG, "Asked to make network call, but no triggers to upload");
            return;
        }
        String requestJson = recordTriggerServiceHelper.toRequestJson(list);
        String.format("Record trigger request payload: %s", requestJson);
        try {
            this.coralService.mo358get().request(FeatureConstants.RECORD_TRIGGER_ENDPOINT).post(JsonUtils.toJsonObject(requestJson)).asRaw().enqueue(createNetworkCallback(list));
        } catch (JsonSyntaxException | IllegalArgumentException e) {
            Log.e(TAG, "Request payload was not a properly formatted json", e);
        }
    }
}
