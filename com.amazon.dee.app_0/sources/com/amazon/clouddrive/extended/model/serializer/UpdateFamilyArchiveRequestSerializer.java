package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.UpdateFamilyArchiveRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleListSerializers;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UpdateFamilyArchiveRequestSerializer implements JsonSerializer<UpdateFamilyArchiveRequest> {
    public static final JsonSerializer<UpdateFamilyArchiveRequest> INSTANCE = new UpdateFamilyArchiveRequestSerializer();
    private final UpdateFamilyArchiveRequestFieldSerializer mFieldSerializer = new UpdateFamilyArchiveRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class UpdateFamilyArchiveRequestFieldSerializer implements JsonFieldSerializer<UpdateFamilyArchiveRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((UpdateFamilyArchiveRequestFieldSerializer) ((UpdateFamilyArchiveRequest) obj), jsonGenerator);
        }

        public <U extends UpdateFamilyArchiveRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getFamilyId() != null) {
                jsonGenerator.writeFieldName("familyId");
                SimpleSerializers.serializeString(u.getFamilyId(), jsonGenerator);
            }
            jsonGenerator.writeFieldName("nodesToAdd");
            SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getNodesToAdd(), jsonGenerator);
            jsonGenerator.writeFieldName("nodesToRemove");
            SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getNodesToRemove(), jsonGenerator);
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(UpdateFamilyArchiveRequest updateFamilyArchiveRequest, JsonGenerator jsonGenerator) throws IOException {
        if (updateFamilyArchiveRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((UpdateFamilyArchiveRequestFieldSerializer) updateFamilyArchiveRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
