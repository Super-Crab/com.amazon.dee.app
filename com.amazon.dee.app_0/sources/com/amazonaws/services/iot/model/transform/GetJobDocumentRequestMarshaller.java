package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetJobDocumentRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class GetJobDocumentRequestMarshaller implements Marshaller<Request<GetJobDocumentRequest>, GetJobDocumentRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetJobDocumentRequest> marshall(GetJobDocumentRequest getJobDocumentRequest) {
        if (getJobDocumentRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getJobDocumentRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/jobs/{jobId}/job-document", "{jobId}", getJobDocumentRequest.getJobId() == null ? "" : StringUtils.fromString(getJobDocumentRequest.getJobId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetJobDocumentRequest)");
    }
}
