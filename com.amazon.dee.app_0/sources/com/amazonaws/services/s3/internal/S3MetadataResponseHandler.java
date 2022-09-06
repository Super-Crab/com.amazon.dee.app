package com.amazonaws.services.s3.internal;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.s3.model.ObjectMetadata;
/* loaded from: classes13.dex */
public class S3MetadataResponseHandler extends AbstractS3ResponseHandler<ObjectMetadata> {
    @Override // com.amazonaws.http.HttpResponseHandler
    /* renamed from: handle */
    public AmazonWebServiceResponse<ObjectMetadata> mo6743handle(HttpResponse httpResponse) throws Exception {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        populateObjectMetadata(httpResponse, objectMetadata);
        AmazonWebServiceResponse<ObjectMetadata> parseResponseMetadata = parseResponseMetadata(httpResponse);
        parseResponseMetadata.setResult(objectMetadata);
        return parseResponseMetadata;
    }
}
