package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class ReferenceTypeDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer {
    private static final long serialVersionUID = 2;
    protected final JavaType _fullType;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public ReferenceTypeDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        this._valueInstantiator = valueInstantiator;
        this._fullType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    /* renamed from: createContextual */
    public JsonDeserializer<?> mo7066createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> handleSecondaryContextualization;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer == null) {
            handleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(this._fullType.mo7244getReferencedType(), beanProperty);
        } else {
            handleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._fullType.mo7244getReferencedType());
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return (handleSecondaryContextualization == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) ? this : withResolved(typeDeserializer, handleSecondaryContextualization);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public T mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object mo7107deserializeWithType;
        ValueInstantiator valueInstantiator = this._valueInstantiator;
        if (valueInstantiator != null) {
            return (T) deserialize(jsonParser, deserializationContext, valueInstantiator.createUsingDefault(deserializationContext));
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer == null) {
            mo7107deserializeWithType = this._valueDeserializer.mo7111deserialize(jsonParser, deserializationContext);
        } else {
            mo7107deserializeWithType = this._valueDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return (T) referenceValue(mo7107deserializeWithType);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo7107deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return mo7075getNullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer2 = this._valueTypeDeserializer;
        if (typeDeserializer2 == null) {
            return mo7111deserialize(jsonParser, deserializationContext);
        }
        return referenceValue(typeDeserializer2.deserializeTypedFromAny(jsonParser, deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return mo7075getNullValue(deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
    /* renamed from: getNullValue */
    public abstract T mo7075getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;

    public abstract Object getReferenced(T t);

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable
    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public JavaType getValueType() {
        return this._fullType;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer != null) {
            return jsonDeserializer.logicalType();
        }
        return super.logicalType();
    }

    public abstract T referenceValue(Object obj);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer == null) {
            return null;
        }
        return jsonDeserializer.supportsUpdate(deserializationConfig);
    }

    public abstract T updateReference(T t, Object obj);

    protected abstract ReferenceTypeDeserializer<T> withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    @Deprecated
    public ReferenceTypeDeserializer(JavaType javaType, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        this(javaType, null, typeDeserializer, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) throws IOException {
        Object mo7107deserializeWithType;
        Object mo7107deserializeWithType2;
        if (!this._valueDeserializer.supportsUpdate(deserializationContext.mo7051getConfig()).equals(Boolean.FALSE) && this._valueTypeDeserializer == null) {
            Object referenced = getReferenced(t);
            if (referenced == null) {
                TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
                if (typeDeserializer == null) {
                    mo7107deserializeWithType2 = this._valueDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                } else {
                    mo7107deserializeWithType2 = this._valueDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                return referenceValue(mo7107deserializeWithType2);
            }
            mo7107deserializeWithType = this._valueDeserializer.deserialize(jsonParser, deserializationContext, referenced);
        } else {
            TypeDeserializer typeDeserializer2 = this._valueTypeDeserializer;
            if (typeDeserializer2 == null) {
                mo7107deserializeWithType = this._valueDeserializer.mo7111deserialize(jsonParser, deserializationContext);
            } else {
                mo7107deserializeWithType = this._valueDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, typeDeserializer2);
            }
        }
        return updateReference(t, mo7107deserializeWithType);
    }
}
