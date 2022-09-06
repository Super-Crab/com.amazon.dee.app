package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CollectionPropertiesUpdateObject;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CollectionPropertiesUpdateObjectListSerializer implements JsonSerializer<List<CollectionPropertiesUpdateObject>> {
    public static final JsonSerializer<List<CollectionPropertiesUpdateObject>> INSTANCE = new CollectionPropertiesUpdateObjectListSerializer();

    private CollectionPropertiesUpdateObjectListSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(List<CollectionPropertiesUpdateObject> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (CollectionPropertiesUpdateObject collectionPropertiesUpdateObject : list) {
            CollectionPropertiesUpdateObjectSerializer.INSTANCE.serialize(collectionPropertiesUpdateObject, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
