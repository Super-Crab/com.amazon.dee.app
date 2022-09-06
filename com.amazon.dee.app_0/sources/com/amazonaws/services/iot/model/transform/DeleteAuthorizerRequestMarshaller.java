package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteAuthorizerRequestMarshaller implements Marshaller<Request<DeleteAuthorizerRequest>, DeleteAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteAuthorizerRequest> marshall(DeleteAuthorizerRequest deleteAuthorizerRequest) {
        if (deleteAuthorizerRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteAuthorizerRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/authorizer/{authorizerName}", "{authorizerName}", deleteAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(deleteAuthorizerRequest.getAuthorizerName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteAuthorizerRequest)");
    }
}
