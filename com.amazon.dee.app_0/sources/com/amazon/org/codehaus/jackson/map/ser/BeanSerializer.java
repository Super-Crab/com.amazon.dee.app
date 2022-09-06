package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.ser.impl.UnwrappingBeanSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.BeanSerializerBase;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public class BeanSerializer extends BeanSerializerBase {
    public BeanSerializer(JavaType javaType, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(javaType, beanPropertyWriterArr, beanPropertyWriterArr2, anyGetterWriter, obj);
    }

    public static BeanSerializer createDummy(Class<?> cls) {
        return new BeanSerializer(cls, BeanSerializerBase.NO_PROPS, (BeanPropertyWriter[]) null, (AnyGetterWriter) null, (Object) null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.BeanSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BeanSerializer for ");
        outline107.append(handledType().getName());
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public JsonSerializer<Object> unwrappingSerializer() {
        return new UnwrappingBeanSerializer(this);
    }

    public BeanSerializer(Class<?> cls, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(cls, beanPropertyWriterArr, beanPropertyWriterArr2, anyGetterWriter, obj);
    }

    protected BeanSerializer(BeanSerializer beanSerializer) {
        super(beanSerializer);
    }

    protected BeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }
}
