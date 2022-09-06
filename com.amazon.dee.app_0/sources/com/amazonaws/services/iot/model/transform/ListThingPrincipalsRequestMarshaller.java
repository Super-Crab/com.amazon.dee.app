package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingPrincipalsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingPrincipalsRequestMarshaller implements Marshaller<Request<ListThingPrincipalsRequest>, ListThingPrincipalsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingPrincipalsRequest> marshall(ListThingPrincipalsRequest listThingPrincipalsRequest) {
        if (listThingPrincipalsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingPrincipalsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/things/{thingName}/principals", "{thingName}", listThingPrincipalsRequest.getThingName() == null ? "" : StringUtils.fromString(listThingPrincipalsRequest.getThingName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingPrincipalsRequest)");
    }
}
