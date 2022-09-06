package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CoverObject;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CoverObjectListSerializer implements JsonSerializer<List<CoverObject>> {
    public static final JsonSerializer<List<CoverObject>> INSTANCE = new CoverObjectListSerializer();

    private CoverObjectListSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(List<CoverObject> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (CoverObject coverObject : list) {
            CoverObjectSerializer.INSTANCE.serialize(coverObject, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
