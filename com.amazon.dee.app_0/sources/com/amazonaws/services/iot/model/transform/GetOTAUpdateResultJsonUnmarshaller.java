package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetOTAUpdateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class GetOTAUpdateResultJsonUnmarshaller implements Unmarshaller<GetOTAUpdateResult, JsonUnmarshallerContext> {
    private static GetOTAUpdateResultJsonUnmarshaller instance;

    public static GetOTAUpdateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetOTAUpdateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GetOTAUpdateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetOTAUpdateResult getOTAUpdateResult = new GetOTAUpdateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("otaUpdateInfo")) {
                getOTAUpdateResult.setOtaUpdateInfo(OTAUpdateInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getOTAUpdateResult;
    }
}
