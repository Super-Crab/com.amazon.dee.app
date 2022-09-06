package com.amazon.communication.serialize;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers;
import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ByteBufferJsonSerializer extends JsonSerializer<ByteBuffer> {
    private static final JsonSerializer<byte[]> sByteArraySerializer = new StdArraySerializers.ByteArraySerializer();

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(ByteBuffer byteBuffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        sByteArraySerializer.serialize(byteBuffer.array(), jsonGenerator, serializerProvider);
    }
}
