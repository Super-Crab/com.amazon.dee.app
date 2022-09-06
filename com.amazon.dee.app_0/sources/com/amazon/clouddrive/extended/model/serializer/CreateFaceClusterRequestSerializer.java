package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.CreateFaceClusterRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleListSerializers;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateFaceClusterRequestSerializer implements JsonSerializer<CreateFaceClusterRequest> {
    public static final JsonSerializer<CreateFaceClusterRequest> INSTANCE = new CreateFaceClusterRequestSerializer();
    private final CreateFaceClusterRequestFieldSerializer mFieldSerializer = new CreateFaceClusterRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CreateFaceClusterRequestFieldSerializer implements JsonFieldSerializer<CreateFaceClusterRequest> {
        CreateFaceClusterRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CreateFaceClusterRequestFieldSerializer) ((CreateFaceClusterRequest) obj), jsonGenerator);
        }

        public <U extends CreateFaceClusterRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getClusterIdList() != null) {
                jsonGenerator.writeFieldName("clusterIds");
                SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getClusterIdList(), jsonGenerator);
            }
            if (u.getNewName() != null) {
                jsonGenerator.writeFieldName("newName");
                SimpleSerializers.serializeString(u.getNewName(), jsonGenerator);
            }
            if (u.getContext() != null) {
                jsonGenerator.writeFieldName("context");
                SimpleSerializers.serializeString(u.getContext(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(CreateFaceClusterRequest createFaceClusterRequest, JsonGenerator jsonGenerator) throws IOException {
        if (createFaceClusterRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CreateFaceClusterRequestFieldSerializer) createFaceClusterRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
