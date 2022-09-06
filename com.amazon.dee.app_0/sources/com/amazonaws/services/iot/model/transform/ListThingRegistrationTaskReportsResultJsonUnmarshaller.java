package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListThingRegistrationTaskReportsResultJsonUnmarshaller implements Unmarshaller<ListThingRegistrationTaskReportsResult, JsonUnmarshallerContext> {
    private static ListThingRegistrationTaskReportsResultJsonUnmarshaller instance;

    public static ListThingRegistrationTaskReportsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListThingRegistrationTaskReportsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListThingRegistrationTaskReportsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListThingRegistrationTaskReportsResult listThingRegistrationTaskReportsResult = new ListThingRegistrationTaskReportsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("resourceLinks")) {
                listThingRegistrationTaskReportsResult.setResourceLinks(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("reportType")) {
                listThingRegistrationTaskReportsResult.setReportType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listThingRegistrationTaskReportsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listThingRegistrationTaskReportsResult;
    }
}
