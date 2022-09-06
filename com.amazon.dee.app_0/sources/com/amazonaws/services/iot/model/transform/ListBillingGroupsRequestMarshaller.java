package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListBillingGroupsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListBillingGroupsRequestMarshaller implements Marshaller<Request<ListBillingGroupsRequest>, ListBillingGroupsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListBillingGroupsRequest> marshall(ListBillingGroupsRequest listBillingGroupsRequest) {
        if (listBillingGroupsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listBillingGroupsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listBillingGroupsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listBillingGroupsRequest.getNextToken()));
            }
            if (listBillingGroupsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listBillingGroupsRequest.getMaxResults()));
            }
            if (listBillingGroupsRequest.getNamePrefixFilter() != null) {
                defaultRequest.addParameter("namePrefixFilter", StringUtils.fromString(listBillingGroupsRequest.getNamePrefixFilter()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/billing-groups", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListBillingGroupsRequest)");
    }
}
