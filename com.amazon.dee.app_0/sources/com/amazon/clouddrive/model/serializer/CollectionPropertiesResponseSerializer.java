package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CollectionPropertiesResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CollectionPropertiesResponseSerializer implements JsonSerializer<CollectionPropertiesResponse> {
    public static final JsonSerializer<CollectionPropertiesResponse> INSTANCE = new CollectionPropertiesResponseSerializer();

    private CollectionPropertiesResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(CollectionPropertiesResponse collectionPropertiesResponse, JsonGenerator jsonGenerator) throws IOException {
        if (collectionPropertiesResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("collectionProperties");
        CollectionPropertiesSerializer.INSTANCE.serialize(collectionPropertiesResponse.getCollectionProperties(), jsonGenerator);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(collectionPropertiesResponse.getId(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
