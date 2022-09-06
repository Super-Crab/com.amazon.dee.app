package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.UpdateNodeRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UpdateNodeRequestSerializer implements JsonSerializer<UpdateNodeRequest> {
    public static final JsonSerializer<UpdateNodeRequest> INSTANCE = new UpdateNodeRequestSerializer();
    private final UpdateNodeRequestFieldSerializer mFieldSerializer = new UpdateNodeRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class UpdateNodeRequestFieldSerializer implements JsonFieldSerializer<UpdateNodeRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((UpdateNodeRequestFieldSerializer) ((UpdateNodeRequest) obj), jsonGenerator);
        }

        public <U extends UpdateNodeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getName().isPresent()) {
                jsonGenerator.writeFieldName("name");
                SimpleSerializers.serializeString(u.getName().get(), jsonGenerator);
            }
            if (u.getDescription().isPresent()) {
                jsonGenerator.writeFieldName("description");
                SimpleSerializers.serializeString(u.getDescription().get(), jsonGenerator);
            }
            if (u.getLabels().isPresent()) {
                jsonGenerator.writeFieldName("labels");
                LabelListSerializer.INSTANCE.serialize(u.getLabels().get(), jsonGenerator);
            }
            if (u.getSettings().isPresent()) {
                jsonGenerator.writeFieldName("settings");
                SettingsSerializer.INSTANCE.serialize(u.getSettings().get(), jsonGenerator);
            }
            if (u.getSymbolicNodeHeroId().isPresent()) {
                jsonGenerator.writeFieldName("symbolicNodeHeroId");
                SimpleSerializers.serializeString(u.getSymbolicNodeHeroId().get(), jsonGenerator);
            }
            if (u.getSubKindProperties().isPresent()) {
                jsonGenerator.writeFieldName("subKindProperties");
                SubKindPropertiesSerializer.INSTANCE.serialize(u.getSubKindProperties().get(), jsonGenerator);
            }
            if (u.getSubKinds().isPresent()) {
                jsonGenerator.writeFieldName("subKinds");
                SubKindListSerializer.INSTANCE.serialize(u.getSubKinds().get(), jsonGenerator);
            }
        }
    }

    private UpdateNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(UpdateNodeRequest updateNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (updateNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((UpdateNodeRequestFieldSerializer) updateNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
