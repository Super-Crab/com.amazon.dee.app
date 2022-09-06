package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class RemoveThingFromBillingGroupRequestMarshaller implements Marshaller<Request<RemoveThingFromBillingGroupRequest>, RemoveThingFromBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RemoveThingFromBillingGroupRequest> marshall(RemoveThingFromBillingGroupRequest removeThingFromBillingGroupRequest) {
        if (removeThingFromBillingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(removeThingFromBillingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/billing-groups/removeThingFromBillingGroup");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (removeThingFromBillingGroupRequest.getBillingGroupName() != null) {
                    String billingGroupName = removeThingFromBillingGroupRequest.getBillingGroupName();
                    jsonWriter.name("billingGroupName");
                    jsonWriter.value(billingGroupName);
                }
                if (removeThingFromBillingGroupRequest.getBillingGroupArn() != null) {
                    String billingGroupArn = removeThingFromBillingGroupRequest.getBillingGroupArn();
                    jsonWriter.name("billingGroupArn");
                    jsonWriter.value(billingGroupArn);
                }
                if (removeThingFromBillingGroupRequest.getThingName() != null) {
                    String thingName = removeThingFromBillingGroupRequest.getThingName();
                    jsonWriter.name("thingName");
                    jsonWriter.value(thingName);
                }
                if (removeThingFromBillingGroupRequest.getThingArn() != null) {
                    String thingArn = removeThingFromBillingGroupRequest.getThingArn();
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
        throw new AmazonClientException("Invalid argument passed to marshall(RemoveThingFromBillingGroupRequest)");
    }
}
