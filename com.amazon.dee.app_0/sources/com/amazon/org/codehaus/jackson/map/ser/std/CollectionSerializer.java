package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes13.dex */
public class CollectionSerializer extends AsArraySerializerBase<Collection<?>> {
    public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(Collection.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new CollectionSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
    }

    public void serializeContentsUsing(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        Iterator<?> it2 = collection.iterator();
        if (it2.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            int i = 0;
            do {
                Object next = it2.next();
                if (next == null) {
                    try {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, collection, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            } while (it2.hasNext());
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
    public void serializeContents(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> _findAndAddDynamic;
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        if (jsonSerializer != null) {
            serializeContentsUsing(collection, jsonGenerator, serializerProvider, jsonSerializer);
            return;
        }
        Iterator<?> it2 = collection.iterator();
        if (!it2.hasNext()) {
            return;
        }
        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        int i = 0;
        do {
            try {
                Object next = it2.next();
                if (next == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class<?> cls = next.getClass();
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
                    if (typeSerializer == null) {
                        serializerFor.serialize(next, jsonGenerator, serializerProvider);
                    } else {
                        serializerFor.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                    }
                }
                i++;
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, e, collection, i);
                return;
            }
        } while (it2.hasNext());
    }
}
