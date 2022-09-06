package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CollectionProperties;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CollectionPropertiesSerializer implements JsonSerializer<CollectionProperties> {
    public static final JsonSerializer<CollectionProperties> INSTANCE = new CollectionPropertiesSerializer();

    private CollectionPropertiesSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(CollectionProperties collectionProperties, JsonGenerator jsonGenerator) throws IOException {
        if (collectionProperties == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(SearchIntents.EXTRA_QUERY);
        QueryObjectSerializer.INSTANCE.serialize(collectionProperties.getQuery(), jsonGenerator);
        jsonGenerator.writeFieldName("areCoversDesired");
        SimpleSerializers.serializeBoolean(collectionProperties.areCoversDesired(), jsonGenerator);
        jsonGenerator.writeFieldName("covers");
        CoverObjectListSerializer.INSTANCE.serialize(collectionProperties.getCovers(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
