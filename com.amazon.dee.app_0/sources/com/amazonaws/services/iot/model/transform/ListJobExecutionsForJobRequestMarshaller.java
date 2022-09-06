package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobExecutionsForJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListJobExecutionsForJobRequestMarshaller implements Marshaller<Request<ListJobExecutionsForJobRequest>, ListJobExecutionsForJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobExecutionsForJobRequest> marshall(ListJobExecutionsForJobRequest listJobExecutionsForJobRequest) {
        if (listJobExecutionsForJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listJobExecutionsForJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/jobs/{jobId}/things".replace("{jobId}", listJobExecutionsForJobRequest.getJobId() == null ? "" : StringUtils.fromString(listJobExecutionsForJobRequest.getJobId()));
            if (listJobExecutionsForJobRequest.getStatus() != null) {
                defaultRequest.addParameter("status", StringUtils.fromString(listJobExecutionsForJobRequest.getStatus()));
            }
            if (listJobExecutionsForJobRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobExecutionsForJobRequest.getMaxResults()));
            }
            if (listJobExecutionsForJobRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobExecutionsForJobRequest.getNextToken()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListJobExecutionsForJobRequest)");
    }
}
