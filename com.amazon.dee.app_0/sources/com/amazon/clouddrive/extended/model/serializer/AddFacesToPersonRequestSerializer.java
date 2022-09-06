package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.AddFacesToPersonRequest;
import com.amazon.clouddrive.extended.model.NodeFacePair;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class AddFacesToPersonRequestSerializer implements JsonSerializer<AddFacesToPersonRequest> {
    public static final JsonSerializer<AddFacesToPersonRequest> INSTANCE = new AddFacesToPersonRequestSerializer();
    private final AddFacesToPersonRequestFieldSerializer mFieldSerializer = new AddFacesToPersonRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AddFacesToPersonRequestFieldSerializer implements JsonFieldSerializer<AddFacesToPersonRequest> {
        AddFacesToPersonRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((AddFacesToPersonRequestFieldSerializer) ((AddFacesToPersonRequest) obj), jsonGenerator);
        }

        public <U extends AddFacesToPersonRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            List<NodeFacePair> faceList = u.getFaceList();
            if (faceList != null) {
                jsonGenerator.writeFieldName("faceList");
                jsonGenerator.writeStartArray();
                for (NodeFacePair nodeFacePair : faceList) {
                    NodeFacePairSerializer.INSTANCE.serialize(nodeFacePair, jsonGenerator);
                }
                jsonGenerator.writeEndArray();
            }
            if (u.getSourcePersonId() != null) {
                jsonGenerator.writeFieldName("sourcePersonId");
                SimpleSerializers.serializeString(u.getSourcePersonId(), jsonGenerator);
            }
        }
    }

    private AddFacesToPersonRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(AddFacesToPersonRequest addFacesToPersonRequest, JsonGenerator jsonGenerator) throws IOException {
        if (addFacesToPersonRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((AddFacesToPersonRequestFieldSerializer) addFacesToPersonRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
