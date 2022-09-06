package com.amazon.whisperjoin.softap.observables;

import android.util.Log;
import com.amazon.whisperjoin.softap.exceptions.SoftAPBadRequestException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidCipherException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidPublicKeyException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPRequestTimeoutException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPServerException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnknownRequestException;
import com.amazon.whisperjoin.softap.json.SendPublicKeyRequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import com.amazon.whisperjoin.softap.wifi.requests.RequestTimeoutException;
import com.google.common.base.Strings;
import java.io.IOException;
import org.json.JSONException;
import rx.Single;
import rx.SingleSubscriber;
/* loaded from: classes13.dex */
public class SendPublicKey extends SoftApRequestOperation<Void> {
    private static final String ENDPOINT_PATH = "/publicKey";
    private static final String TAG = "SendPublicKey";
    private final int encryptionSchemeId;
    private SendPublicKeyRequestBuilder jsonRequestBuilder;
    private final String pemEncodedPublicKey;

    public SendPublicKey(String str, int i, RequestBuilderProvider requestBuilderProvider) {
        this(str, i, new SendPublicKeyRequestBuilder(), requestBuilderProvider);
    }

    private Exception getSoftApException(RequestException requestException) {
        if (requestException.getErrorCode().isPresent()) {
            int intValue = requestException.getErrorCode().get().intValue();
            if (intValue == 400) {
                Log.e(TAG, "Invalid decrypted JSON format or missing decrypted fields while sending public key", requestException);
                return new SoftAPBadRequestException(requestException);
            } else if (intValue == 500) {
                Log.e(TAG, "Unknown device error while sending public key", requestException);
                return new SoftAPServerException(requestException);
            } else if (intValue == 405) {
                Log.e(TAG, "Invalid requested cipher while sending public key", requestException);
                return new SoftAPInvalidCipherException(requestException);
            } else if (intValue != 406) {
                Log.e(TAG, String.format("Request Exception while sending public key with error code: %d", Integer.valueOf(intValue)), requestException);
                return new SoftAPUnknownRequestException(requestException);
            } else {
                Log.e(TAG, "Invalid public key while sending public key", requestException);
                return new SoftAPInvalidPublicKeyException(requestException);
            }
        } else if (requestException instanceof RequestTimeoutException) {
            Log.e(TAG, "A Request Timeout Exception occurred while sending public key", requestException);
            return new SoftAPRequestTimeoutException(requestException);
        } else {
            Log.e(TAG, "An Request Exception occurred while sending public key", requestException);
            return new SoftAPUnknownRequestException(requestException);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPublicKey(SingleSubscriber<? super Void> singleSubscriber) {
        try {
            requestBuilder().setContentType(RequestBuilder.ContentType.JSON_UTF_8).setRequestMethod(RequestBuilder.RequestMethod.POST).setRequestBody(this.jsonRequestBuilder.build(this.pemEncodedPublicKey, this.encryptionSchemeId)).buildRequest().makeRequest();
            singleSubscriber.onSuccess(null);
        } catch (RequestException e) {
            singleSubscriber.onError(getSoftApException(e));
        } catch (IOException e2) {
            Log.e(TAG, "An IO Exception occurred while sending public key", e2);
            singleSubscriber.onError(e2);
        } catch (JSONException e3) {
            Log.e(TAG, "A JSON Exception occurred while sending public key", e3);
            singleSubscriber.onError(e3);
        }
    }

    @Override // com.amazon.whisperjoin.softap.observables.SoftApRequestOperation
    protected String getPath() {
        return ENDPOINT_PATH;
    }

    @Override // com.amazon.whisperjoin.softap.observables.HttpRequestOperation
    public Single<Void> observe() {
        return Single.create(new Single.OnSubscribe<Void>() { // from class: com.amazon.whisperjoin.softap.observables.SendPublicKey.1
            @Override // rx.functions.Action1
            public void call(SingleSubscriber<? super Void> singleSubscriber) {
                SendPublicKey.this.sendPublicKey(singleSubscriber);
            }
        });
    }

    SendPublicKey(String str, int i, SendPublicKeyRequestBuilder sendPublicKeyRequestBuilder, RequestBuilderProvider requestBuilderProvider) {
        super(requestBuilderProvider);
        if (!Strings.isNullOrEmpty(str)) {
            this.pemEncodedPublicKey = str;
            this.encryptionSchemeId = i;
            this.jsonRequestBuilder = sendPublicKeyRequestBuilder;
            return;
        }
        throw new IllegalArgumentException("Public Key can't be null or empty");
    }
}
