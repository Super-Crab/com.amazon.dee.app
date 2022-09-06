package com.amazon.clouddrive.model.deserializer;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public interface JsonFieldDeserializer<T> {
    <U extends T> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException;
}
