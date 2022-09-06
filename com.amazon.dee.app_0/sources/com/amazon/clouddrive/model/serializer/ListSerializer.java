package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListSerializer<T> implements JsonSerializer<List<T>> {
    private final JsonSerializer<T> mSerializer;

    public ListSerializer(JsonSerializer<T> jsonSerializer) {
        this.mSerializer = jsonSerializer;
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public /* bridge */ /* synthetic */ void serialize(Object obj, JsonGenerator jsonGenerator) throws IOException {
        serialize((List) ((List) obj), jsonGenerator);
    }

    public void serialize(List<T> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (T t : list) {
            this.mSerializer.serialize(t, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
