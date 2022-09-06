package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigningCertificateChain;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class CodeSigningCertificateChainJsonUnmarshaller implements Unmarshaller<CodeSigningCertificateChain, JsonUnmarshallerContext> {
    private static CodeSigningCertificateChainJsonUnmarshaller instance;

    CodeSigningCertificateChainJsonUnmarshaller() {
    }

    public static CodeSigningCertificateChainJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CodeSigningCertificateChainJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CodeSigningCertificateChain unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CodeSigningCertificateChain codeSigningCertificateChain = new CodeSigningCertificateChain();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("certificateName")) {
                codeSigningCertificateChain.setCertificateName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("inlineDocument")) {
                codeSigningCertificateChain.setInlineDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return codeSigningCertificateChain;
    }
}
