package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingTypeRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeThingTypeRequestMarshaller implements Marshaller<Request<DescribeThingTypeRequest>, DescribeThingTypeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingTypeRequest> marshall(DescribeThingTypeRequest describeThingTypeRequest) {
        if (describeThingTypeRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeThingTypeRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/thing-types/{thingTypeName}", "{thingTypeName}", describeThingTypeRequest.getThingTypeName() == null ? "" : StringUtils.fromString(describeThingTypeRequest.getThingTypeName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingTypeRequest)");
    }
}
