package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeJobRequestMarshaller implements Marshaller<Request<DescribeJobRequest>, DescribeJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeJobRequest> marshall(DescribeJobRequest describeJobRequest) {
        if (describeJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/jobs/{jobId}", "{jobId}", describeJobRequest.getJobId() == null ? "" : StringUtils.fromString(describeJobRequest.getJobId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeJobRequest)");
    }
}
