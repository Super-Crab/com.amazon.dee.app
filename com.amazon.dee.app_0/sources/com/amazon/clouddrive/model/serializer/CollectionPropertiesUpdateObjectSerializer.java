package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.clouddrive.model.CollectionPropertiesUpdateObject;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CollectionPropertiesUpdateObjectSerializer implements JsonSerializer<CollectionPropertiesUpdateObject> {
    public static final JsonSerializer<CollectionPropertiesUpdateObject> INSTANCE = new CollectionPropertiesUpdateObjectSerializer();

    private CollectionPropertiesUpdateObjectSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(CollectionPropertiesUpdateObject collectionPropertiesUpdateObject, JsonGenerator jsonGenerator) throws IOException {
        if (collectionPropertiesUpdateObject == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("collectionProperties");
        CollectionPropertiesSerializer.INSTANCE.serialize(collectionPropertiesUpdateObject.getCollectionProperties(), jsonGenerator);
        jsonGenerator.writeFieldName("op");
        SimpleSerializers.serializeString(collectionPropertiesUpdateObject.getOp(), jsonGenerator);
        jsonGenerator.writeFieldName(RouteParameter.PATH);
        SimpleSerializers.serializeString(collectionPropertiesUpdateObject.getPath(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
