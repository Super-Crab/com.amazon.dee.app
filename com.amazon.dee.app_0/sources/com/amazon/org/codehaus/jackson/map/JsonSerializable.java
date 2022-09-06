package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import java.io.IOException;
@Deprecated
/* loaded from: classes13.dex */
public interface JsonSerializable {
    void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;
}
