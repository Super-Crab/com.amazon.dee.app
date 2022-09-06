package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListRoleAliasesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListRoleAliasesRequestMarshaller implements Marshaller<Request<ListRoleAliasesRequest>, ListRoleAliasesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListRoleAliasesRequest> marshall(ListRoleAliasesRequest listRoleAliasesRequest) {
        if (listRoleAliasesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listRoleAliasesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listRoleAliasesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listRoleAliasesRequest.getPageSize()));
            }
            if (listRoleAliasesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listRoleAliasesRequest.getMarker()));
            }
            if (listRoleAliasesRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listRoleAliasesRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/role-aliases", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListRoleAliasesRequest)");
    }
}
