package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
/* loaded from: classes13.dex */
public class UpdateThingShadowRequestMarshaller implements Marshaller<Request<UpdateThingShadowRequest>, UpdateThingShadowRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingShadowRequest> marshall(UpdateThingShadowRequest updateThingShadowRequest) {
        if (updateThingShadowRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateThingShadowRequest, "AWSIotData");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/things/{thingName}/shadow".replace("{thingName}", updateThingShadowRequest.getThingName() == null ? "" : StringUtils.fromString(updateThingShadowRequest.getThingName())));
            defaultRequest.addHeader("Content-Length", Integer.toString(updateThingShadowRequest.getPayload().remaining()));
            defaultRequest.setContent(BinaryUtils.toStream(updateThingShadowRequest.getPayload()));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingShadowRequest)");
    }
}
