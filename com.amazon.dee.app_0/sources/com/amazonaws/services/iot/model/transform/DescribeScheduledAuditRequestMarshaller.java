package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeScheduledAuditRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeScheduledAuditRequestMarshaller implements Marshaller<Request<DescribeScheduledAuditRequest>, DescribeScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeScheduledAuditRequest> marshall(DescribeScheduledAuditRequest describeScheduledAuditRequest) {
        if (describeScheduledAuditRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeScheduledAuditRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/audit/scheduledaudits/{scheduledAuditName}", "{scheduledAuditName}", describeScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(describeScheduledAuditRequest.getScheduledAuditName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeScheduledAuditRequest)");
    }
}
