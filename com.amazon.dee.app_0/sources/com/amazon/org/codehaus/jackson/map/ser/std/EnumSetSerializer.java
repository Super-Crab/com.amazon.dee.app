package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
/* loaded from: classes13.dex */
public class EnumSetSerializer extends AsArraySerializerBase<EnumSet<? extends Enum<?>>> {
    public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        super(EnumSet.class, javaType, true, null, beanProperty, null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
    public void serializeContents(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        Iterator it2 = enumSet.iterator();
        while (it2.hasNext()) {
            Enum r1 = (Enum) it2.next();
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider.findValueSerializer(r1.getDeclaringClass(), this._property);
            }
            jsonSerializer.serialize(r1, jsonGenerator, serializerProvider);
        }
    }
}
