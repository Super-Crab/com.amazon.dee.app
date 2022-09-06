package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeIndexRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeIndexRequestMarshaller implements Marshaller<Request<DescribeIndexRequest>, DescribeIndexRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeIndexRequest> marshall(DescribeIndexRequest describeIndexRequest) {
        if (describeIndexRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeIndexRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/indices/{indexName}", "{indexName}", describeIndexRequest.getIndexName() == null ? "" : StringUtils.fromString(describeIndexRequest.getIndexName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeIndexRequest)");
    }
}
