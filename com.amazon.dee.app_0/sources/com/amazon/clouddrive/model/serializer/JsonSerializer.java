package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public interface JsonSerializer<T> {
    void serialize(T t, JsonGenerator jsonGenerator) throws IOException;
}
