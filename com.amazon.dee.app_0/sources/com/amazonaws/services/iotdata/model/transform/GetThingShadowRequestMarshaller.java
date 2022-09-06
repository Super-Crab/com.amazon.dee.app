package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class GetThingShadowRequestMarshaller implements Marshaller<Request<GetThingShadowRequest>, GetThingShadowRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetThingShadowRequest> marshall(GetThingShadowRequest getThingShadowRequest) {
        if (getThingShadowRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getThingShadowRequest, "AWSIotData");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/things/{thingName}/shadow", "{thingName}", getThingShadowRequest.getThingName() == null ? "" : StringUtils.fromString(getThingShadowRequest.getThingName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetThingShadowRequest)");
    }
}
