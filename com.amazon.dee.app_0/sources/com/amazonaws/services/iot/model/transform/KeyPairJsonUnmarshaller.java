package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.KeyPair;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class KeyPairJsonUnmarshaller implements Unmarshaller<KeyPair, JsonUnmarshallerContext> {
    private static KeyPairJsonUnmarshaller instance;

    KeyPairJsonUnmarshaller() {
    }

    public static KeyPairJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new KeyPairJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public KeyPair unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        KeyPair keyPair = new KeyPair();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("PublicKey")) {
                keyPair.setPublicKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("PrivateKey")) {
                keyPair.setPrivateKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return keyPair;
    }
}
