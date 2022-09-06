package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListJobsRequestMarshaller implements Marshaller<Request<ListJobsRequest>, ListJobsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobsRequest> marshall(ListJobsRequest listJobsRequest) {
        if (listJobsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listJobsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listJobsRequest.getStatus() != null) {
                defaultRequest.addParameter("status", StringUtils.fromString(listJobsRequest.getStatus()));
            }
            if (listJobsRequest.getTargetSelection() != null) {
                defaultRequest.addParameter("targetSelection", StringUtils.fromString(listJobsRequest.getTargetSelection()));
            }
            if (listJobsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobsRequest.getMaxResults()));
            }
            if (listJobsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobsRequest.getNextToken()));
            }
            if (listJobsRequest.getThingGroupName() != null) {
                defaultRequest.addParameter("thingGroupName", StringUtils.fromString(listJobsRequest.getThingGroupName()));
            }
            if (listJobsRequest.getThingGroupId() != null) {
                defaultRequest.addParameter("thingGroupId", StringUtils.fromString(listJobsRequest.getThingGroupId()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/jobs", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListJobsRequest)");
    }
}
