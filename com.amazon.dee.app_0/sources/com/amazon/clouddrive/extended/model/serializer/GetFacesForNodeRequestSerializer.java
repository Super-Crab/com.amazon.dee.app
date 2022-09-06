package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.GetFacesForNodeRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetFacesForNodeRequestSerializer implements JsonSerializer<GetFacesForNodeRequest> {
    public static final JsonSerializer<GetFacesForNodeRequest> INSTANCE = new GetFacesForNodeRequestSerializer();
    private final GetFacesForNodeRequestFieldSerializer mFieldSerializer = new GetFacesForNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetFacesForNodeRequestFieldSerializer implements JsonFieldSerializer<GetFacesForNodeRequest> {
        GetFacesForNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetFacesForNodeRequestFieldSerializer) ((GetFacesForNodeRequest) obj), jsonGenerator);
        }

        public <U extends GetFacesForNodeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getNodeId() != null) {
                jsonGenerator.writeFieldName(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
                SimpleSerializers.serializeString(u.getNodeId(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetFacesForNodeRequest getFacesForNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getFacesForNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetFacesForNodeRequestFieldSerializer) getFacesForNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
