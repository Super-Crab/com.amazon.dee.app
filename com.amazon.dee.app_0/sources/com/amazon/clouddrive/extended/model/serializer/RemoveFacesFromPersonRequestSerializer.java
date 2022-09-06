package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.NodeFacePair;
import com.amazon.clouddrive.extended.model.RemoveFacesFromPersonRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class RemoveFacesFromPersonRequestSerializer implements JsonSerializer<RemoveFacesFromPersonRequest> {
    public static final JsonSerializer<RemoveFacesFromPersonRequest> INSTANCE = new RemoveFacesFromPersonRequestSerializer();

    private RemoveFacesFromPersonRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(RemoveFacesFromPersonRequest removeFacesFromPersonRequest, JsonGenerator jsonGenerator) throws IOException {
        if (removeFacesFromPersonRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        List<NodeFacePair> faces = removeFacesFromPersonRequest.getFaces();
        if (faces != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("faceList");
            jsonGenerator.writeStartArray();
            for (NodeFacePair nodeFacePair : faces) {
                NodeFacePairSerializer.INSTANCE.serialize(nodeFacePair, jsonGenerator);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
            return;
        }
        jsonGenerator.writeNull();
    }
}
