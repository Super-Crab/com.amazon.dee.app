package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetOTAUpdateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class GetOTAUpdateRequestMarshaller implements Marshaller<Request<GetOTAUpdateRequest>, GetOTAUpdateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetOTAUpdateRequest> marshall(GetOTAUpdateRequest getOTAUpdateRequest) {
        if (getOTAUpdateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getOTAUpdateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/otaUpdates/{otaUpdateId}", "{otaUpdateId}", getOTAUpdateRequest.getOtaUpdateId() == null ? "" : StringUtils.fromString(getOTAUpdateRequest.getOtaUpdateId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetOTAUpdateRequest)");
    }
}
