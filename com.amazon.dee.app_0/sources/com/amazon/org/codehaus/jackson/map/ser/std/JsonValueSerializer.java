package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.schema.SchemaAware;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class JsonValueSerializer extends SerializerBase<Object> implements ResolvableSerializer, SchemaAware {
    protected final Method _accessorMethod;
    protected boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;

    public JsonValueSerializer(Method method, JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) {
        super(Object.class);
        this._accessorMethod = method;
        this._valueSerializer = jsonSerializer;
        this._property = beanProperty;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        return jsonSerializer instanceof SchemaAware ? ((SchemaAware) jsonSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode();
    }

    protected boolean isNaturalTypeWithStdHandling(JavaType javaType, JsonSerializer<?> jsonSerializer) {
        Class<?> rawClass = javaType.getRawClass();
        if (javaType.isPrimitive()) {
            if (rawClass != Integer.TYPE && rawClass != Boolean.TYPE && rawClass != Double.TYPE) {
                return false;
            }
        } else if (rawClass != String.class && rawClass != Integer.class && rawClass != Boolean.class && rawClass != Double.class) {
            return false;
        }
        return jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        if (this._valueSerializer == null) {
            if (!serializerProvider.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING) && !Modifier.isFinal(this._accessorMethod.getReturnType().getModifiers())) {
                return;
            }
            JavaType constructType = serializerProvider.constructType(this._accessorMethod.getGenericReturnType());
            this._valueSerializer = serializerProvider.findTypedValueSerializer(constructType, false, this._property);
            this._forceTypeInformation = isNaturalTypeWithStdHandling(constructType, this._valueSerializer);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        try {
            Object invoke = this._accessorMethod.invoke(obj, new Object[0]);
            if (invoke == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider.findTypedValueSerializer(invoke.getClass(), true, this._property);
            }
            jsonSerializer.serialize(invoke, jsonGenerator, serializerProvider);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            }
            throw JsonMappingException.wrapWithPath(e, obj, this._accessorMethod.getName() + "()");
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        try {
            Object invoke = this._accessorMethod.invoke(obj, new Object[0]);
            if (invoke == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer != null) {
                if (this._forceTypeInformation) {
                    typeSerializer.writeTypePrefixForScalar(obj, jsonGenerator);
                }
                jsonSerializer.serializeWithType(invoke, jsonGenerator, serializerProvider, typeSerializer);
                if (!this._forceTypeInformation) {
                    return;
                }
                typeSerializer.writeTypeSuffixForScalar(obj, jsonGenerator);
                return;
            }
            serializerProvider.findTypedValueSerializer(invoke.getClass(), true, this._property).serialize(invoke, jsonGenerator, serializerProvider);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            }
            throw JsonMappingException.wrapWithPath(e, obj, this._accessorMethod.getName() + "()");
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(@JsonValue serializer for method ");
        outline107.append(this._accessorMethod.getDeclaringClass());
        outline107.append(MqttTopic.MULTI_LEVEL_WILDCARD);
        outline107.append(this._accessorMethod.getName());
        outline107.append(")");
        return outline107.toString();
    }
}
