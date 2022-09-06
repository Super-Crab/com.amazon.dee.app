package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigningCertificateChain;
import com.amazonaws.services.iot.model.CodeSigningSignature;
import com.amazonaws.services.iot.model.CustomCodeSigning;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class CustomCodeSigningJsonMarshaller {
    private static CustomCodeSigningJsonMarshaller instance;

    CustomCodeSigningJsonMarshaller() {
    }

    public static CustomCodeSigningJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CustomCodeSigningJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CustomCodeSigning customCodeSigning, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (customCodeSigning.getSignature() != null) {
            CodeSigningSignature signature = customCodeSigning.getSignature();
            awsJsonWriter.name("signature");
            CodeSigningSignatureJsonMarshaller.getInstance().marshall(signature, awsJsonWriter);
        }
        if (customCodeSigning.getCertificateChain() != null) {
            CodeSigningCertificateChain certificateChain = customCodeSigning.getCertificateChain();
            awsJsonWriter.name("certificateChain");
            CodeSigningCertificateChainJsonMarshaller.getInstance().marshall(certificateChain, awsJsonWriter);
        }
        if (customCodeSigning.getHashAlgorithm() != null) {
            String hashAlgorithm = customCodeSigning.getHashAlgorithm();
            awsJsonWriter.name("hashAlgorithm");
            awsJsonWriter.value(hashAlgorithm);
        }
        if (customCodeSigning.getSignatureAlgorithm() != null) {
            String signatureAlgorithm = customCodeSigning.getSignatureAlgorithm();
            awsJsonWriter.name("signatureAlgorithm");
            awsJsonWriter.value(signatureAlgorithm);
        }
        awsJsonWriter.endObject();
    }
}
