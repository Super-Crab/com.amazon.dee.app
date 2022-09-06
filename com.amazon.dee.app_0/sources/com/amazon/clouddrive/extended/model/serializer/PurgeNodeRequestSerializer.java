package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.PurgeNodeRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PurgeNodeRequestSerializer implements JsonSerializer<PurgeNodeRequest> {
    public static final JsonSerializer<PurgeNodeRequest> INSTANCE = new PurgeNodeRequestSerializer();
    private final PurgeNodeRequestFieldSerializer mFieldSerializer = new PurgeNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PurgeNodeRequestFieldSerializer implements JsonFieldSerializer<PurgeNodeRequest> {
        PurgeNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PurgeNodeRequestFieldSerializer) ((PurgeNodeRequest) obj), jsonGenerator);
        }

        public <U extends PurgeNodeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getRecurse() != null) {
                jsonGenerator.writeFieldName("recurse");
                SimpleSerializers.serializeString(u.getRecurse().toString(), jsonGenerator);
            }
        }
    }

    private PurgeNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(PurgeNodeRequest purgeNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (purgeNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PurgeNodeRequestFieldSerializer) purgeNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
