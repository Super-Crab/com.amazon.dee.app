package com.amazon.communication.serialize;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferIonDeserializer extends JsonDeserializer<ByteBuffer> {
    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: avoid collision after fix types in other method */
    public ByteBuffer mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        try {
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject instanceof byte[]) {
                    return ByteBuffer.wrap((byte[]) embeddedObject);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                return ByteBuffer.wrap(jsonParser.getBinaryValue());
            }
            throw new JsonMappingException("Unexpected token: " + currentToken, jsonParser.getCurrentLocation());
        } catch (IllegalArgumentException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("not a valid representation (error: ");
            outline107.append(e.getMessage());
            outline107.append(")");
            throw deserializationContext.weirdStringException(ByteBuffer.class, outline107.toString());
        }
    }
}
