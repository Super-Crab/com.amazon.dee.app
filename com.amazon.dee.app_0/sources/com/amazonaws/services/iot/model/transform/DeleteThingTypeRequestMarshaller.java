package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteThingTypeRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteThingTypeRequestMarshaller implements Marshaller<Request<DeleteThingTypeRequest>, DeleteThingTypeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingTypeRequest> marshall(DeleteThingTypeRequest deleteThingTypeRequest) {
        if (deleteThingTypeRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteThingTypeRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/thing-types/{thingTypeName}", "{thingTypeName}", deleteThingTypeRequest.getThingTypeName() == null ? "" : StringUtils.fromString(deleteThingTypeRequest.getThingTypeName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingTypeRequest)");
    }
}
