package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListJobExecutionsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListJobExecutionsForThingRequestMarshaller implements Marshaller<Request<ListJobExecutionsForThingRequest>, ListJobExecutionsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListJobExecutionsForThingRequest> marshall(ListJobExecutionsForThingRequest listJobExecutionsForThingRequest) {
        if (listJobExecutionsForThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listJobExecutionsForThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/things/{thingName}/jobs".replace("{thingName}", listJobExecutionsForThingRequest.getThingName() == null ? "" : StringUtils.fromString(listJobExecutionsForThingRequest.getThingName()));
            if (listJobExecutionsForThingRequest.getStatus() != null) {
                defaultRequest.addParameter("status", StringUtils.fromString(listJobExecutionsForThingRequest.getStatus()));
            }
            if (listJobExecutionsForThingRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listJobExecutionsForThingRequest.getMaxResults()));
            }
            if (listJobExecutionsForThingRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listJobExecutionsForThingRequest.getNextToken()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListJobExecutionsForThingRequest)");
    }
}
