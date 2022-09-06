package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class NonTypedScalarSerializerBase<T> extends ScalarSerializerBase<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public NonTypedScalarSerializerBase(Class<T> cls) {
        super(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        serialize(t, jsonGenerator, serializerProvider);
    }
}
