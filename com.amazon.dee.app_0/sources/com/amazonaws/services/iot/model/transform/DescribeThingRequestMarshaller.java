package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeThingRequestMarshaller implements Marshaller<Request<DescribeThingRequest>, DescribeThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingRequest> marshall(DescribeThingRequest describeThingRequest) {
        if (describeThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/things/{thingName}", "{thingName}", describeThingRequest.getThingName() == null ? "" : StringUtils.fromString(describeThingRequest.getThingName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingRequest)");
    }
}
