package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListV2LoggingLevelsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListV2LoggingLevelsResultJsonUnmarshaller implements Unmarshaller<ListV2LoggingLevelsResult, JsonUnmarshallerContext> {
    private static ListV2LoggingLevelsResultJsonUnmarshaller instance;

    public static ListV2LoggingLevelsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListV2LoggingLevelsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListV2LoggingLevelsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListV2LoggingLevelsResult listV2LoggingLevelsResult = new ListV2LoggingLevelsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("logTargetConfigurations")) {
                listV2LoggingLevelsResult.setLogTargetConfigurations(new ListUnmarshaller(LogTargetConfigurationJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listV2LoggingLevelsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listV2LoggingLevelsResult;
    }
}
