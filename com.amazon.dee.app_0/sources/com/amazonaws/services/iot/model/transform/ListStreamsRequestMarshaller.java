package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListStreamsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListStreamsRequestMarshaller implements Marshaller<Request<ListStreamsRequest>, ListStreamsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListStreamsRequest> marshall(ListStreamsRequest listStreamsRequest) {
        if (listStreamsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listStreamsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listStreamsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listStreamsRequest.getMaxResults()));
            }
            if (listStreamsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listStreamsRequest.getNextToken()));
            }
            if (listStreamsRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listStreamsRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/streams", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListStreamsRequest)");
    }
}
