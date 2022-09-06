package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
    public ClassDeserializer() {
        super(Class.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public Class<?> mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            if (text.indexOf(46) < 0) {
                if ("int".equals(text)) {
                    return Integer.TYPE;
                }
                if ("long".equals(text)) {
                    return Long.TYPE;
                }
                if ("float".equals(text)) {
                    return Float.TYPE;
                }
                if ("double".equals(text)) {
                    return Double.TYPE;
                }
                if ("boolean".equals(text)) {
                    return Boolean.TYPE;
                }
                if ("byte".equals(text)) {
                    return Byte.TYPE;
                }
                if ("char".equals(text)) {
                    return Character.TYPE;
                }
                if ("short".equals(text)) {
                    return Short.TYPE;
                }
                if ("void".equals(text)) {
                    return Void.TYPE;
                }
            }
            try {
                return Class.forName(jsonParser.getText());
            } catch (ClassNotFoundException e) {
                throw deserializationContext.instantiationException(this._valueClass, e);
            }
        }
        throw deserializationContext.mappingException(this._valueClass, currentToken);
    }
}
