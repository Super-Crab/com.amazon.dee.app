package com.amazon.whisperjoin.softap.observables;

import android.util.Log;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import java.io.IOException;
import rx.Single;
import rx.SingleSubscriber;
/* loaded from: classes13.dex */
public abstract class PostBinaryData extends SoftApRequestOperation<Void> {
    private static final String TAG = SecureProvisionDevice.class.getSimpleName();
    private final byte[] binaryRequestBody;
    private final String path;

    public PostBinaryData(String str, byte[] bArr, RequestBuilderProvider requestBuilderProvider) {
        super(requestBuilderProvider);
        this.path = str;
        this.binaryRequestBody = (byte[]) bArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postData(SingleSubscriber singleSubscriber) {
        try {
            requestBuilder().setContentType(RequestBuilder.ContentType.BINARY).setRequestMethod(RequestBuilder.RequestMethod.POST).setRequestBody(this.binaryRequestBody).buildRequest().makeRequest();
            singleSubscriber.onSuccess(null);
        } catch (RequestException e) {
            singleSubscriber.onError(getSoftApException(e));
        } catch (IOException e2) {
            Log.e(TAG, "An IO Exception occurred while posting data", e2);
            singleSubscriber.onError(e2);
        }
    }

    @Override // com.amazon.whisperjoin.softap.observables.SoftApRequestOperation
    protected String getPath() {
        return this.path;
    }

    protected abstract Exception getSoftApException(RequestException requestException);

    @Override // com.amazon.whisperjoin.softap.observables.HttpRequestOperation
    public Single<Void> observe() {
        return Single.create(new Single.OnSubscribe<Void>() { // from class: com.amazon.whisperjoin.softap.observables.PostBinaryData.1
            @Override // rx.functions.Action1
            public void call(SingleSubscriber<? super Void> singleSubscriber) {
                PostBinaryData.this.postData(singleSubscriber);
            }
        });
    }
}
