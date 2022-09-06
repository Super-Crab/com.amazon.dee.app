package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteStreamRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteStreamRequestMarshaller implements Marshaller<Request<DeleteStreamRequest>, DeleteStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteStreamRequest> marshall(DeleteStreamRequest deleteStreamRequest) {
        if (deleteStreamRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteStreamRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/streams/{streamId}", "{streamId}", deleteStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(deleteStreamRequest.getStreamId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteStreamRequest)");
    }
}
