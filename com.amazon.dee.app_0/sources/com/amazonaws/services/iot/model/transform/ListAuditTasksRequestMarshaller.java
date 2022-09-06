package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuditTasksRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListAuditTasksRequestMarshaller implements Marshaller<Request<ListAuditTasksRequest>, ListAuditTasksRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuditTasksRequest> marshall(ListAuditTasksRequest listAuditTasksRequest) {
        if (listAuditTasksRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listAuditTasksRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listAuditTasksRequest.getStartTime() != null) {
                defaultRequest.addParameter("startTime", StringUtils.fromDate(listAuditTasksRequest.getStartTime()));
            }
            if (listAuditTasksRequest.getEndTime() != null) {
                defaultRequest.addParameter("endTime", StringUtils.fromDate(listAuditTasksRequest.getEndTime()));
            }
            if (listAuditTasksRequest.getTaskType() != null) {
                defaultRequest.addParameter("taskType", StringUtils.fromString(listAuditTasksRequest.getTaskType()));
            }
            if (listAuditTasksRequest.getTaskStatus() != null) {
                defaultRequest.addParameter("taskStatus", StringUtils.fromString(listAuditTasksRequest.getTaskStatus()));
            }
            if (listAuditTasksRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listAuditTasksRequest.getNextToken()));
            }
            if (listAuditTasksRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listAuditTasksRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/audit/tasks", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListAuditTasksRequest)");
    }
}
