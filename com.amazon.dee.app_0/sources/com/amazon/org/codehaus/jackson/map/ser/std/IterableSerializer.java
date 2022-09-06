package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Iterator;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class IterableSerializer extends AsArraySerializerBase<Iterable<?>> {
    public IterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        super(Iterable.class, javaType, z, typeSerializer, beanProperty, null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new IterableSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
    public void serializeContents(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Iterator<?> it2 = iterable.iterator();
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
