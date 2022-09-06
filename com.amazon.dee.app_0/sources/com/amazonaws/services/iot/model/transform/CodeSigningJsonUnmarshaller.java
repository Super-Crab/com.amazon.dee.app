package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigning;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class CodeSigningJsonUnmarshaller implements Unmarshaller<CodeSigning, JsonUnmarshallerContext> {
    private static CodeSigningJsonUnmarshaller instance;

    CodeSigningJsonUnmarshaller() {
    }

    public static CodeSigningJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CodeSigningJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CodeSigning unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CodeSigning codeSigning = new CodeSigning();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("awsSignerJobId")) {
                codeSigning.setAwsSignerJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("startSigningJobParameter")) {
                codeSigning.setStartSigningJobParameter(StartSigningJobParameterJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("customCodeSigning")) {
                codeSigning.setCustomCodeSigning(CustomCodeSigningJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return codeSigning;
    }
}
