package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public interface Serializers {

    /* loaded from: classes13.dex */
    public static class Base implements Serializers {
        @Override // com.amazon.org.codehaus.jackson.map.Serializers
        public JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.map.Serializers
        public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.map.Serializers
        public JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.map.Serializers
        public JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.map.Serializers
        public JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.map.Serializers
        public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) {
            return null;
        }
    }

    @Deprecated
    /* loaded from: classes13.dex */
    public static class None extends Base {
    }

    JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer);

    JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer);

    JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer);

    JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2);

    JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2);

    JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty);
}
