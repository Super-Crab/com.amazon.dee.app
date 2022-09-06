package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeEventConfigurationsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class DescribeEventConfigurationsResultJsonUnmarshaller implements Unmarshaller<DescribeEventConfigurationsResult, JsonUnmarshallerContext> {
    private static DescribeEventConfigurationsResultJsonUnmarshaller instance;

    public static DescribeEventConfigurationsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeEventConfigurationsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeEventConfigurationsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeEventConfigurationsResult describeEventConfigurationsResult = new DescribeEventConfigurationsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("eventConfigurations")) {
                describeEventConfigurationsResult.setEventConfigurations(new MapUnmarshaller(ConfigurationJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("creationDate")) {
                describeEventConfigurationsResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lastModifiedDate")) {
                describeEventConfigurationsResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeEventConfigurationsResult;
    }
}
