package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.RenameFaceClusterRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class RenameFaceClusterRequestSerializer implements JsonSerializer<RenameFaceClusterRequest> {
    public static final JsonSerializer<RenameFaceClusterRequest> INSTANCE = new RenameFaceClusterRequestSerializer();
    private final RenameFaceClusterRequestFieldSerializer mFieldSerializer = new RenameFaceClusterRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class RenameFaceClusterRequestFieldSerializer implements JsonFieldSerializer<RenameFaceClusterRequest> {
        RenameFaceClusterRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((RenameFaceClusterRequestFieldSerializer) ((RenameFaceClusterRequest) obj), jsonGenerator);
        }

        public <U extends RenameFaceClusterRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getSourceClusterId() != null) {
                jsonGenerator.writeFieldName("sourceCluster");
                SimpleSerializers.serializeString(u.getSourceClusterId(), jsonGenerator);
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
    public void serialize(RenameFaceClusterRequest renameFaceClusterRequest, JsonGenerator jsonGenerator) throws IOException {
        if (renameFaceClusterRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((RenameFaceClusterRequestFieldSerializer) renameFaceClusterRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
