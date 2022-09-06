package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeAccountAuditConfigurationResultJsonUnmarshaller implements Unmarshaller<DescribeAccountAuditConfigurationResult, JsonUnmarshallerContext> {
    private static DescribeAccountAuditConfigurationResultJsonUnmarshaller instance;

    public static DescribeAccountAuditConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeAccountAuditConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeAccountAuditConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeAccountAuditConfigurationResult describeAccountAuditConfigurationResult = new DescribeAccountAuditConfigurationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("roleArn")) {
                describeAccountAuditConfigurationResult.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("auditNotificationTargetConfigurations")) {
                describeAccountAuditConfigurationResult.setAuditNotificationTargetConfigurations(new MapUnmarshaller(AuditNotificationTargetJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("auditCheckConfigurations")) {
                describeAccountAuditConfigurationResult.setAuditCheckConfigurations(new MapUnmarshaller(AuditCheckConfigurationJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeAccountAuditConfigurationResult;
    }
}
