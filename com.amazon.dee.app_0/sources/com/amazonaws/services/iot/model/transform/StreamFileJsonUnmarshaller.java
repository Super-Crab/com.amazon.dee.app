package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StreamFile;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class StreamFileJsonUnmarshaller implements Unmarshaller<StreamFile, JsonUnmarshallerContext> {
    private static StreamFileJsonUnmarshaller instance;

    StreamFileJsonUnmarshaller() {
    }

    public static StreamFileJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StreamFileJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StreamFile unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        StreamFile streamFile = new StreamFile();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("fileId")) {
                streamFile.setFileId(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("s3Location")) {
                streamFile.setS3Location(S3LocationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return streamFile;
    }
}
