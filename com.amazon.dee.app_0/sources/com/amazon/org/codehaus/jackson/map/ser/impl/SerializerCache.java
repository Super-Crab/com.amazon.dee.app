package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes13.dex */
public final class SerializerCache {
    private HashMap<TypeKey, JsonSerializer<Object>> _sharedMap = new HashMap<>(64);
    private ReadOnlyClassToSerializerMap _readOnlyMap = null;

    /* loaded from: classes13.dex */
    public static final class TypeKey {
        protected Class<?> _class;
        protected int _hashCode;
        protected boolean _isTyped;
        protected JavaType _type;

        public TypeKey(Class<?> cls, boolean z) {
            this._class = cls;
            this._type = null;
            this._isTyped = z;
            this._hashCode = hash(cls, z);
        }

        private static final int hash(Class<?> cls, boolean z) {
            int hashCode = cls.getName().hashCode();
            return z ? hashCode + 1 : hashCode;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            TypeKey typeKey = (TypeKey) obj;
            if (typeKey._isTyped != this._isTyped) {
                return false;
            }
            Class<?> cls = this._class;
            if (cls == null) {
                return this._type.equals(typeKey._type);
            }
            return typeKey._class == cls;
        }

        public final int hashCode() {
            return this._hashCode;
        }

        public void resetTyped(Class<?> cls) {
            this._type = null;
            this._class = cls;
            this._isTyped = true;
            this._hashCode = hash(cls, true);
        }

        public void resetUntyped(Class<?> cls) {
            this._type = null;
            this._class = cls;
            this._isTyped = false;
            this._hashCode = hash(cls, false);
        }

        public final String toString() {
            if (this._class != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{class: ");
                GeneratedOutlineSupport1.outline146(this._class, outline107, ", typed? ");
                return GeneratedOutlineSupport1.outline97(outline107, this._isTyped, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("{type: ");
            outline1072.append(this._type);
            outline1072.append(", typed? ");
            return GeneratedOutlineSupport1.outline97(outline1072, this._isTyped, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        }

        private static final int hash(JavaType javaType, boolean z) {
            int hashCode = javaType.hashCode() - 1;
            return z ? hashCode - 1 : hashCode;
        }

        public void resetTyped(JavaType javaType) {
            this._type = javaType;
            this._class = null;
            this._isTyped = true;
            this._hashCode = hash(javaType, true);
        }

        public void resetUntyped(JavaType javaType) {
            this._type = javaType;
            this._class = null;
            this._isTyped = false;
            this._hashCode = hash(javaType, false);
        }

        public TypeKey(JavaType javaType, boolean z) {
            this._type = javaType;
            this._class = null;
            this._isTyped = z;
            this._hashCode = hash(javaType, z);
        }
    }

    public void addAndResolveNonTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(cls, false), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
            if (jsonSerializer instanceof ResolvableSerializer) {
                ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
            }
        }
    }

    public void addTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(javaType, true), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
        }
    }

    public synchronized void flush() {
        this._sharedMap.clear();
    }

    public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap;
        synchronized (this) {
            readOnlyClassToSerializerMap = this._readOnlyMap;
            if (readOnlyClassToSerializerMap == null) {
                readOnlyClassToSerializerMap = ReadOnlyClassToSerializerMap.from(this._sharedMap);
                this._readOnlyMap = readOnlyClassToSerializerMap;
            }
        }
        return readOnlyClassToSerializerMap.instance();
    }

    public synchronized int size() {
        return this._sharedMap.size();
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(javaType, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(cls, false));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(cls, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(javaType, false));
        }
        return jsonSerializer;
    }

    public void addTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(cls, true), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
        }
    }

    public void addAndResolveNonTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(javaType, false), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
            if (jsonSerializer instanceof ResolvableSerializer) {
                ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
            }
        }
    }
}
