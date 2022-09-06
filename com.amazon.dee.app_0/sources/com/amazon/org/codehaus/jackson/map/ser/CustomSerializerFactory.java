package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerFactory;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Modifier;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class CustomSerializerFactory extends BeanSerializerFactory {
    protected HashMap<ClassKey, JsonSerializer<?>> _directClassMappings;
    protected JsonSerializer<?> _enumSerializerOverride;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings;
    protected HashMap<ClassKey, JsonSerializer<?>> _transitiveClassMappings;

    public CustomSerializerFactory() {
        this(null);
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

    public <T> void addGenericMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap<>();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._transitiveClassMappings == null) {
            this._transitiveClassMappings = new HashMap<>();
        }
        this._transitiveClassMappings.put(classKey, jsonSerializer);
    }

    public <T> void addSpecificMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (!cls.isInterface()) {
            if (!Modifier.isAbstract(cls.getModifiers())) {
                if (this._directClassMappings == null) {
                    this._directClassMappings = new HashMap<>();
                }
                this._directClassMappings.put(classKey, jsonSerializer);
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not add specific mapping for an abstract class ("), ")"));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not add specific mapping for an interface ("), ")"));
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BeanSerializerFactory, com.amazon.org.codehaus.jackson.map.ser.BasicSerializerFactory, com.amazon.org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> findCustomSerializer = findCustomSerializer(javaType.getRawClass(), serializationConfig);
        return findCustomSerializer != null ? findCustomSerializer : super.createSerializer(serializationConfig, javaType, beanProperty);
    }

    protected JsonSerializer<?> findCustomSerializer(Class<?> cls, SerializationConfig serializationConfig) {
        JsonSerializer<?> jsonSerializer;
        JsonSerializer<?> jsonSerializer2;
        ClassKey classKey = new ClassKey(cls);
        HashMap<ClassKey, JsonSerializer<?>> hashMap = this._directClassMappings;
        if (hashMap == null || (jsonSerializer2 = hashMap.get(classKey)) == null) {
            if (cls.isEnum() && (jsonSerializer = this._enumSerializerOverride) != null) {
                return jsonSerializer;
            }
            if (this._transitiveClassMappings != null) {
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    classKey.reset(cls2);
                    JsonSerializer<?> jsonSerializer3 = this._transitiveClassMappings.get(classKey);
                    if (jsonSerializer3 != null) {
                        return jsonSerializer3;
                    }
                }
            }
            if (this._interfaceMappings == null) {
                return null;
            }
            classKey.reset(cls);
            JsonSerializer<?> jsonSerializer4 = this._interfaceMappings.get(classKey);
            if (jsonSerializer4 != null) {
                return jsonSerializer4;
            }
            while (cls != null) {
                JsonSerializer<?> _findInterfaceMapping = _findInterfaceMapping(cls, classKey);
                if (_findInterfaceMapping != null) {
                    return _findInterfaceMapping;
                }
                cls = cls.getSuperclass();
            }
            return null;
        }
        return jsonSerializer2;
    }

    public void setEnumSerializer(JsonSerializer<?> jsonSerializer) {
        this._enumSerializerOverride = jsonSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BeanSerializerFactory, com.amazon.org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (CustomSerializerFactory.class != CustomSerializerFactory.class) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subtype of CustomSerializerFactory (");
            outline107.append(CustomSerializerFactory.class.getName());
            outline107.append(") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with ");
            outline107.append("additional serializer definitions");
            throw new IllegalStateException(outline107.toString());
        }
        return new CustomSerializerFactory(config);
    }

    public CustomSerializerFactory(SerializerFactory.Config config) {
        super(config);
        this._directClassMappings = null;
        this._transitiveClassMappings = null;
        this._interfaceMappings = null;
    }
}
