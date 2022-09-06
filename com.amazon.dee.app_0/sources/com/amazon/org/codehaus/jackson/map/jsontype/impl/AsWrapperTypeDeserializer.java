package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public class AsWrapperTypeDeserializer extends TypeDeserializerBase {
    @Deprecated
    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        JsonToken jsonToken = JsonToken.START_OBJECT;
        if (currentToken == jsonToken) {
            JsonToken nextToken = jsonParser.nextToken();
            JsonToken jsonToken2 = JsonToken.FIELD_NAME;
            if (nextToken == jsonToken2) {
                JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext, jsonParser.getText());
                jsonParser.nextToken();
                Object mo4206deserialize = _findDeserializer.mo4206deserialize(jsonParser, deserializationContext);
                JsonToken nextToken2 = jsonParser.nextToken();
                JsonToken jsonToken3 = JsonToken.END_OBJECT;
                if (nextToken2 != jsonToken3) {
                    throw deserializationContext.wrongTokenException(jsonParser, jsonToken3, "expected closing END_OBJECT after type information and deserialized value");
                }
                return mo4206deserialize;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("need JSON String that contains type id (for subtype of ");
            outline107.append(baseTypeName());
            outline107.append(")");
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken2, outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("need JSON Object to contain As.WRAPPER_OBJECT type information for class ");
        outline1072.append(baseTypeName());
        throw deserializationContext.wrongTokenException(jsonParser, jsonToken, outline1072.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.WRAPPER_OBJECT;
    }

    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls) {
        super(javaType, typeIdResolver, beanProperty, null);
    }
}
