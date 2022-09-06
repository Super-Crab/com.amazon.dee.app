package com.amazon.clouddrive.model.deserializer;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public interface JsonDeserializer<T> {
    /* renamed from: deserialize */
    T mo3229deserialize(JsonParser jsonParser) throws IOException;
}
