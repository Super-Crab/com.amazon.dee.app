package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigningSignature;
import com.amazonaws.util.json.AwsJsonWriter;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
class CodeSigningSignatureJsonMarshaller {
    private static CodeSigningSignatureJsonMarshaller instance;

    CodeSigningSignatureJsonMarshaller() {
    }

    public static CodeSigningSignatureJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CodeSigningSignatureJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CodeSigningSignature codeSigningSignature, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (codeSigningSignature.getInlineDocument() != null) {
            ByteBuffer inlineDocument = codeSigningSignature.getInlineDocument();
            awsJsonWriter.name("inlineDocument");
            awsJsonWriter.value(inlineDocument);
        }
        awsJsonWriter.endObject();
    }
}
