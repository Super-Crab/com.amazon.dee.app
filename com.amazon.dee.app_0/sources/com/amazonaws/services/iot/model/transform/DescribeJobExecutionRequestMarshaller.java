package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeJobExecutionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeJobExecutionRequestMarshaller implements Marshaller<Request<DescribeJobExecutionRequest>, DescribeJobExecutionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeJobExecutionRequest> marshall(DescribeJobExecutionRequest describeJobExecutionRequest) {
        if (describeJobExecutionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeJobExecutionRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String str = "";
            String replace = "/things/{thingName}/jobs/{jobId}".replace("{jobId}", describeJobExecutionRequest.getJobId() == null ? str : StringUtils.fromString(describeJobExecutionRequest.getJobId()));
            if (describeJobExecutionRequest.getThingName() != null) {
                str = StringUtils.fromString(describeJobExecutionRequest.getThingName());
            }
            String replace2 = replace.replace("{thingName}", str);
            if (describeJobExecutionRequest.getExecutionNumber() != null) {
                defaultRequest.addParameter("executionNumber", StringUtils.fromLong(describeJobExecutionRequest.getExecutionNumber()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace2, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeJobExecutionRequest)");
    }
}
