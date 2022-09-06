package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public abstract class PropertySerializerMap {

    /* loaded from: classes13.dex */
    private static final class Double extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer1;
        private final JsonSerializer<Object> _serializer2;
        private final Class<?> _type1;
        private final Class<?> _type2;

        public Double(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
            this._type1 = cls;
            this._serializer1 = jsonSerializer;
            this._type2 = cls2;
            this._serializer2 = jsonSerializer2;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Multi(new TypeAndSerializer[]{new TypeAndSerializer(this._type1, this._serializer1), new TypeAndSerializer(this._type2, this._serializer2)});
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this._type1) {
                return this._serializer1;
            }
            if (cls != this._type2) {
                return null;
            }
            return this._serializer2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class Empty extends PropertySerializerMap {
        protected static final Empty instance = new Empty();

        private Empty() {
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Single(cls, jsonSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return null;
        }
    }

    /* loaded from: classes13.dex */
    private static final class Multi extends PropertySerializerMap {
        private static final int MAX_ENTRIES = 8;
        private final TypeAndSerializer[] _entries;

        public Multi(TypeAndSerializer[] typeAndSerializerArr) {
            this._entries = typeAndSerializerArr;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            TypeAndSerializer[] typeAndSerializerArr = this._entries;
            int length = typeAndSerializerArr.length;
            if (length == 8) {
                return this;
            }
            TypeAndSerializer[] typeAndSerializerArr2 = new TypeAndSerializer[length + 1];
            System.arraycopy(typeAndSerializerArr, 0, typeAndSerializerArr2, 0, length);
            typeAndSerializerArr2[length] = new TypeAndSerializer(cls, jsonSerializer);
            return new Multi(typeAndSerializerArr2);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            int length = this._entries.length;
            for (int i = 0; i < length; i++) {
                TypeAndSerializer typeAndSerializer = this._entries[i];
                if (typeAndSerializer.type == cls) {
                    return typeAndSerializer.serializer;
                }
            }
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public static final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer<Object> serializer;

        public SerializerAndMapResult(JsonSerializer<Object> jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    /* loaded from: classes13.dex */
    private static final class Single extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer;
        private final Class<?> _type;

        public Single(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this._type = cls;
            this._serializer = jsonSerializer;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Double(this._type, this._serializer, cls, jsonSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap
        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this._type) {
                return this._serializer;
            }
            return null;
        }
    }

    /* loaded from: classes13.dex */
    private static final class TypeAndSerializer {
        public final JsonSerializer<Object> serializer;
        public final Class<?> type;

        public TypeAndSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.type = cls;
            this.serializer = jsonSerializer;
        }
    }

    public static PropertySerializerMap emptyMap() {
        return Empty.instance;
    }

    public final SerializerAndMapResult findAndAddSerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(cls, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(cls, findValueSerializer));
    }

    public abstract PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> serializerFor(Class<?> cls);

    public final SerializerAndMapResult findAndAddSerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(javaType.getRawClass(), findValueSerializer));
    }
}
