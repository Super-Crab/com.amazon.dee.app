package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateThingGroupsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class UpdateThingGroupsForThingRequestMarshaller implements Marshaller<Request<UpdateThingGroupsForThingRequest>, UpdateThingGroupsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateThingGroupsForThingRequest> marshall(UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest) {
        if (updateThingGroupsForThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateThingGroupsForThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/thing-groups/updateThingGroupsForThing");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateThingGroupsForThingRequest.getThingName() != null) {
                    String thingName = updateThingGroupsForThingRequest.getThingName();
                    jsonWriter.name("thingName");
                    jsonWriter.value(thingName);
                }
                if (updateThingGroupsForThingRequest.getThingGroupsToAdd() != null) {
                    List<String> thingGroupsToAdd = updateThingGroupsForThingRequest.getThingGroupsToAdd();
                    jsonWriter.name("thingGroupsToAdd");
                    jsonWriter.beginArray();
                    for (String str : thingGroupsToAdd) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (updateThingGroupsForThingRequest.getThingGroupsToRemove() != null) {
                    List<String> thingGroupsToRemove = updateThingGroupsForThingRequest.getThingGroupsToRemove();
                    jsonWriter.name("thingGroupsToRemove");
                    jsonWriter.beginArray();
                    for (String str2 : thingGroupsToRemove) {
                        if (str2 != null) {
                            jsonWriter.value(str2);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (updateThingGroupsForThingRequest.getOverrideDynamicGroups() != null) {
                    Boolean overrideDynamicGroups = updateThingGroupsForThingRequest.getOverrideDynamicGroups();
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateThingGroupsForThingRequest)");
    }
}
