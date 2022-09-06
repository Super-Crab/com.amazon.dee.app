package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AddThingToBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class AddThingToBillingGroupRequestMarshaller implements Marshaller<Request<AddThingToBillingGroupRequest>, AddThingToBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AddThingToBillingGroupRequest> marshall(AddThingToBillingGroupRequest addThingToBillingGroupRequest) {
        if (addThingToBillingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(addThingToBillingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/billing-groups/addThingToBillingGroup");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (addThingToBillingGroupRequest.getBillingGroupName() != null) {
                    String billingGroupName = addThingToBillingGroupRequest.getBillingGroupName();
                    jsonWriter.name("billingGroupName");
                    jsonWriter.value(billingGroupName);
                }
                if (addThingToBillingGroupRequest.getBillingGroupArn() != null) {
                    String billingGroupArn = addThingToBillingGroupRequest.getBillingGroupArn();
                    jsonWriter.name("billingGroupArn");
                    jsonWriter.value(billingGroupArn);
                }
                if (addThingToBillingGroupRequest.getThingName() != null) {
                    String thingName = addThingToBillingGroupRequest.getThingName();
                    jsonWriter.name("thingName");
                    jsonWriter.value(thingName);
                }
                if (addThingToBillingGroupRequest.getThingArn() != null) {
                    String thingArn = addThingToBillingGroupRequest.getThingArn();
                    jsonWriter.name("thingArn");
                    jsonWriter.value(thingArn);
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
        throw new AmazonClientException("Invalid argument passed to marshall(AddThingToBillingGroupRequest)");
    }
}
