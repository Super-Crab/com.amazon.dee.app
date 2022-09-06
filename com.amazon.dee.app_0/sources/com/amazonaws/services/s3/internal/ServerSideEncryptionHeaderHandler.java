package com.amazonaws.services.s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.ServerSideEncryptionResult;
/* loaded from: classes13.dex */
public class ServerSideEncryptionHeaderHandler<T extends ServerSideEncryptionResult> implements HeaderHandler<T> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazonaws.services.s3.internal.HeaderHandler
    public /* bridge */ /* synthetic */ void handle(Object obj, HttpResponse httpResponse) {
        handle((ServerSideEncryptionHeaderHandler<T>) ((ServerSideEncryptionResult) obj), httpResponse);
    }

    public void handle(T t, HttpResponse httpResponse) {
        t.setSSEAlgorithm(httpResponse.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION));
        t.setSSECustomerAlgorithm(httpResponse.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM));
        t.setSSECustomerKeyMd5(httpResponse.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5));
    }
}
