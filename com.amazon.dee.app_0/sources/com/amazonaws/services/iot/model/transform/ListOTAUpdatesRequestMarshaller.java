package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListOTAUpdatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListOTAUpdatesRequestMarshaller implements Marshaller<Request<ListOTAUpdatesRequest>, ListOTAUpdatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListOTAUpdatesRequest> marshall(ListOTAUpdatesRequest listOTAUpdatesRequest) {
        if (listOTAUpdatesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listOTAUpdatesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listOTAUpdatesRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listOTAUpdatesRequest.getMaxResults()));
            }
            if (listOTAUpdatesRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listOTAUpdatesRequest.getNextToken()));
            }
            if (listOTAUpdatesRequest.getOtaUpdateStatus() != null) {
                defaultRequest.addParameter("otaUpdateStatus", StringUtils.fromString(listOTAUpdatesRequest.getOtaUpdateStatus()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/otaUpdates", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListOTAUpdatesRequest)");
    }
}
