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
import com.amazon.org.codehaus.jackson.util.JsonParserSequence;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    protected final String _typePropertyName;

    /* renamed from: com.amazon.org.codehaus.jackson.map.jsontype.impl.AsPropertyTypeDeserializer$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Deprecated
    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        this(javaType, typeIdResolver, beanProperty, null, str);
    }

    protected Object _deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken().ordinal()) {
            case 7:
                if (!this._baseType.getRawClass().isAssignableFrom(String.class)) {
                    return null;
                }
                return jsonParser.getText();
            case 8:
                if (!this._baseType.getRawClass().isAssignableFrom(Integer.class)) {
                    return null;
                }
                return Integer.valueOf(jsonParser.getIntValue());
            case 9:
                if (!this._baseType.getRawClass().isAssignableFrom(Double.class)) {
                    return null;
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            case 10:
                if (!this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return null;
                }
                return Boolean.TRUE;
            case 11:
                if (!this._baseType.getRawClass().isAssignableFrom(Boolean.class)) {
                    return null;
                }
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jsonParser, DeserializationContext deserializationContext, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        if (this._defaultImpl != null) {
            JsonDeserializer<Object> _findDefaultImplDeserializer = _findDefaultImplDeserializer(deserializationContext);
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                jsonParser = tokenBuffer.asParser(jsonParser);
                jsonParser.nextToken();
            }
            return _findDefaultImplDeserializer.mo4206deserialize(jsonParser, deserializationContext);
        }
        Object _deserializeIfNatural = _deserializeIfNatural(jsonParser, deserializationContext);
        if (_deserializeIfNatural != null) {
            return _deserializeIfNatural;
        }
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        JsonToken jsonToken = JsonToken.FIELD_NAME;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("missing property '");
        outline107.append(this._typePropertyName);
        outline107.append("' that is to contain type id  (for class ");
        outline107.append(baseTypeName());
        outline107.append(")");
        throw deserializationContext.wrongTokenException(jsonParser, jsonToken, outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
        return deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        } else if (currentToken == JsonToken.START_ARRAY) {
            return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null);
        } else {
            if (currentToken != JsonToken.FIELD_NAME) {
                return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null);
            }
        }
        TokenBuffer tokenBuffer = null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (this._typePropertyName.equals(currentName)) {
                JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext, jsonParser.getText());
                if (tokenBuffer != null) {
                    jsonParser = JsonParserSequence.createFlattened(tokenBuffer.asParser(jsonParser), jsonParser);
                }
                jsonParser.nextToken();
                return _findDeserializer.mo4206deserialize(jsonParser, deserializationContext);
            }
            if (tokenBuffer == null) {
                tokenBuffer = new TokenBuffer(null);
            }
            tokenBuffer.writeFieldName(currentName);
            tokenBuffer.copyCurrentStructure(jsonParser);
            currentToken = jsonParser.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, tokenBuffer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.PROPERTY;
    }

    public AsPropertyTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls, String str) {
        super(javaType, typeIdResolver, beanProperty, cls);
        this._typePropertyName = str;
    }
}
