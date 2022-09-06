package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetIndexingConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetIndexingConfigurationResultJsonUnmarshaller implements Unmarshaller<GetIndexingConfigurationResult, JsonUnmarshallerContext> {
    private static GetIndexingConfigurationResultJsonUnmarshaller instance;

    public static GetIndexingConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetIndexingConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetIndexingConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetIndexingConfigurationResult getIndexingConfigurationResult = new GetIndexingConfigurationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingIndexingConfiguration")) {
                getIndexingConfigurationResult.setThingIndexingConfiguration(ThingIndexingConfigurationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingGroupIndexingConfiguration")) {
                getIndexingConfigurationResult.setThingGroupIndexingConfiguration(ThingGroupIndexingConfigurationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getIndexingConfigurationResult;
    }
}
