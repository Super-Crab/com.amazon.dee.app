package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ThingGroupIndexingConfiguration;
import com.amazonaws.services.iot.model.ThingIndexingConfiguration;
import com.amazonaws.services.iot.model.UpdateIndexingConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class UpdateIndexingConfigurationRequestMarshaller implements Marshaller<Request<UpdateIndexingConfigurationRequest>, UpdateIndexingConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateIndexingConfigurationRequest> marshall(UpdateIndexingConfigurationRequest updateIndexingConfigurationRequest) {
        if (updateIndexingConfigurationRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateIndexingConfigurationRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/indexing/config");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateIndexingConfigurationRequest.getThingIndexingConfiguration() != null) {
                    ThingIndexingConfiguration thingIndexingConfiguration = updateIndexingConfigurationRequest.getThingIndexingConfiguration();
                    jsonWriter.name("thingIndexingConfiguration");
                    ThingIndexingConfigurationJsonMarshaller.getInstance().marshall(thingIndexingConfiguration, jsonWriter);
                }
                if (updateIndexingConfigurationRequest.getThingGroupIndexingConfiguration() != null) {
                    ThingGroupIndexingConfiguration thingGroupIndexingConfiguration = updateIndexingConfigurationRequest.getThingGroupIndexingConfiguration();
                    jsonWriter.name("thingGroupIndexingConfiguration");
                    ThingGroupIndexingConfigurationJsonMarshaller.getInstance().marshall(thingGroupIndexingConfiguration, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateIndexingConfigurationRequest)");
    }
}
