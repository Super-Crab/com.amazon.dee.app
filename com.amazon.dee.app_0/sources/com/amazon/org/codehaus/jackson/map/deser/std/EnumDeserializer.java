package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.annotate.JsonCachable;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.EnumResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Method;
@JsonCachable
/* loaded from: classes13.dex */
public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    protected final EnumResolver<?> _resolver;

    /* loaded from: classes13.dex */
    protected static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        protected final Class<?> _enumClass;
        protected final Method _factory;
        protected final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = annotatedMethod.mo4213getAnnotated();
            this._inputType = cls2;
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Object mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Object valueOf;
            Class<?> cls = this._inputType;
            if (cls == null) {
                valueOf = jsonParser.getText();
            } else if (cls == Integer.class) {
                valueOf = Integer.valueOf(jsonParser.getValueAsInt());
            } else if (cls == Long.class) {
                valueOf = Long.valueOf(jsonParser.getValueAsLong());
            } else {
                throw deserializationContext.mappingException(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, valueOf);
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
                return null;
            }
        }
    }

    public EnumDeserializer(EnumResolver<?> enumResolver) {
        super(Enum.class);
        this._resolver = enumResolver;
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        Class cls2;
        Class<?> parameterClass = annotatedMethod.getParameterClass(0);
        if (parameterClass == String.class) {
            cls2 = null;
        } else if (parameterClass != Integer.TYPE && parameterClass != Integer.class) {
            if (parameterClass != Long.TYPE && parameterClass != Long.class) {
                throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String or int/Integer/long/Long");
            }
            cls2 = Long.class;
        } else {
            cls2 = Integer.class;
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
        }
        return new FactoryBasedDeserializer(cls, annotatedMethod, cls2);
    }

    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Enum, java.lang.Enum<?>] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, java.lang.Enum<?>] */
    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public Enum<?> mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.VALUE_STRING && currentToken != JsonToken.FIELD_NAME) {
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                if (!deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                    ?? r3 = this._resolver.getEnum(jsonParser.getIntValue());
                    if (r3 != 0) {
                        return r3;
                    }
                    Class<?> enumClass = this._resolver.getEnumClass();
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("index value outside legal index range [0..");
                    outline107.append(this._resolver.lastValidIndex());
                    outline107.append("]");
                    throw deserializationContext.weirdNumberException(enumClass, outline107.toString());
                }
                throw deserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
            }
            throw deserializationContext.mappingException(this._resolver.getEnumClass());
        }
        ?? findEnum = this._resolver.findEnum(jsonParser.getText());
        if (findEnum == 0) {
            throw deserializationContext.weirdStringException(this._resolver.getEnumClass(), "value not one of declared Enum instance names");
        }
        return findEnum;
    }
}
