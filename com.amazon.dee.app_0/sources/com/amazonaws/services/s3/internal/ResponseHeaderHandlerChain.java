package com.amazonaws.services.s3.internal;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.transform.Unmarshaller;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes13.dex */
public class ResponseHeaderHandlerChain<T> extends S3XmlResponseHandler<T> {
    private final List<HeaderHandler<T>> headerHandlers;

    public ResponseHeaderHandlerChain(Unmarshaller<T, InputStream> unmarshaller, HeaderHandler<T>... headerHandlerArr) {
        super(unmarshaller);
        this.headerHandlers = Arrays.asList(headerHandlerArr);
    }

    @Override // com.amazonaws.services.s3.internal.S3XmlResponseHandler, com.amazonaws.http.HttpResponseHandler
    /* renamed from: handle */
    public AmazonWebServiceResponse<T> mo6743handle(HttpResponse httpResponse) throws Exception {
        AmazonWebServiceResponse<T> mo6743handle = super.mo6743handle(httpResponse);
        T result = mo6743handle.getResult();
        if (result != null) {
            for (HeaderHandler<T> headerHandler : this.headerHandlers) {
                headerHandler.handle(result, httpResponse);
            }
        }
        return mo6743handle;
    }
}
