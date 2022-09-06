package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class AbortConfigJsonUnmarshaller implements Unmarshaller<AbortConfig, JsonUnmarshallerContext> {
    private static AbortConfigJsonUnmarshaller instance;

    AbortConfigJsonUnmarshaller() {
    }

    public static AbortConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AbortConfigJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AbortConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AbortConfig abortConfig = new AbortConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("criteriaList")) {
                abortConfig.setCriteriaList(new ListUnmarshaller(AbortCriteriaJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return abortConfig;
    }
}
