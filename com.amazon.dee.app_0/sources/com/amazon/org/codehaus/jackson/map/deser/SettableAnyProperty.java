package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Method;
/* loaded from: classes13.dex */
public final class SettableAnyProperty {
    protected final BeanProperty _property;
    protected final Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;

    @Deprecated
    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMethod annotatedMethod, JavaType javaType) {
        this(beanProperty, annotatedMethod, javaType, (JsonDeserializer<Object>) null);
    }

    private String getClassName() {
        return this._setter.getDeclaringClass().getName();
    }

    protected void _throwAsIOE(Exception exc, String str, Object obj) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            String name = obj == null ? "[NULL]" : obj.getClass().getName();
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("Problem deserializing \"any\" property '", str);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("' of class ");
            outline107.append(getClassName());
            outline107.append(" (expected type: ");
            outline112.append(outline107.toString());
            outline112.append(this._type);
            outline112.append("; actual type: ");
            outline112.append(name);
            outline112.append(")");
            String message = exc.getMessage();
            if (message != null) {
                outline112.append(", problem: ");
                outline112.append(message);
            } else {
                outline112.append(" (no error message provided)");
            }
            throw new JsonMappingException(outline112.toString(), null, exc);
        } else if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Exception exc2 = exc;
            if (!z) {
                while (exc2.getCause() != null) {
                    exc2 = exc2.getCause();
                }
                throw new JsonMappingException(exc2.getMessage(), null, exc2);
            }
            throw ((RuntimeException) exc);
        } else {
            throw ((IOException) exc);
        }
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return this._valueDeserializer.mo4206deserialize(jsonParser, deserializationContext);
    }

    public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        set(obj, str, deserialize(jsonParser, deserializationContext));
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public JavaType getType() {
        return this._type;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public final void set(Object obj, String str, Object obj2) throws IOException {
        try {
            this._setter.invoke(obj, str, obj2);
        } catch (Exception e) {
            _throwAsIOE(e, str, obj2);
        }
    }

    @Deprecated
    public void setValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        if (this._valueDeserializer == null) {
            this._valueDeserializer = jsonDeserializer;
            return;
        }
        throw new IllegalStateException("Already had assigned deserializer for SettableAnyProperty");
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("[any property on class "), getClassName(), "]");
    }

    public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        return new SettableAnyProperty(this._property, this._setter, this._type, jsonDeserializer);
    }

    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMethod annotatedMethod, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this(beanProperty, annotatedMethod.mo4213getAnnotated(), javaType, jsonDeserializer);
    }

    public SettableAnyProperty(BeanProperty beanProperty, Method method, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this._property = beanProperty;
        this._type = javaType;
        this._setter = method;
        this._valueDeserializer = jsonDeserializer;
    }
}
