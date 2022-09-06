package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AddThingToThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class AddThingToThingGroupRequestMarshaller implements Marshaller<Request<AddThingToThingGroupRequest>, AddThingToThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AddThingToThingGroupRequest> marshall(AddThingToThingGroupRequest addThingToThingGroupRequest) {
        if (addThingToThingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(addThingToThingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/thing-groups/addThingToThingGroup");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (addThingToThingGroupRequest.getThingGroupName() != null) {
                    String thingGroupName = addThingToThingGroupRequest.getThingGroupName();
                    jsonWriter.name("thingGroupName");
                    jsonWriter.value(thingGroupName);
                }
                if (addThingToThingGroupRequest.getThingGroupArn() != null) {
                    String thingGroupArn = addThingToThingGroupRequest.getThingGroupArn();
                    jsonWriter.name("thingGroupArn");
                    jsonWriter.value(thingGroupArn);
                }
                if (addThingToThingGroupRequest.getThingName() != null) {
                    String thingName = addThingToThingGroupRequest.getThingName();
                    jsonWriter.name("thingName");
                    jsonWriter.value(thingName);
                }
                if (addThingToThingGroupRequest.getThingArn() != null) {
                    String thingArn = addThingToThingGroupRequest.getThingArn();
                    jsonWriter.name("thingArn");
                    jsonWriter.value(thingArn);
                }
                if (addThingToThingGroupRequest.getOverrideDynamicGroups() != null) {
                    Boolean overrideDynamicGroups = addThingToThingGroupRequest.getOverrideDynamicGroups();
                    jsonWriter.name("overrideDynamicGroups");
                    jsonWriter.value(overrideDynamicGroups.booleanValue());
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
        throw new AmazonClientException("Invalid argument passed to marshall(AddThingToThingGroupRequest)");
    }
}
