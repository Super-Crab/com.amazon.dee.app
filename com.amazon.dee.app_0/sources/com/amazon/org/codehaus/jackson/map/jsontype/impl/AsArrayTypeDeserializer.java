package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public class AsArrayTypeDeserializer extends TypeDeserializerBase {
    @Deprecated
    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        boolean isExpectedStartArrayToken = jsonParser.isExpectedStartArrayToken();
        Object mo4206deserialize = _findDeserializer(deserializationContext, _locateTypeId(jsonParser, deserializationContext)).mo4206deserialize(jsonParser, deserializationContext);
        if (isExpectedStartArrayToken) {
            JsonToken nextToken = jsonParser.nextToken();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            if (nextToken != jsonToken) {
                throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "expected closing END_ARRAY after type information and deserialized value");
            }
        }
        return mo4206deserialize;
    }

    protected final String _locateTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            TypeIdResolver typeIdResolver = this._idResolver;
            if ((typeIdResolver instanceof TypeIdResolverBase) && this._defaultImpl != null) {
                return ((TypeIdResolverBase) typeIdResolver).idFromBaseType();
            }
            JsonToken jsonToken = JsonToken.START_ARRAY;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("need JSON Array to contain As.WRAPPER_ARRAY type information for class ");
            outline107.append(baseTypeName());
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken, outline107.toString());
        } else if (jsonParser.nextToken() != JsonToken.VALUE_STRING) {
            TypeIdResolver typeIdResolver2 = this._idResolver;
            if ((typeIdResolver2 instanceof TypeIdResolverBase) && this._defaultImpl != null) {
                return ((TypeIdResolverBase) typeIdResolver2).idFromBaseType();
            }
            JsonToken jsonToken2 = JsonToken.VALUE_STRING;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("need JSON String that contains type id (for subtype of ");
            outline1072.append(baseTypeName());
            outline1072.append(")");
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken2, outline1072.toString());
        } else {
            String text = jsonParser.getText();
            jsonParser.nextToken();
            return text;
        }
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
        return JsonTypeInfo.As.WRAPPER_ARRAY;
    }

    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls) {
        super(javaType, typeIdResolver, beanProperty, cls);
    }
}
