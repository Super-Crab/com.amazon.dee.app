package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.SetV2LoggingOptionsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class SetV2LoggingOptionsRequestMarshaller implements Marshaller<Request<SetV2LoggingOptionsRequest>, SetV2LoggingOptionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SetV2LoggingOptionsRequest> marshall(SetV2LoggingOptionsRequest setV2LoggingOptionsRequest) {
        if (setV2LoggingOptionsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(setV2LoggingOptionsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/v2LoggingOptions");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (setV2LoggingOptionsRequest.getRoleArn() != null) {
                    String roleArn = setV2LoggingOptionsRequest.getRoleArn();
                    jsonWriter.name("roleArn");
                    jsonWriter.value(roleArn);
                }
                if (setV2LoggingOptionsRequest.getDefaultLogLevel() != null) {
                    String defaultLogLevel = setV2LoggingOptionsRequest.getDefaultLogLevel();
                    jsonWriter.name("defaultLogLevel");
                    jsonWriter.value(defaultLogLevel);
                }
                if (setV2LoggingOptionsRequest.getDisableAllLogs() != null) {
                    Boolean disableAllLogs = setV2LoggingOptionsRequest.getDisableAllLogs();
                    jsonWriter.name("disableAllLogs");
                    jsonWriter.value(disableAllLogs.booleanValue());
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
        throw new AmazonClientException("Invalid argument passed to marshall(SetV2LoggingOptionsRequest)");
    }
}
