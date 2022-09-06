package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeThingRegistrationTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeThingRegistrationTaskRequestMarshaller implements Marshaller<Request<DescribeThingRegistrationTaskRequest>, DescribeThingRegistrationTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeThingRegistrationTaskRequest> marshall(DescribeThingRegistrationTaskRequest describeThingRegistrationTaskRequest) {
        if (describeThingRegistrationTaskRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeThingRegistrationTaskRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/thing-registration-tasks/{taskId}", "{taskId}", describeThingRegistrationTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(describeThingRegistrationTaskRequest.getTaskId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeThingRegistrationTaskRequest)");
    }
}
