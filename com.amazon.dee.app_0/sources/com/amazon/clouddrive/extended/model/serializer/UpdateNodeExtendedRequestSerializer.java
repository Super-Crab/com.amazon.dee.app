package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.UpdateNodeExtendedRequest;
import com.amazon.clouddrive.model.UpdateNodeRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.clouddrive.model.serializer.UpdateNodeRequestSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UpdateNodeExtendedRequestSerializer implements JsonSerializer<UpdateNodeExtendedRequest> {
    public static final JsonSerializer<UpdateNodeExtendedRequest> INSTANCE = new UpdateNodeExtendedRequestSerializer();
    private final UpdateNodeExtendedRequestFieldSerializer mFieldSerializer = new UpdateNodeExtendedRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UpdateNodeExtendedRequestFieldSerializer extends UpdateNodeRequestSerializer.UpdateNodeRequestFieldSerializer {
        UpdateNodeExtendedRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.UpdateNodeRequestSerializer.UpdateNodeRequestFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((UpdateNodeExtendedRequestFieldSerializer) ((UpdateNodeRequest) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.UpdateNodeRequestSerializer.UpdateNodeRequestFieldSerializer
        public <U extends UpdateNodeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((UpdateNodeExtendedRequestFieldSerializer) u, jsonGenerator);
            if (!(u instanceof UpdateNodeExtendedRequest) || !((UpdateNodeExtendedRequest) u).getFamilyId().isPresent()) {
                return;
            }
            jsonGenerator.writeFieldName("familyId");
            SimpleSerializers.serializeString(u.getName().get(), jsonGenerator);
        }
    }

    private UpdateNodeExtendedRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(UpdateNodeExtendedRequest updateNodeExtendedRequest, JsonGenerator jsonGenerator) throws IOException {
        if (updateNodeExtendedRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((UpdateNodeExtendedRequestFieldSerializer) updateNodeExtendedRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
