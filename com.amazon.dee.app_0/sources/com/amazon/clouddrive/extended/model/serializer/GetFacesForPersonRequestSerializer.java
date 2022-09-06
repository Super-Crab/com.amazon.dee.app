package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.clouddrive.extended.model.GetFacesForPersonRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetFacesForPersonRequestSerializer implements JsonSerializer<GetFacesForPersonRequest> {
    public static final JsonSerializer<GetFacesForPersonRequest> INSTANCE = new GetFacesForPersonRequestSerializer();
    private final GetFacesForPersonRequestFieldSerializer mFieldSerializer = new GetFacesForPersonRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetFacesForPersonRequestFieldSerializer implements JsonFieldSerializer<GetFacesForPersonRequest> {
        GetFacesForPersonRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetFacesForPersonRequestFieldSerializer) ((GetFacesForPersonRequest) obj), jsonGenerator);
        }

        public <U extends GetFacesForPersonRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            List<String> nodeIds = u.getNodeIds();
            if (nodeIds != null) {
                jsonGenerator.writeFieldName("nodeIds");
                jsonGenerator.writeStartArray();
                for (String str : nodeIds) {
                    SimpleSerializers.serializeString(str, jsonGenerator);
                }
                jsonGenerator.writeEndArray();
            }
            if (u.getPersonId() != null) {
                jsonGenerator.writeFieldName(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID);
                SimpleSerializers.serializeString(u.getPersonId(), jsonGenerator);
            }
        }
    }

    private GetFacesForPersonRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(GetFacesForPersonRequest getFacesForPersonRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getFacesForPersonRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetFacesForPersonRequestFieldSerializer) getFacesForPersonRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
