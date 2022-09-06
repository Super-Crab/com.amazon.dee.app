package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes13.dex */
public class StdContainerSerializers {

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static class IndexedListSerializer extends AsArraySerializerBase<List<?>> {
        public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            super(List.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IndexedListSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
        }

        public void serializeContentsUsing(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
            int size = list.size();
            if (size == 0) {
                return;
            }
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                if (obj == null) {
                    try {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, list, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        }

        public void serializeTypedContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            JsonSerializer<Object> _findAndAddDynamic;
            int size = list.size();
            if (size == 0) {
                return;
            }
            int i = 0;
            try {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                while (i < size) {
                    Object obj = list.get(i);
                    if (obj == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls = obj.getClass();
                        JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            if (this._elementType.hasGenericTypes()) {
                                _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                            } else {
                                _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            }
                            serializerFor = _findAndAddDynamic;
                            propertySerializerMap = this._dynamicSerializers;
                        }
                        serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    i++;
                }
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, e, list, i);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
        public void serializeContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            JsonSerializer<Object> _findAndAddDynamic;
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer != null) {
                serializeContentsUsing(list, jsonGenerator, serializerProvider, jsonSerializer);
            } else if (this._valueTypeSerializer != null) {
                serializeTypedContents(list, jsonGenerator, serializerProvider);
            } else {
                int size = list.size();
                if (size == 0) {
                    return;
                }
                int i = 0;
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < size) {
                        Object obj = list.get(i);
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                                } else {
                                    _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                }
                                serializerFor = _findAndAddDynamic;
                                propertySerializerMap = this._dynamicSerializers;
                            }
                            serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                        }
                        i++;
                    }
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, list, i);
                }
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes13.dex */
    public static class IteratorSerializer extends AsArraySerializerBase<Iterator<?>> {
        public IteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(Iterator.class, javaType, z, typeSerializer, beanProperty, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IteratorSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
        public void serializeContents(Iterator<?> it2, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (it2.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                Class<?> cls = null;
                JsonSerializer<Object> jsonSerializer = null;
                do {
                    Object next = it2.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls2 = next.getClass();
                        if (cls2 != cls) {
                            jsonSerializer = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                        }
                        if (typeSerializer == null) {
                            jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                } while (it2.hasNext());
            }
        }
    }

    public static ContainerSerializerBase<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return new CollectionSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static JsonSerializer<?> enumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        return new EnumSetSerializer(javaType, beanProperty);
    }

    public static ContainerSerializerBase<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return new IndexedListSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static ContainerSerializerBase<?> iterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IterableSerializer(javaType, z, typeSerializer, beanProperty);
    }

    public static ContainerSerializerBase<?> iteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IteratorSerializer(javaType, z, typeSerializer, beanProperty);
    }
}
