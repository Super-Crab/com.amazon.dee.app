package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingsInBillingGroupRequestMarshaller implements Marshaller<Request<ListThingsInBillingGroupRequest>, ListThingsInBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingsInBillingGroupRequest> marshall(ListThingsInBillingGroupRequest listThingsInBillingGroupRequest) {
        if (listThingsInBillingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingsInBillingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/billing-groups/{billingGroupName}/things".replace("{billingGroupName}", listThingsInBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(listThingsInBillingGroupRequest.getBillingGroupName()));
            if (listThingsInBillingGroupRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingsInBillingGroupRequest.getNextToken()));
            }
            if (listThingsInBillingGroupRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingsInBillingGroupRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingsInBillingGroupRequest)");
    }
}
