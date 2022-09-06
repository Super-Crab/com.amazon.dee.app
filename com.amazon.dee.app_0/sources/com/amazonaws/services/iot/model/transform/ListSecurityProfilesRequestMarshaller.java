package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListSecurityProfilesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListSecurityProfilesRequestMarshaller implements Marshaller<Request<ListSecurityProfilesRequest>, ListSecurityProfilesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListSecurityProfilesRequest> marshall(ListSecurityProfilesRequest listSecurityProfilesRequest) {
        if (listSecurityProfilesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listSecurityProfilesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listSecurityProfilesRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listSecurityProfilesRequest.getNextToken()));
            }
            if (listSecurityProfilesRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listSecurityProfilesRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/security-profiles", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListSecurityProfilesRequest)");
    }
}
