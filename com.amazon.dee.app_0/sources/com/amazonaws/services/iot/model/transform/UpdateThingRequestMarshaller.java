package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttributePayload;
import com.amazonaws.services.iot.model.UpdateThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class UpdateThingRequestMarshaller implements Marshaller<Request<UpdateThingRequest>, UpdateThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingRequest> marshall(UpdateThingRequest updateThingRequest) {
        if (updateThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            defaultRequest.setResourcePath("/things/{thingName}".replace("{thingName}", updateThingRequest.getThingName() == null ? "" : StringUtils.fromString(updateThingRequest.getThingName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateThingRequest.getThingTypeName() != null) {
                    String thingTypeName = updateThingRequest.getThingTypeName();
                    jsonWriter.name("thingTypeName");
                    jsonWriter.value(thingTypeName);
                }
                if (updateThingRequest.getAttributePayload() != null) {
                    AttributePayload attributePayload = updateThingRequest.getAttributePayload();
                    jsonWriter.name("attributePayload");
                    AttributePayloadJsonMarshaller.getInstance().marshall(attributePayload, jsonWriter);
                }
                if (updateThingRequest.getExpectedVersion() != null) {
                    Long expectedVersion = updateThingRequest.getExpectedVersion();
                    jsonWriter.name("expectedVersion");
                    jsonWriter.value(expectedVersion);
                }
                if (updateThingRequest.getRemoveThingType() != null) {
                    Boolean removeThingType = updateThingRequest.getRemoveThingType();
                    jsonWriter.name("removeThingType");
                    jsonWriter.value(removeThingType.booleanValue());
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingRequest)");
    }
}
