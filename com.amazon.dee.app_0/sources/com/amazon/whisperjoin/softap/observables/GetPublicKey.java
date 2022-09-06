package com.amazon.whisperjoin.softap.observables;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.whisperjoin.softap.exceptions.SoftAPRequestTimeoutException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPServerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnknownRequestException;
import com.amazon.whisperjoin.softap.wifi.requests.Request;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import com.amazon.whisperjoin.softap.wifi.requests.RequestTimeoutException;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Single;
import rx.SingleSubscriber;
/* loaded from: classes13.dex */
public class GetPublicKey extends SoftApRequestOperation<String> {
    private static final String ENDPOINT_PATH = "/publicKey";
    private static final String JSON_KEY_PUBLIC_KEY = "publicKey";
    private static final String TAG = "GetPublicKey";

    public GetPublicKey(RequestBuilderProvider requestBuilderProvider) {
        super(requestBuilderProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPubKey(SingleSubscriber singleSubscriber) {
        try {
            Request buildRequest = requestBuilder().setRequestMethod(RequestBuilder.RequestMethod.GET).buildRequest();
            buildRequest.makeRequest();
            String string = new JSONObject(buildRequest.getResponseString()).getString(JSON_KEY_PUBLIC_KEY);
            if (!TextUtils.isEmpty(string)) {
                singleSubscriber.onSuccess(string);
                return;
            }
            throw new JSONException("Public key not present in JSON response");
        } catch (RequestException e) {
            singleSubscriber.onError(getSoftApException(e));
        } catch (IOException e2) {
            Log.e(TAG, "An IO Exception occurred while getting public key", e2);
            singleSubscriber.onError(e2);
        } catch (JSONException e3) {
            Log.e(TAG, "A JSON Exception occurred while getting public key", e3);
            singleSubscriber.onError(e3);
        }
    }

    private Exception getSoftApException(RequestException requestException) {
        if (requestException.getErrorCode().isPresent()) {
            int intValue = requestException.getErrorCode().get().intValue();
            if (intValue != 500) {
                Log.e(TAG, String.format("Request Exception while getting public key with error code: %d", Integer.valueOf(intValue)), requestException);
                return new SoftAPUnknownRequestException(requestException);
            }
            Log.e(TAG, "Unknown device error while getting public key", requestException);
            return new SoftAPServerException(requestException);
        } else if (requestException instanceof RequestTimeoutException) {
            Log.e(TAG, "A Request Timeout Exception occurred while getting public key", requestException);
            return new SoftAPRequestTimeoutException(requestException);
        } else {
            Log.e(TAG, "An Request Exception occurred while getting public key", requestException);
            return new SoftAPUnknownRequestException(requestException);
        }
    }

    @Override // com.amazon.whisperjoin.softap.observables.SoftApRequestOperation
    protected String getPath() {
        return ENDPOINT_PATH;
    }

    @Override // com.amazon.whisperjoin.softap.observables.HttpRequestOperation
    public Single<String> observe() {
        return Single.create(new Single.OnSubscribe<String>() { // from class: com.amazon.whisperjoin.softap.observables.GetPublicKey.1
            @Override // rx.functions.Action1
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                GetPublicKey.this.getPubKey(singleSubscriber);
            }
        });
    }
}
