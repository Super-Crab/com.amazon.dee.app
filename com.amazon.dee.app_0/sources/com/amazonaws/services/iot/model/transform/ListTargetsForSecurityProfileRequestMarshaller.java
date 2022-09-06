package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListTargetsForSecurityProfileRequestMarshaller implements Marshaller<Request<ListTargetsForSecurityProfileRequest>, ListTargetsForSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTargetsForSecurityProfileRequest> marshall(ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest) {
        if (listTargetsForSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listTargetsForSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/security-profiles/{securityProfileName}/targets".replace("{securityProfileName}", listTargetsForSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(listTargetsForSecurityProfileRequest.getSecurityProfileName()));
            if (listTargetsForSecurityProfileRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listTargetsForSecurityProfileRequest.getNextToken()));
            }
            if (listTargetsForSecurityProfileRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listTargetsForSecurityProfileRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListTargetsForSecurityProfileRequest)");
    }
}
