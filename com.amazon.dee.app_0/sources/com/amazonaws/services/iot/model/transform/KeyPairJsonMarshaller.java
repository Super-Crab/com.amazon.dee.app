package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.KeyPair;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class KeyPairJsonMarshaller {
    private static KeyPairJsonMarshaller instance;

    KeyPairJsonMarshaller() {
    }

    public static KeyPairJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new KeyPairJsonMarshaller();
        }
        return instance;
    }

    public void marshall(KeyPair keyPair, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (keyPair.getPublicKey() != null) {
            String publicKey = keyPair.getPublicKey();
            awsJsonWriter.name("PublicKey");
            awsJsonWriter.value(publicKey);
        }
        if (keyPair.getPrivateKey() != null) {
            String privateKey = keyPair.getPrivateKey();
            awsJsonWriter.name("PrivateKey");
            awsJsonWriter.value(privateKey);
        }
        awsJsonWriter.endObject();
    }
}
