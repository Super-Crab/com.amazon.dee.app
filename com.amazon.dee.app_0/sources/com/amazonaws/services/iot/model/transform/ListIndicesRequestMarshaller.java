package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListIndicesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListIndicesRequestMarshaller implements Marshaller<Request<ListIndicesRequest>, ListIndicesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListIndicesRequest> marshall(ListIndicesRequest listIndicesRequest) {
        if (listIndicesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listIndicesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listIndicesRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listIndicesRequest.getNextToken()));
            }
            if (listIndicesRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listIndicesRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/indices", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListIndicesRequest)");
    }
}
