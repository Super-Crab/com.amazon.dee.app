package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttributePayload;
import com.amazonaws.services.iot.model.CreateThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class CreateThingRequestMarshaller implements Marshaller<Request<CreateThingRequest>, CreateThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateThingRequest> marshall(CreateThingRequest createThingRequest) {
        if (createThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/things/{thingName}".replace("{thingName}", createThingRequest.getThingName() == null ? "" : StringUtils.fromString(createThingRequest.getThingName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createThingRequest.getThingTypeName() != null) {
                    String thingTypeName = createThingRequest.getThingTypeName();
                    jsonWriter.name("thingTypeName");
                    jsonWriter.value(thingTypeName);
                }
                if (createThingRequest.getAttributePayload() != null) {
                    AttributePayload attributePayload = createThingRequest.getAttributePayload();
                    jsonWriter.name("attributePayload");
                    AttributePayloadJsonMarshaller.getInstance().marshall(attributePayload, jsonWriter);
                }
                if (createThingRequest.getBillingGroupName() != null) {
                    String billingGroupName = createThingRequest.getBillingGroupName();
                    jsonWriter.name("billingGroupName");
                    jsonWriter.value(billingGroupName);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateThingRequest)");
    }
}
