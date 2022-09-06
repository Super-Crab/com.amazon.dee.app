package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeBillingGroupRequestMarshaller implements Marshaller<Request<DescribeBillingGroupRequest>, DescribeBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeBillingGroupRequest> marshall(DescribeBillingGroupRequest describeBillingGroupRequest) {
        if (describeBillingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeBillingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/billing-groups/{billingGroupName}", "{billingGroupName}", describeBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(describeBillingGroupRequest.getBillingGroupName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeBillingGroupRequest)");
    }
}
