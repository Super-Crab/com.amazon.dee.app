package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListActiveViolationsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListActiveViolationsRequestMarshaller implements Marshaller<Request<ListActiveViolationsRequest>, ListActiveViolationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListActiveViolationsRequest> marshall(ListActiveViolationsRequest listActiveViolationsRequest) {
        if (listActiveViolationsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listActiveViolationsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listActiveViolationsRequest.getThingName() != null) {
                defaultRequest.addParameter("thingName", StringUtils.fromString(listActiveViolationsRequest.getThingName()));
            }
            if (listActiveViolationsRequest.getSecurityProfileName() != null) {
                defaultRequest.addParameter("securityProfileName", StringUtils.fromString(listActiveViolationsRequest.getSecurityProfileName()));
            }
            if (listActiveViolationsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listActiveViolationsRequest.getNextToken()));
            }
            if (listActiveViolationsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listActiveViolationsRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/active-violations", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListActiveViolationsRequest)");
    }
}
