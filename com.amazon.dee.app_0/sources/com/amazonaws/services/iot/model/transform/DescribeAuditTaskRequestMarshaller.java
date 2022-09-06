package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuditTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeAuditTaskRequestMarshaller implements Marshaller<Request<DescribeAuditTaskRequest>, DescribeAuditTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuditTaskRequest> marshall(DescribeAuditTaskRequest describeAuditTaskRequest) {
        if (describeAuditTaskRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeAuditTaskRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/audit/tasks/{taskId}", "{taskId}", describeAuditTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(describeAuditTaskRequest.getTaskId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuditTaskRequest)");
    }
}
