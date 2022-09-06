package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeThingGroupRequestMarshaller implements Marshaller<Request<DescribeThingGroupRequest>, DescribeThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingGroupRequest> marshall(DescribeThingGroupRequest describeThingGroupRequest) {
        if (describeThingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeThingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/thing-groups/{thingGroupName}", "{thingGroupName}", describeThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(describeThingGroupRequest.getThingGroupName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingGroupRequest)");
    }
}
