package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigningCertificateChain;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class CodeSigningCertificateChainJsonMarshaller {
    private static CodeSigningCertificateChainJsonMarshaller instance;

    CodeSigningCertificateChainJsonMarshaller() {
    }

    public static CodeSigningCertificateChainJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CodeSigningCertificateChainJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CodeSigningCertificateChain codeSigningCertificateChain, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (codeSigningCertificateChain.getCertificateName() != null) {
            String certificateName = codeSigningCertificateChain.getCertificateName();
            awsJsonWriter.name("certificateName");
            awsJsonWriter.value(certificateName);
        }
        if (codeSigningCertificateChain.getInlineDocument() != null) {
            String inlineDocument = codeSigningCertificateChain.getInlineDocument();
            awsJsonWriter.name("inlineDocument");
            awsJsonWriter.value(inlineDocument);
        }
        awsJsonWriter.endObject();
    }
}
