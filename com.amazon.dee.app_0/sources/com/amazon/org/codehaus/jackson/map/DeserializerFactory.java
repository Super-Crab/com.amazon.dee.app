package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public abstract class DeserializerFactory {
    protected static final Deserializers[] NO_DESERIALIZERS = new Deserializers[0];

    /* loaded from: classes13.dex */
    public static abstract class Config {
        public abstract Iterable<AbstractTypeResolver> abstractTypeResolvers();

        public abstract Iterable<BeanDeserializerModifier> deserializerModifiers();

        public abstract Iterable<Deserializers> deserializers();

        public abstract boolean hasAbstractTypeResolvers();

        public abstract boolean hasDeserializerModifiers();

        public abstract boolean hasDeserializers();

        public abstract boolean hasKeyDeserializers();

        public abstract boolean hasValueInstantiators();

        public abstract Iterable<KeyDeserializers> keyDeserializers();

        public abstract Iterable<ValueInstantiators> valueInstantiators();

        public abstract Config withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

        public abstract Config withAdditionalDeserializers(Deserializers deserializers);

        public abstract Config withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers);

        public abstract Config withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

        public abstract Config withValueInstantiators(ValueInstantiators valueInstantiators);
    }

    public abstract JsonDeserializer<?> createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<Object> createBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<?> createCollectionDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionType collectionType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionLikeType collectionLikeType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<?> createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public KeyDeserializer createKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return null;
    }

    public abstract JsonDeserializer<?> createMapDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapType mapType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<?> createMapLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapLikeType mapLikeType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return null;
    }

    public abstract ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException;

    public abstract Config getConfig();

    public abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException;

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return withConfig(getConfig().withAbstractTypeResolver(abstractTypeResolver));
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers) {
        return withConfig(getConfig().withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return withConfig(getConfig().withAdditionalKeyDeserializers(keyDeserializers));
    }

    public abstract DeserializerFactory withConfig(Config config);

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return withConfig(getConfig().withDeserializerModifier(beanDeserializerModifier));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators valueInstantiators) {
        return withConfig(getConfig().withValueInstantiators(valueInstantiators));
    }
}
