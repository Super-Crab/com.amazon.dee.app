package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetachThingPrincipalRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DetachThingPrincipalRequestMarshaller implements Marshaller<Request<DetachThingPrincipalRequest>, DetachThingPrincipalRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DetachThingPrincipalRequest> marshall(DetachThingPrincipalRequest detachThingPrincipalRequest) {
        if (detachThingPrincipalRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(detachThingPrincipalRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (detachThingPrincipalRequest.getPrincipal() != null) {
                defaultRequest.addHeader("x-amzn-principal", StringUtils.fromString(detachThingPrincipalRequest.getPrincipal()));
            }
            if (!GeneratedOutlineSupport1.outline190("/things/{thingName}/principals", "{thingName}", detachThingPrincipalRequest.getThingName() == null ? "" : StringUtils.fromString(detachThingPrincipalRequest.getThingName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DetachThingPrincipalRequest)");
    }
}
