package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.SetPreferenceRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SetPreferenceRequestSerializer implements JsonSerializer<SetPreferenceRequest> {
    public static final JsonSerializer<SetPreferenceRequest> INSTANCE = new SetPreferenceRequestSerializer();
    private final SetPreferenceRequestFieldSerializer mFieldSerializer = new SetPreferenceRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SetPreferenceRequestFieldSerializer implements JsonFieldSerializer<SetPreferenceRequest> {
        SetPreferenceRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SetPreferenceRequestFieldSerializer) ((SetPreferenceRequest) obj), jsonGenerator);
        }

        public <U extends SetPreferenceRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("preferenceValue");
            SimpleSerializers.serializeString(u.getPreferenceValue(), jsonGenerator);
        }
    }

    private SetPreferenceRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SetPreferenceRequest setPreferenceRequest, JsonGenerator jsonGenerator) throws IOException {
        if (setPreferenceRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SetPreferenceRequestFieldSerializer) setPreferenceRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
