package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.ContentSignature;
import com.amazon.clouddrive.model.ContentSignatures;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ContentSignaturesDeserializer implements JsonDeserializer<ContentSignatures> {
    public static final JsonDeserializer<ContentSignatures> INSTANCE = new ContentSignaturesDeserializer();

    private ContentSignaturesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ContentSignatures mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_ARRAY) {
            HashMap hashMap = new HashMap();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (!jsonParser.isClosed()) {
                    ContentSignature mo3229deserialize = ContentSignatureDeserializer.INSTANCE.mo3229deserialize(jsonParser);
                    hashMap.put(mo3229deserialize.getContentSignatureType(), mo3229deserialize.getContentSignature());
                } else {
                    throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                }
            }
            return new ContentSignatures(hashMap);
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of array, got ", currentToken), jsonParser.getTokenLocation());
    }
}
