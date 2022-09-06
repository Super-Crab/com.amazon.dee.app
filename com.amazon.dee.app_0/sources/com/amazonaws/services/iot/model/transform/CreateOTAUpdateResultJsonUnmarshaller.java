package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateOTAUpdateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateOTAUpdateResultJsonUnmarshaller implements Unmarshaller<CreateOTAUpdateResult, JsonUnmarshallerContext> {
    private static CreateOTAUpdateResultJsonUnmarshaller instance;

    public static CreateOTAUpdateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateOTAUpdateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateOTAUpdateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateOTAUpdateResult createOTAUpdateResult = new CreateOTAUpdateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("otaUpdateId")) {
                createOTAUpdateResult.setOtaUpdateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsIotJobId")) {
                createOTAUpdateResult.setAwsIotJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("otaUpdateArn")) {
                createOTAUpdateResult.setOtaUpdateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("awsIotJobArn")) {
                createOTAUpdateResult.setAwsIotJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("otaUpdateStatus")) {
                createOTAUpdateResult.setOtaUpdateStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createOTAUpdateResult;
    }
}
