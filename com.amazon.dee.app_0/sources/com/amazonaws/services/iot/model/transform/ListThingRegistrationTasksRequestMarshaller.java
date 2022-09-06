package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingRegistrationTasksRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingRegistrationTasksRequestMarshaller implements Marshaller<Request<ListThingRegistrationTasksRequest>, ListThingRegistrationTasksRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingRegistrationTasksRequest> marshall(ListThingRegistrationTasksRequest listThingRegistrationTasksRequest) {
        if (listThingRegistrationTasksRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingRegistrationTasksRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listThingRegistrationTasksRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingRegistrationTasksRequest.getNextToken()));
            }
            if (listThingRegistrationTasksRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingRegistrationTasksRequest.getMaxResults()));
            }
            if (listThingRegistrationTasksRequest.getStatus() != null) {
                defaultRequest.addParameter("status", StringUtils.fromString(listThingRegistrationTasksRequest.getStatus()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/thing-registration-tasks", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingRegistrationTasksRequest)");
    }
}
