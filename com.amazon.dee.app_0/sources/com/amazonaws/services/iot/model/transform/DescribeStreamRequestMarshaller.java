package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeStreamRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeStreamRequestMarshaller implements Marshaller<Request<DescribeStreamRequest>, DescribeStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeStreamRequest> marshall(DescribeStreamRequest describeStreamRequest) {
        if (describeStreamRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeStreamRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/streams/{streamId}", "{streamId}", describeStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(describeStreamRequest.getStreamId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeStreamRequest)");
    }
}
