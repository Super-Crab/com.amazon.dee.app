package com.amazonaws.http;
/* loaded from: classes13.dex */
public interface HttpResponseHandler<T> {
    /* renamed from: handle */
    T mo6743handle(HttpResponse httpResponse) throws Exception;

    boolean needsConnectionLeftOpen();
}
