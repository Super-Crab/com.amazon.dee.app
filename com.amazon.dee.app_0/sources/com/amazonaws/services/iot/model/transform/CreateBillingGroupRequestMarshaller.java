package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.BillingGroupProperties;
import com.amazonaws.services.iot.model.CreateBillingGroupRequest;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateBillingGroupRequestMarshaller implements Marshaller<Request<CreateBillingGroupRequest>, CreateBillingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateBillingGroupRequest> marshall(CreateBillingGroupRequest createBillingGroupRequest) {
        if (createBillingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createBillingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/billing-groups/{billingGroupName}".replace("{billingGroupName}", createBillingGroupRequest.getBillingGroupName() == null ? "" : StringUtils.fromString(createBillingGroupRequest.getBillingGroupName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createBillingGroupRequest.getBillingGroupProperties() != null) {
                    BillingGroupProperties billingGroupProperties = createBillingGroupRequest.getBillingGroupProperties();
                    jsonWriter.name("billingGroupProperties");
                    BillingGroupPropertiesJsonMarshaller.getInstance().marshall(billingGroupProperties, jsonWriter);
                }
                if (createBillingGroupRequest.getTags() != null) {
                    List<Tag> tags = createBillingGroupRequest.getTags();
                    jsonWriter.name("tags");
                    jsonWriter.beginArray();
                    for (Tag tag : tags) {
                        if (tag != null) {
                            TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateBillingGroupRequest)");
    }
}
