package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteBillingGroupRequestMarshaller implements Marshaller<Request<DeleteBillingGroupRequest>, DeleteBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteBillingGroupRequest> marshall(DeleteBillingGroupRequest deleteBillingGroupRequest) {
        if (deleteBillingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteBillingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/billing-groups/{billingGroupName}".replace("{billingGroupName}", deleteBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(deleteBillingGroupRequest.getBillingGroupName()));
            if (deleteBillingGroupRequest.getExpectedVersion() != null) {
                defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteBillingGroupRequest.getExpectedVersion()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteBillingGroupRequest)");
    }
}
