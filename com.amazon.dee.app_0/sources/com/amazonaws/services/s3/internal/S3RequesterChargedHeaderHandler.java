package com.amazonaws.services.s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.S3RequesterChargedResult;
/* loaded from: classes13.dex */
public class S3RequesterChargedHeaderHandler<T extends S3RequesterChargedResult> implements HeaderHandler<T> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazonaws.services.s3.internal.HeaderHandler
    public /* bridge */ /* synthetic */ void handle(Object obj, HttpResponse httpResponse) {
        handle((S3RequesterChargedHeaderHandler<T>) ((S3RequesterChargedResult) obj), httpResponse);
    }

    public void handle(T t, HttpResponse httpResponse) {
        t.setRequesterCharged(httpResponse.getHeaders().get(Headers.REQUESTER_CHARGED_HEADER) != null);
    }
}
