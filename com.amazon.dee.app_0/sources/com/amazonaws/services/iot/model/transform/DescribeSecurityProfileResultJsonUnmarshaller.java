package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeSecurityProfileResultJsonUnmarshaller implements Unmarshaller<DescribeSecurityProfileResult, JsonUnmarshallerContext> {
    private static DescribeSecurityProfileResultJsonUnmarshaller instance;

    public static DescribeSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeSecurityProfileResult describeSecurityProfileResult = new DescribeSecurityProfileResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("securityProfileName")) {
                describeSecurityProfileResult.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("securityProfileArn")) {
                describeSecurityProfileResult.setSecurityProfileArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("securityProfileDescription")) {
                describeSecurityProfileResult.setSecurityProfileDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("behaviors")) {
                describeSecurityProfileResult.setBehaviors(new ListUnmarshaller(BehaviorJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("alertTargets")) {
                describeSecurityProfileResult.setAlertTargets(new MapUnmarshaller(AlertTargetJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("version")) {
                describeSecurityProfileResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("creationDate")) {
                describeSecurityProfileResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lastModifiedDate")) {
                describeSecurityProfileResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeSecurityProfileResult;
    }
}
