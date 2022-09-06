package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Arrays;
/* loaded from: classes2.dex */
public abstract class PropertySerializerMap {
    protected final boolean _resetWhenFull;

    /* loaded from: classes2.dex */
    private static final class Double extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer1;
        private final JsonSerializer<Object> _serializer2;
        private final Class<?> _type1;
        private final Class<?> _type2;

        public Double(PropertySerializerMap propertySerializerMap, Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
            super(propertySerializerMap);
            this._type1 = cls;
            this._serializer1 = jsonSerializer;
            this._type2 = cls2;
            this._serializer2 = jsonSerializer2;
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Multi(this, new TypeAndSerializer[]{new TypeAndSerializer(this._type1, this._serializer1), new TypeAndSerializer(this._type2, this._serializer2), new TypeAndSerializer(cls, jsonSerializer)});
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
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
    /* loaded from: classes2.dex */
    public static final class Empty extends PropertySerializerMap {
        public static final Empty FOR_PROPERTIES = new Empty(false);
        public static final Empty FOR_ROOT_VALUES = new Empty(true);

        protected Empty(boolean z) {
            super(z);
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Single(this, cls, jsonSerializer);
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    private static final class Multi extends PropertySerializerMap {
        private static final int MAX_ENTRIES = 8;
        private final TypeAndSerializer[] _entries;

        public Multi(PropertySerializerMap propertySerializerMap, TypeAndSerializer[] typeAndSerializerArr) {
            super(propertySerializerMap);
            this._entries = typeAndSerializerArr;
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            TypeAndSerializer[] typeAndSerializerArr = this._entries;
            int length = typeAndSerializerArr.length;
            if (length == 8) {
                return this._resetWhenFull ? new Single(this, cls, jsonSerializer) : this;
            }
            TypeAndSerializer[] typeAndSerializerArr2 = (TypeAndSerializer[]) Arrays.copyOf(typeAndSerializerArr, length + 1);
            typeAndSerializerArr2[length] = new TypeAndSerializer(cls, jsonSerializer);
            return new Multi(this, typeAndSerializerArr2);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0044  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0050  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x005f A[ORIG_RETURN, RETURN] */
        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> serializerFor(java.lang.Class<?> r4) {
            /*
                r3 = this;
                com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap$TypeAndSerializer[] r0 = r3._entries
                r1 = 0
                r1 = r0[r1]
                java.lang.Class<?> r2 = r1.type
                if (r2 != r4) goto Lc
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r1.serializer
                return r4
            Lc:
                r1 = 1
                r1 = r0[r1]
                java.lang.Class<?> r2 = r1.type
                if (r2 != r4) goto L16
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r1.serializer
                return r4
            L16:
                r1 = 2
                r1 = r0[r1]
                java.lang.Class<?> r2 = r1.type
                if (r2 != r4) goto L20
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r1.serializer
                return r4
            L20:
                int r1 = r0.length
                switch(r1) {
                    case 4: goto L53;
                    case 5: goto L47;
                    case 6: goto L3b;
                    case 7: goto L2f;
                    case 8: goto L25;
                    default: goto L24;
                }
            L24:
                goto L5f
            L25:
                r1 = 7
                r0 = r0[r1]
                java.lang.Class<?> r1 = r0.type
                if (r1 != r4) goto L2f
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r0.serializer
                return r4
            L2f:
                com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap$TypeAndSerializer[] r0 = r3._entries
                r1 = 6
                r0 = r0[r1]
                java.lang.Class<?> r1 = r0.type
                if (r1 != r4) goto L3b
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r0.serializer
                return r4
            L3b:
                com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap$TypeAndSerializer[] r0 = r3._entries
                r1 = 5
                r0 = r0[r1]
                java.lang.Class<?> r1 = r0.type
                if (r1 != r4) goto L47
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r0.serializer
                return r4
            L47:
                com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap$TypeAndSerializer[] r0 = r3._entries
                r1 = 4
                r0 = r0[r1]
                java.lang.Class<?> r1 = r0.type
                if (r1 != r4) goto L53
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r0.serializer
                return r4
            L53:
                com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap$TypeAndSerializer[] r0 = r3._entries
                r1 = 3
                r0 = r0[r1]
                java.lang.Class<?> r1 = r0.type
                if (r1 != r4) goto L5f
                com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r0.serializer
                return r4
            L5f:
                r4 = 0
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.Multi.serializerFor(java.lang.Class):com.fasterxml.jackson.databind.JsonSerializer");
        }
    }

    /* loaded from: classes2.dex */
    public static final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer<Object> serializer;

        public SerializerAndMapResult(JsonSerializer<Object> jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    /* loaded from: classes2.dex */
    private static final class Single extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer;
        private final Class<?> _type;

        public Single(PropertySerializerMap propertySerializerMap, Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            super(propertySerializerMap);
            this._type = cls;
            this._serializer = jsonSerializer;
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Double(this, this._type, this._serializer, cls, jsonSerializer);
        }

        @Override // com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap
        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this._type) {
                return this._serializer;
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    private static final class TypeAndSerializer {
        public final JsonSerializer<Object> serializer;
        public final Class<?> type;

        public TypeAndSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.type = cls;
            this.serializer = jsonSerializer;
        }
    }

    protected PropertySerializerMap(boolean z) {
        this._resetWhenFull = z;
    }

    public static PropertySerializerMap emptyForProperties() {
        return Empty.FOR_PROPERTIES;
    }

    public static PropertySerializerMap emptyForRootValues() {
        return Empty.FOR_ROOT_VALUES;
    }

    public final SerializerAndMapResult addSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new SerializerAndMapResult(jsonSerializer, newWith(cls, jsonSerializer));
    }

    public final SerializerAndMapResult findAndAddKeySerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findKeySerializer = serializerProvider.findKeySerializer(cls, beanProperty);
        return new SerializerAndMapResult(findKeySerializer, newWith(cls, findKeySerializer));
    }

    public final SerializerAndMapResult findAndAddPrimarySerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findPrimaryPropertySerializer = serializerProvider.findPrimaryPropertySerializer(cls, beanProperty);
        return new SerializerAndMapResult(findPrimaryPropertySerializer, newWith(cls, findPrimaryPropertySerializer));
    }

    public final SerializerAndMapResult findAndAddRootValueSerializer(Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> findTypedValueSerializer = serializerProvider.findTypedValueSerializer(cls, false, (BeanProperty) null);
        return new SerializerAndMapResult(findTypedValueSerializer, newWith(cls, findTypedValueSerializer));
    }

    public final SerializerAndMapResult findAndAddSecondarySerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findContentValueSerializer = serializerProvider.findContentValueSerializer(cls, beanProperty);
        return new SerializerAndMapResult(findContentValueSerializer, newWith(cls, findContentValueSerializer));
    }

    public abstract PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> serializerFor(Class<?> cls);

    public final SerializerAndMapResult addSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        return new SerializerAndMapResult(jsonSerializer, newWith(javaType.getRawClass(), jsonSerializer));
    }

    protected PropertySerializerMap(PropertySerializerMap propertySerializerMap) {
        this._resetWhenFull = propertySerializerMap._resetWhenFull;
    }

    public final SerializerAndMapResult findAndAddPrimarySerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findPrimaryPropertySerializer = serializerProvider.findPrimaryPropertySerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findPrimaryPropertySerializer, newWith(javaType.getRawClass(), findPrimaryPropertySerializer));
    }

    public final SerializerAndMapResult findAndAddRootValueSerializer(JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> findTypedValueSerializer = serializerProvider.findTypedValueSerializer(javaType, false, (BeanProperty) null);
        return new SerializerAndMapResult(findTypedValueSerializer, newWith(javaType.getRawClass(), findTypedValueSerializer));
    }

    public final SerializerAndMapResult findAndAddSecondarySerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> findContentValueSerializer = serializerProvider.findContentValueSerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findContentValueSerializer, newWith(javaType.getRawClass(), findContentValueSerializer));
    }
}
