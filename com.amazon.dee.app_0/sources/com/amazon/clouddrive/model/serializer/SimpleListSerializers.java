package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SimpleListSerializers {
    public static final JsonSerializer<List<String>> STRING_LIST_SERIALIZER = new ListSerializer(new StringSerializer());

    /* loaded from: classes11.dex */
    private static class StringSerializer implements JsonSerializer<String> {
        StringSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
        public void serialize(String str, JsonGenerator jsonGenerator) throws IOException {
            SimpleSerializers.serializeString(str, jsonGenerator);
        }
    }

    private SimpleListSerializers() {
    }
}
