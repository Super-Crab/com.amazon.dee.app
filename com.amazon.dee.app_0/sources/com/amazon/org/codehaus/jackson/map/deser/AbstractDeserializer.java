package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
/* loaded from: classes13.dex */
public class AbstractDeserializer extends JsonDeserializer<Object> {
    protected final boolean _acceptBoolean;
    protected final boolean _acceptDouble;
    protected final boolean _acceptInt;
    protected final boolean _acceptString;
    protected final JavaType _baseType;

    /* renamed from: com.amazon.org.codehaus.jackson.map.deser.AbstractDeserializer$1  reason: invalid class name */
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

    public AbstractDeserializer(JavaType javaType) {
        this._baseType = javaType;
        Class<?> rawClass = javaType.getRawClass();
        this._acceptString = rawClass.isAssignableFrom(String.class);
        boolean z = false;
        this._acceptBoolean = rawClass == Boolean.TYPE || rawClass.isAssignableFrom(Boolean.class);
        this._acceptInt = rawClass == Integer.TYPE || rawClass.isAssignableFrom(Integer.class);
        this._acceptDouble = (rawClass == Double.TYPE || rawClass.isAssignableFrom(Double.class)) ? true : z;
    }

    protected Object _deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken().ordinal()) {
            case 7:
                if (!this._acceptString) {
                    return null;
                }
                return jsonParser.getText();
            case 8:
                if (!this._acceptInt) {
                    return null;
                }
                return Integer.valueOf(jsonParser.getIntValue());
            case 9:
                if (!this._acceptDouble) {
                    return null;
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            case 10:
                if (!this._acceptBoolean) {
                    return null;
                }
                return Boolean.TRUE;
            case 11:
                if (!this._acceptBoolean) {
                    return null;
                }
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public Object mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        throw deserializationContext.instantiationException(this._baseType.getRawClass(), "abstract types can only be instantiated with additional type information");
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        Object _deserializeIfNatural = _deserializeIfNatural(jsonParser, deserializationContext);
        return _deserializeIfNatural != null ? _deserializeIfNatural : typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
}
