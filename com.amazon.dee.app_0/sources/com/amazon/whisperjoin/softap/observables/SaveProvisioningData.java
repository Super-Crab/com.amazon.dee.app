package com.amazon.whisperjoin.softap.observables;

import android.util.Log;
import com.amazon.whisperjoin.softap.exceptions.SoftAPBadRequestException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidProvisioningDataException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPRequestTimeoutException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPServerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnknownRequestException;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.serializer.ByteSerializer;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import com.amazon.whisperjoin.softap.wifi.requests.RequestTimeoutException;
import java.io.IOException;
import rx.Single;
import rx.SingleSubscriber;
/* loaded from: classes13.dex */
public class SaveProvisioningData extends SoftApRequestOperation<Void> {
    private static final String ENDPOINT_PATH = "/provisioningData";
    private static final String TAG = "SaveProvisioningData";
    private final ByteSerializer<ProvisioningData> byteSerializer;
    private final ProvisioningData provisioningData;

    public SaveProvisioningData(RequestBuilderProvider requestBuilderProvider, ByteSerializer<ProvisioningData> byteSerializer, ProvisioningData provisioningData) {
        super(requestBuilderProvider);
        this.byteSerializer = byteSerializer;
        this.provisioningData = provisioningData;
    }

    private Exception getSoftApException(RequestException requestException) {
        if (requestException.getErrorCode().isPresent()) {
            int intValue = requestException.getErrorCode().get().intValue();
            if (intValue == 400) {
                Log.e(TAG, "Invalid JSON format or missing fields while saving provisioning data", requestException);
                return new SoftAPBadRequestException(requestException);
            } else if (intValue == 406) {
                Log.e(TAG, "Invalid provisioning data while saving provisioning data", requestException);
                return new SoftAPInvalidProvisioningDataException(requestException);
            } else if (intValue != 500) {
                Log.e(TAG, String.format("Request Exception while saving provisioning data with error code: %d", Integer.valueOf(intValue)), requestException);
                return new SoftAPUnknownRequestException(requestException);
            } else {
                Log.e(TAG, "Unknown device error while saving provisioning data", requestException);
                return new SoftAPServerException(requestException);
            }
        } else if (requestException instanceof RequestTimeoutException) {
            Log.e(TAG, "A Request Timeout Exception occurred while saving provisioning data", requestException);
            return new SoftAPRequestTimeoutException(requestException);
        } else {
            Log.e(TAG, "An Request Exception occurred while saving provisioning data", requestException);
            return new SoftAPUnknownRequestException(requestException);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendProvisioningData(SingleSubscriber<? super Void> singleSubscriber) {
        String.format("Saving provisioning data with UTC time: %s, marketplace ID: %s, country code: %s", this.provisioningData.getUtcTime(), this.provisioningData.getMarketplace(), this.provisioningData.getCountryCode());
        try {
            requestBuilder().setContentType(RequestBuilder.ContentType.JSON_UTF_8).setRequestMethod(RequestBuilder.RequestMethod.POST).setRequestBody(this.byteSerializer.serialize(this.provisioningData)).buildRequest().makeRequest();
            singleSubscriber.onSuccess(null);
        } catch (RequestException e) {
            singleSubscriber.onError(getSoftApException(e));
        } catch (IOException e2) {
            Log.e(TAG, "An IO Exception occurred while saving provisioning data", e2);
            singleSubscriber.onError(e2);
        }
    }

    @Override // com.amazon.whisperjoin.softap.observables.SoftApRequestOperation
    protected String getPath() {
        return ENDPOINT_PATH;
    }

    @Override // com.amazon.whisperjoin.softap.observables.HttpRequestOperation
    public Single<Void> observe() {
        return Single.create(new Single.OnSubscribe<Void>() { // from class: com.amazon.whisperjoin.softap.observables.SaveProvisioningData.1
            @Override // rx.functions.Action1
            public void call(SingleSubscriber<? super Void> singleSubscriber) {
                SaveProvisioningData.this.sendProvisioningData(singleSubscriber);
            }
        });
    }
}
