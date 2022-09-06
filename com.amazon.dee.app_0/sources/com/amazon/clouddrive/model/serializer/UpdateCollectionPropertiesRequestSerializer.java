package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.UpdateCollectionPropertiesRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UpdateCollectionPropertiesRequestSerializer implements JsonSerializer<UpdateCollectionPropertiesRequest> {
    public static final JsonSerializer<UpdateCollectionPropertiesRequest> INSTANCE = new UpdateCollectionPropertiesRequestSerializer();

    private UpdateCollectionPropertiesRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (updateCollectionPropertiesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(updateCollectionPropertiesRequest.getId(), jsonGenerator);
        jsonGenerator.writeFieldName("operations");
        CollectionPropertiesUpdateObjectListSerializer.INSTANCE.serialize(updateCollectionPropertiesRequest.getOperations(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
