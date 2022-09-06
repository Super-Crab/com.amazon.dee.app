package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public interface JsonFieldSerializer<T> {
    <U extends T> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException;
}
