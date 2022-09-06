package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListTagsForResourceRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListTagsForResourceRequestMarshaller implements Marshaller<Request<ListTagsForResourceRequest>, ListTagsForResourceRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListTagsForResourceRequest> marshall(ListTagsForResourceRequest listTagsForResourceRequest) {
        if (listTagsForResourceRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listTagsForResourceRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listTagsForResourceRequest.getResourceArn() != null) {
                defaultRequest.addParameter("resourceArn", StringUtils.fromString(listTagsForResourceRequest.getResourceArn()));
            }
            if (listTagsForResourceRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listTagsForResourceRequest.getNextToken()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/tags", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListTagsForResourceRequest)");
    }
}
