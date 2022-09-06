package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.CreatePersonRequest;
import com.amazon.clouddrive.extended.model.NodeFacePair;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreatePersonRequestSerializer implements JsonSerializer<CreatePersonRequest> {
    public static final JsonSerializer<CreatePersonRequest> INSTANCE = new CreatePersonRequestSerializer();
    private final CreatePersonRequestFieldSerializer mFieldSerializer = new CreatePersonRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CreatePersonRequestFieldSerializer implements JsonFieldSerializer<CreatePersonRequest> {
        CreatePersonRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CreatePersonRequestFieldSerializer) ((CreatePersonRequest) obj), jsonGenerator);
        }

        public <U extends CreatePersonRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            List<NodeFacePair> faceList = u.getFaceList();
            if (faceList != null) {
                jsonGenerator.writeFieldName("faceList");
                jsonGenerator.writeStartArray();
                for (NodeFacePair nodeFacePair : faceList) {
                    NodeFacePairSerializer.INSTANCE.serialize(nodeFacePair, jsonGenerator);
                }
                jsonGenerator.writeEndArray();
            }
            if (u.getNewPersonName() != null) {
                jsonGenerator.writeFieldName("newPersonName");
                SimpleSerializers.serializeString(u.getNewPersonName(), jsonGenerator);
            }
            if (u.getSourcePersonId() != null) {
                jsonGenerator.writeFieldName("sourcePersonId");
                SimpleSerializers.serializeString(u.getSourcePersonId(), jsonGenerator);
            }
        }
    }

    private CreatePersonRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(CreatePersonRequest createPersonRequest, JsonGenerator jsonGenerator) throws IOException {
        if (createPersonRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CreatePersonRequestFieldSerializer) createPersonRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
