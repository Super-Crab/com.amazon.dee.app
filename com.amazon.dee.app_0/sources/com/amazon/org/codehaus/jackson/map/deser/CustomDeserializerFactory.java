package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializerFactory;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
@Deprecated
/* loaded from: classes13.dex */
public class CustomDeserializerFactory extends BeanDeserializerFactory {
    protected HashMap<ClassKey, JsonDeserializer<Object>> _directClassMappings;
    protected HashMap<ClassKey, Class<?>> _mixInAnnotations;

    public CustomDeserializerFactory() {
        this(null);
    }

    public void addMixInAnnotationMapping(Class<?> cls, Class<?> cls2) {
        if (this._mixInAnnotations == null) {
            this._mixInAnnotations = new HashMap<>();
        }
        this._mixInAnnotations.put(new ClassKey(cls), cls2);
    }

    public <T> void addSpecificMapping(Class<T> cls, JsonDeserializer<? extends T> jsonDeserializer) {
        ClassKey classKey = new ClassKey(cls);
        if (this._directClassMappings == null) {
            this._directClassMappings = new HashMap<>();
        }
        this._directClassMappings.put(classKey, jsonDeserializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        ClassKey classKey = new ClassKey(arrayType.getRawClass());
        HashMap<ClassKey, JsonDeserializer<Object>> hashMap = this._directClassMappings;
        return (hashMap == null || (jsonDeserializer = hashMap.get(classKey)) == null) ? super.createArrayDeserializer(deserializationConfig, deserializerProvider, arrayType, beanProperty) : jsonDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BeanDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        ClassKey classKey = new ClassKey(javaType.getRawClass());
        HashMap<ClassKey, JsonDeserializer<Object>> hashMap = this._directClassMappings;
        return (hashMap == null || (jsonDeserializer = hashMap.get(classKey)) == null) ? super.createBeanDeserializer(deserializationConfig, deserializerProvider, javaType, beanProperty) : jsonDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<?> createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        if (this._directClassMappings != null) {
            JsonDeserializer<?> jsonDeserializer = this._directClassMappings.get(new ClassKey(javaType.getRawClass()));
            if (jsonDeserializer != null) {
                return jsonDeserializer;
            }
        }
        return super.createEnumDeserializer(deserializationConfig, deserializerProvider, javaType, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BeanDeserializerFactory, com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public DeserializerFactory withConfig(DeserializerFactory.Config config) {
        if (CustomDeserializerFactory.class != CustomDeserializerFactory.class) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subtype of CustomDeserializerFactory (");
            outline107.append(CustomDeserializerFactory.class.getName());
            outline107.append(") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with ");
            outline107.append("additional deserializer definitions");
            throw new IllegalStateException(outline107.toString());
        }
        return new CustomDeserializerFactory(config);
    }

    protected CustomDeserializerFactory(DeserializerFactory.Config config) {
        super(config);
        this._directClassMappings = null;
    }
}
