package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteRoleAliasRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteRoleAliasRequestMarshaller implements Marshaller<Request<DeleteRoleAliasRequest>, DeleteRoleAliasRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteRoleAliasRequest> marshall(DeleteRoleAliasRequest deleteRoleAliasRequest) {
        if (deleteRoleAliasRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteRoleAliasRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/role-aliases/{roleAlias}", "{roleAlias}", deleteRoleAliasRequest.getRoleAlias() == null ? "" : StringUtils.fromString(deleteRoleAliasRequest.getRoleAlias()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteRoleAliasRequest)");
    }
}
