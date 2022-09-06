package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListScheduledAuditsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListScheduledAuditsRequestMarshaller implements Marshaller<Request<ListScheduledAuditsRequest>, ListScheduledAuditsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListScheduledAuditsRequest> marshall(ListScheduledAuditsRequest listScheduledAuditsRequest) {
        if (listScheduledAuditsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listScheduledAuditsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listScheduledAuditsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listScheduledAuditsRequest.getNextToken()));
            }
            if (listScheduledAuditsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listScheduledAuditsRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/audit/scheduledaudits", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListScheduledAuditsRequest)");
    }
}
