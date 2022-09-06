package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.Base64Variants;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class StringDeserializer extends StdScalarDeserializer<String> {
    public StringDeserializer() {
        super(String.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public String mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            return jsonParser.getText();
        }
        if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            if (embeddedObject instanceof byte[]) {
                return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false);
            }
            return embeddedObject.toString();
        } else if (currentToken.isScalarValue()) {
            return jsonParser.getText();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType  reason: collision with other method in class */
    public String mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return mo4206deserialize(jsonParser, deserializationContext);
    }
}
