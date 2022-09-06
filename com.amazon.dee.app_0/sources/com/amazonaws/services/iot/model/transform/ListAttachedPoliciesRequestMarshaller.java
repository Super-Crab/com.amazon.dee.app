package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAttachedPoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class ListAttachedPoliciesRequestMarshaller implements Marshaller<Request<ListAttachedPoliciesRequest>, ListAttachedPoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAttachedPoliciesRequest> marshall(ListAttachedPoliciesRequest listAttachedPoliciesRequest) {
        if (listAttachedPoliciesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listAttachedPoliciesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            String replace = "/attached-policies/{target}".replace("{target}", listAttachedPoliciesRequest.getTarget() == null ? "" : StringUtils.fromString(listAttachedPoliciesRequest.getTarget()));
            if (listAttachedPoliciesRequest.getRecursive() != null) {
                defaultRequest.addParameter("recursive", StringUtils.fromBoolean(listAttachedPoliciesRequest.getRecursive()));
            }
            if (listAttachedPoliciesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listAttachedPoliciesRequest.getMarker()));
            }
            if (listAttachedPoliciesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listAttachedPoliciesRequest.getPageSize()));
            }
            defaultRequest.setResourcePath(replace);
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListAttachedPoliciesRequest)");
    }
}
