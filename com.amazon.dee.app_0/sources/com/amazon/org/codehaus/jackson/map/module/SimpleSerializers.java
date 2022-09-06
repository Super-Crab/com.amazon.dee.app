package com.amazon.org.codehaus.jackson.map.module;

import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.Serializers;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class SimpleSerializers extends Serializers.Base {
    protected HashMap<ClassKey, JsonSerializer<?>> _classMappings = null;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings = null;

    private void _addSerializer(Class<?> cls, JsonSerializer<?> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap<>();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._classMappings == null) {
            this._classMappings = new HashMap<>();
        }
        this._classMappings.put(classKey, jsonSerializer);
    }

    protected JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey classKey) {
        Class<?>[] interfaces;
        for (Class<?> cls2 : cls.getInterfaces()) {
            classKey.reset(cls2);
            JsonSerializer<?> jsonSerializer = this._interfaceMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            JsonSerializer<?> _findInterfaceMapping = _findInterfaceMapping(cls2, classKey);
            if (_findInterfaceMapping != null) {
                return _findInterfaceMapping;
            }
        }
        return null;
    }

    public void addSerializer(JsonSerializer<?> jsonSerializer) {
        Class<?> handledType = jsonSerializer.handledType();
        if (handledType != null && handledType != Object.class) {
            _addSerializer(handledType, jsonSerializer);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonSerializer of type ");
        outline107.append(jsonSerializer.getClass().getName());
        outline107.append(" does not define valid handledType() -- must either register with method that takes type argument ");
        outline107.append(" or make serializer extend 'org.codehaus.jackson.map.ser.std.SerializerBase'");
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.Serializers.Base, com.amazon.org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, arrayType, beanDescription, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.Serializers.Base, com.amazon.org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, collectionLikeType, beanDescription, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.Serializers.Base, com.amazon.org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, collectionType, beanDescription, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.Serializers.Base, com.amazon.org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(serializationConfig, mapLikeType, beanDescription, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.Serializers.Base, com.amazon.org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(serializationConfig, mapType, beanDescription, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.Serializers.Base, com.amazon.org.codehaus.jackson.map.Serializers
    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) {
        JsonSerializer<?> _findInterfaceMapping;
        JsonSerializer<?> jsonSerializer;
        Class<?> rawClass = javaType.getRawClass();
        ClassKey classKey = new ClassKey(rawClass);
        if (rawClass.isInterface()) {
            HashMap<ClassKey, JsonSerializer<?>> hashMap = this._interfaceMappings;
            if (hashMap != null && (jsonSerializer = hashMap.get(classKey)) != null) {
                return jsonSerializer;
            }
        } else {
            HashMap<ClassKey, JsonSerializer<?>> hashMap2 = this._classMappings;
            if (hashMap2 != null) {
                JsonSerializer<?> jsonSerializer2 = hashMap2.get(classKey);
                if (jsonSerializer2 != null) {
                    return jsonSerializer2;
                }
                for (Class<?> cls = rawClass; cls != null; cls = cls.getSuperclass()) {
                    classKey.reset(cls);
                    JsonSerializer<?> jsonSerializer3 = this._classMappings.get(classKey);
                    if (jsonSerializer3 != null) {
                        return jsonSerializer3;
                    }
                }
            }
        }
        if (this._interfaceMappings != null) {
            JsonSerializer<?> _findInterfaceMapping2 = _findInterfaceMapping(rawClass, classKey);
            if (_findInterfaceMapping2 != null) {
                return _findInterfaceMapping2;
            }
            if (rawClass.isInterface()) {
                return null;
            }
            do {
                rawClass = rawClass.getSuperclass();
                if (rawClass == null) {
                    return null;
                }
                _findInterfaceMapping = _findInterfaceMapping(rawClass, classKey);
            } while (_findInterfaceMapping == null);
            return _findInterfaceMapping;
        }
        return null;
    }

    public <T> void addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        _addSerializer(cls, jsonSerializer);
    }
}
