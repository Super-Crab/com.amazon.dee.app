package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTargetsForPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class ListTargetsForPolicyRequestMarshaller implements Marshaller<Request<ListTargetsForPolicyRequest>, ListTargetsForPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTargetsForPolicyRequest> marshall(ListTargetsForPolicyRequest listTargetsForPolicyRequest) {
        if (listTargetsForPolicyRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listTargetsForPolicyRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            String replace = "/policy-targets/{policyName}".replace("{policyName}", listTargetsForPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(listTargetsForPolicyRequest.getPolicyName()));
            if (listTargetsForPolicyRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listTargetsForPolicyRequest.getMarker()));
            }
            if (listTargetsForPolicyRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listTargetsForPolicyRequest.getPageSize()));
            }
            defaultRequest.setResourcePath(replace);
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListTargetsForPolicyRequest)");
    }
}
