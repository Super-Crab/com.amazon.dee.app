package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
/* loaded from: classes13.dex */
public class JavaTypeDeserializer extends StdScalarDeserializer<JavaType> {
    public JavaTypeDeserializer() {
        super(JavaType.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public JavaType mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return getEmptyValue();
            }
            return deserializationContext.getTypeFactory().constructFromCanonical(trim);
        } else if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return (JavaType) jsonParser.getEmbeddedObject();
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }
}
