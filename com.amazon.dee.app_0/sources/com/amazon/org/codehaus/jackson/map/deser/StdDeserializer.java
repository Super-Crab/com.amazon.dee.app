package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.Base64Variants;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
@Deprecated
/* loaded from: classes13.dex */
public abstract class StdDeserializer<T> extends com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer<T> {

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public class CalendarDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.CalendarDeserializer {
        public CalendarDeserializer() {
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public class ClassDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.ClassDeserializer {
        public ClassDeserializer() {
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes13.dex */
    public static final class StringDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer<String> {
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

    protected StdDeserializer(Class<?> cls) {
        super(cls);
    }

    protected StdDeserializer(JavaType javaType) {
        super(javaType);
    }
}
