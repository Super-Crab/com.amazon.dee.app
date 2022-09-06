package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.ContentSignature;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ContentSignatureDeserializer implements JsonDeserializer<ContentSignature> {
    public static final JsonDeserializer<ContentSignature> INSTANCE = new ContentSignatureDeserializer();

    private ContentSignatureDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ContentSignature mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        String str = null;
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            String str2 = null;
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        boolean z = true;
                        if ("contentSignatureType".equals(currentName)) {
                            str = SimpleDeserializers.deserializeString(jsonParser);
                        } else if ("contentSignature".equals(currentName)) {
                            str2 = SimpleDeserializers.deserializeString(jsonParser);
                        } else {
                            z = false;
                        }
                        if (!z) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return new ContentSignature(str, str2);
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
