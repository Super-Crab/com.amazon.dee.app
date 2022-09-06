package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingRegistrationTaskReportsRequestMarshaller implements Marshaller<Request<ListThingRegistrationTaskReportsRequest>, ListThingRegistrationTaskReportsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingRegistrationTaskReportsRequest> marshall(ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest) {
        if (listThingRegistrationTaskReportsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingRegistrationTaskReportsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/thing-registration-tasks/{taskId}/reports".replace("{taskId}", listThingRegistrationTaskReportsRequest.getTaskId() == null ? "" : StringUtils.fromString(listThingRegistrationTaskReportsRequest.getTaskId()));
            if (listThingRegistrationTaskReportsRequest.getReportType() != null) {
                defaultRequest.addParameter("reportType", StringUtils.fromString(listThingRegistrationTaskReportsRequest.getReportType()));
            }
            if (listThingRegistrationTaskReportsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingRegistrationTaskReportsRequest.getNextToken()));
            }
            if (listThingRegistrationTaskReportsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingRegistrationTaskReportsRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingRegistrationTaskReportsRequest)");
    }
}
