package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonProcessingException;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class DeserializationProblemHandler {
    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        return false;
    }
}
