package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeprecateThingTypeRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class DeprecateThingTypeRequestMarshaller implements Marshaller<Request<DeprecateThingTypeRequest>, DeprecateThingTypeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeprecateThingTypeRequest> marshall(DeprecateThingTypeRequest deprecateThingTypeRequest) {
        if (deprecateThingTypeRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deprecateThingTypeRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/thing-types/{thingTypeName}/deprecate".replace("{thingTypeName}", deprecateThingTypeRequest.getThingTypeName() == null ? "" : StringUtils.fromString(deprecateThingTypeRequest.getThingTypeName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (deprecateThingTypeRequest.getUndoDeprecate() != null) {
                    Boolean undoDeprecate = deprecateThingTypeRequest.getUndoDeprecate();
                    jsonWriter.name("undoDeprecate");
                    jsonWriter.value(undoDeprecate.booleanValue());
                }
                jsonWriter.endObject();
                jsonWriter.close();
                String stringWriter2 = stringWriter.toString();
                byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
                defaultRequest.setContent(new StringInputStream(stringWriter2));
                defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
                if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeprecateThingTypeRequest)");
    }
}
