package com.amazon.whisperjoin.softap.observables;

import android.util.Log;
import com.amazon.whisperjoin.softap.exceptions.SoftAPDeserializerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPRequestTimeoutException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPServerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnknownRequestException;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.serializer.StringDeserializer;
import com.amazon.whisperjoin.softap.wifi.requests.Request;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import com.amazon.whisperjoin.softap.wifi.requests.RequestTimeoutException;
import java.io.IOException;
import rx.Single;
import rx.SingleSubscriber;
/* loaded from: classes13.dex */
public class GetDeviceDetails extends SoftApRequestOperation<DeviceDetails> {
    private static final String ENDPOINT_PATH = "/deviceDetails";
    private static final String TAG = "GetDeviceDetails";
    private final StringDeserializer<DeviceDetails> stringSerializer;

    public GetDeviceDetails(RequestBuilderProvider requestBuilderProvider, StringDeserializer<DeviceDetails> stringDeserializer) {
        super(requestBuilderProvider);
        this.stringSerializer = stringDeserializer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceDetails(SingleSubscriber<? super DeviceDetails> singleSubscriber) {
        try {
            Request buildRequest = requestBuilder().setContentType(RequestBuilder.ContentType.JSON_UTF_8).buildRequest();
            buildRequest.makeRequest();
            String responseString = buildRequest.getResponseString();
            String.format("Parsing JSON Response: %s", responseString);
            DeviceDetails mo6645deserialize = this.stringSerializer.mo6645deserialize(responseString);
            String.format("Retrieved device details: %s", mo6645deserialize);
            singleSubscriber.onSuccess(mo6645deserialize);
        } catch (SoftAPDeserializerException e) {
            Log.e(TAG, "A Serializer Exception occurred while getting device details", e);
            singleSubscriber.onError(e);
        } catch (RequestException e2) {
            singleSubscriber.onError(getSoftApException(e2));
        } catch (IOException e3) {
            Log.e(TAG, "An IO Exception occurred while getting device details", e3);
            singleSubscriber.onError(e3);
        }
    }

    private Exception getSoftApException(RequestException requestException) {
        if (requestException.getErrorCode().isPresent()) {
            int intValue = requestException.getErrorCode().get().intValue();
            if (intValue != 500) {
                Log.e(TAG, String.format("Request Exception while getting device details with error code: %d", Integer.valueOf(intValue)), requestException);
                return new SoftAPUnknownRequestException(requestException);
            }
            Log.e(TAG, "Unknown device error while getting device details", requestException);
            return new SoftAPServerException(requestException);
        } else if (requestException instanceof RequestTimeoutException) {
            Log.e(TAG, "A Request Timeout Exception occurred while getting device details", requestException);
            return new SoftAPRequestTimeoutException(requestException);
        } else {
            Log.e(TAG, "An unknown Request Exception occurred while getting device details", requestException);
            return new SoftAPUnknownRequestException(requestException);
        }
    }

    @Override // com.amazon.whisperjoin.softap.observables.SoftApRequestOperation
    protected String getPath() {
        return ENDPOINT_PATH;
    }

    @Override // com.amazon.whisperjoin.softap.observables.HttpRequestOperation
    public Single<DeviceDetails> observe() {
        return Single.create(new Single.OnSubscribe<DeviceDetails>() { // from class: com.amazon.whisperjoin.softap.observables.GetDeviceDetails.1
            @Override // rx.functions.Action1
            public void call(SingleSubscriber<? super DeviceDetails> singleSubscriber) {
                GetDeviceDetails.this.getDeviceDetails(singleSubscriber);
            }
        });
    }
}
