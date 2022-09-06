package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.ser.std.BeanSerializerBase;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public class UnwrappingBeanSerializer extends BeanSerializerBase {
    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public boolean isUnwrappingSerializer() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.BeanSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UnwrappingBeanSerializer for ");
        outline107.append(handledType().getName());
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public JsonSerializer<Object> unwrappingSerializer() {
        return this;
    }
}
