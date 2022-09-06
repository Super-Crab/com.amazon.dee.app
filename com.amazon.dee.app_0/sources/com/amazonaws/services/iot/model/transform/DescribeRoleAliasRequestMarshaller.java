package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeRoleAliasRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeRoleAliasRequestMarshaller implements Marshaller<Request<DescribeRoleAliasRequest>, DescribeRoleAliasRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeRoleAliasRequest> marshall(DescribeRoleAliasRequest describeRoleAliasRequest) {
        if (describeRoleAliasRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeRoleAliasRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/role-aliases/{roleAlias}", "{roleAlias}", describeRoleAliasRequest.getRoleAlias() == null ? "" : StringUtils.fromString(describeRoleAliasRequest.getRoleAlias()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeRoleAliasRequest)");
    }
}
