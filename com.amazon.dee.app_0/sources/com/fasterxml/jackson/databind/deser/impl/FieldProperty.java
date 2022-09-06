package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
/* loaded from: classes2.dex */
public final class FieldProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedField _annotated;
    protected final transient Field _field;
    protected final boolean _skipNulls;

    public FieldProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        this._annotated = annotatedField;
        this._field = annotatedField.mo7136getAnnotated();
        this._skipNulls = NullsConstantProvider.isSkipper(this._nullProvider);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        Object mo7107deserializeWithType;
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (this._skipNulls) {
                return;
            }
            mo7107deserializeWithType = this._nullProvider.mo7075getNullValue(deserializationContext);
        } else {
            TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
            if (typeDeserializer == null) {
                Object mo7111deserialize = this._valueDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                if (mo7111deserialize != null) {
                    mo7107deserializeWithType = mo7111deserialize;
                } else if (this._skipNulls) {
                    return;
                } else {
                    mo7107deserializeWithType = this._nullProvider.mo7075getNullValue(deserializationContext);
                }
            } else {
                mo7107deserializeWithType = this._valueDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        try {
            this._field.set(obj, mo7107deserializeWithType);
        } catch (Exception e) {
            _throwAsIOE(jsonParser, e, mo7107deserializeWithType);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        Object mo7107deserializeWithType;
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (this._skipNulls) {
                return obj;
            }
            mo7107deserializeWithType = this._nullProvider.mo7075getNullValue(deserializationContext);
        } else {
            TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
            if (typeDeserializer == null) {
                Object mo7111deserialize = this._valueDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                if (mo7111deserialize != null) {
                    mo7107deserializeWithType = mo7111deserialize;
                } else if (this._skipNulls) {
                    return obj;
                } else {
                    mo7107deserializeWithType = this._nullProvider.mo7075getNullValue(deserializationContext);
                }
            } else {
                mo7107deserializeWithType = this._valueDeserializer.mo7107deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        try {
            this._field.set(obj, mo7107deserializeWithType);
        } catch (Exception e) {
            _throwAsIOE(jsonParser, e, mo7107deserializeWithType);
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public void fixAccess(DeserializationConfig deserializationConfig) {
        ClassUtil.checkAndFixAccess(this._field, deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty, com.fasterxml.jackson.databind.BeanProperty
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        AnnotatedField annotatedField = this._annotated;
        if (annotatedField == null) {
            return null;
        }
        return (A) annotatedField.getAnnotation(cls);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty, com.fasterxml.jackson.databind.BeanProperty
    public AnnotatedMember getMember() {
        return this._annotated;
    }

    Object readResolve() {
        return new FieldProperty(this);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public void set(Object obj, Object obj2) throws IOException {
        try {
            this._field.set(obj, obj2);
        } catch (Exception e) {
            _throwAsIOE(e, obj2);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        try {
            this._field.set(obj, obj2);
        } catch (Exception e) {
            _throwAsIOE(e, obj2);
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public SettableBeanProperty withName(PropertyName propertyName) {
        return new FieldProperty(this, propertyName);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public SettableBeanProperty withNullProvider(NullValueProvider nullValueProvider) {
        return new FieldProperty(this, this._valueDeserializer, nullValueProvider);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        JsonDeserializer<?> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == jsonDeserializer) {
            return this;
        }
        NullValueProvider nullValueProvider = this._nullProvider;
        if (jsonDeserializer2 == nullValueProvider) {
            nullValueProvider = jsonDeserializer;
        }
        return new FieldProperty(this, jsonDeserializer, nullValueProvider);
    }

    protected FieldProperty(FieldProperty fieldProperty, JsonDeserializer<?> jsonDeserializer, NullValueProvider nullValueProvider) {
        super(fieldProperty, jsonDeserializer, nullValueProvider);
        this._annotated = fieldProperty._annotated;
        this._field = fieldProperty._field;
        this._skipNulls = NullsConstantProvider.isSkipper(nullValueProvider);
    }

    protected FieldProperty(FieldProperty fieldProperty, PropertyName propertyName) {
        super(fieldProperty, propertyName);
        this._annotated = fieldProperty._annotated;
        this._field = fieldProperty._field;
        this._skipNulls = fieldProperty._skipNulls;
    }

    protected FieldProperty(FieldProperty fieldProperty) {
        super(fieldProperty);
        this._annotated = fieldProperty._annotated;
        Field mo7136getAnnotated = this._annotated.mo7136getAnnotated();
        if (mo7136getAnnotated != null) {
            this._field = mo7136getAnnotated;
            this._skipNulls = fieldProperty._skipNulls;
            return;
        }
        throw new IllegalArgumentException("Missing field (broken JDK (de)serialization?)");
    }
}
