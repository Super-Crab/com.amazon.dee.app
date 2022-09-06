package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonProcessingException;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class KeyDeserializer {

    /* loaded from: classes13.dex */
    public static abstract class None extends KeyDeserializer {
    }

    public abstract Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;
}
