package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.DeleteThingShadowRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteThingShadowRequestMarshaller implements Marshaller<Request<DeleteThingShadowRequest>, DeleteThingShadowRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingShadowRequest> marshall(DeleteThingShadowRequest deleteThingShadowRequest) {
        if (deleteThingShadowRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteThingShadowRequest, "AWSIotData");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/things/{thingName}/shadow", "{thingName}", deleteThingShadowRequest.getThingName() == null ? "" : StringUtils.fromString(deleteThingShadowRequest.getThingName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingShadowRequest)");
    }
}
