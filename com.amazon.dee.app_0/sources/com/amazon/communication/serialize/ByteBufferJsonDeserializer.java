package com.amazon.communication.serialize;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferJsonDeserializer extends JsonDeserializer<ByteBuffer> {
    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: avoid collision after fix types in other method */
    public ByteBuffer mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (JsonToken.VALUE_STRING == currentToken) {
            return ByteBuffer.wrap(jsonParser.getBinaryValue(deserializationContext.getBase64Variant()));
        }
        throw new JsonMappingException("Unexpected token: " + currentToken, jsonParser.getCurrentLocation());
    }
}
