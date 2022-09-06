package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachThingPrincipalRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class AttachThingPrincipalRequestMarshaller implements Marshaller<Request<AttachThingPrincipalRequest>, AttachThingPrincipalRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachThingPrincipalRequest> marshall(AttachThingPrincipalRequest attachThingPrincipalRequest) {
        if (attachThingPrincipalRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(attachThingPrincipalRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            if (attachThingPrincipalRequest.getPrincipal() != null) {
                defaultRequest.addHeader("x-amzn-principal", StringUtils.fromString(attachThingPrincipalRequest.getPrincipal()));
            }
            defaultRequest.setResourcePath("/things/{thingName}/principals".replace("{thingName}", attachThingPrincipalRequest.getThingName() == null ? "" : StringUtils.fromString(attachThingPrincipalRequest.getThingName())));
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(AttachThingPrincipalRequest)");
    }
}
