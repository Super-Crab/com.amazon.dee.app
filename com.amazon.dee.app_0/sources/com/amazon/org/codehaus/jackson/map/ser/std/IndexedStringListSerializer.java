package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
import java.util.List;
@JacksonStdImpl
/* loaded from: classes13.dex */
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements ResolvableSerializer {
    protected JsonSerializer<String> _serializer;

    public IndexedStringListSerializer(BeanProperty beanProperty) {
        this(beanProperty, null);
    }

    private final void serializeContents(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int i = 0;
        try {
            int size = list.size();
            while (i < size) {
                String str = list.get(i);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonGenerator.writeString(str);
                }
                i++;
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, list, i);
        }
    }

    private final void serializeUsingCustom(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int i = 0;
        try {
            int size = list.size();
            JsonSerializer<String> jsonSerializer = this._serializer;
            while (i < size) {
                String str = list.get(i);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
                }
                i++;
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, list, i);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.StaticListSerializerBase
    protected JsonNode contentSchema() {
        return createSchemaNode("string", true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        if (this._serializer == null) {
            JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
            if (isDefaultSerializer(findValueSerializer)) {
                return;
            }
            this._serializer = findValueSerializer;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IndexedStringListSerializer(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super(List.class, beanProperty);
        this._serializer = jsonSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartArray();
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForArray(list, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(list, jsonGenerator);
    }
}
