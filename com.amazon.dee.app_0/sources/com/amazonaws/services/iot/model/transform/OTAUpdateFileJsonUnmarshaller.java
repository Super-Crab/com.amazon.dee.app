package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OTAUpdateFile;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class OTAUpdateFileJsonUnmarshaller implements Unmarshaller<OTAUpdateFile, JsonUnmarshallerContext> {
    private static OTAUpdateFileJsonUnmarshaller instance;

    OTAUpdateFileJsonUnmarshaller() {
    }

    public static OTAUpdateFileJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateFileJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OTAUpdateFile unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OTAUpdateFile oTAUpdateFile = new OTAUpdateFile();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("fileName")) {
                oTAUpdateFile.setFileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("fileVersion")) {
                oTAUpdateFile.setFileVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("fileLocation")) {
                oTAUpdateFile.setFileLocation(FileLocationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("codeSigning")) {
                oTAUpdateFile.setCodeSigning(CodeSigningJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("attributes")) {
                oTAUpdateFile.setAttributes(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return oTAUpdateFile;
    }
}
