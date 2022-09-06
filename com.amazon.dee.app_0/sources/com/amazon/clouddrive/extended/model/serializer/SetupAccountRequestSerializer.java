package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.SetupAccountRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SetupAccountRequestSerializer implements JsonSerializer<SetupAccountRequest> {
    public static final JsonSerializer<SetupAccountRequest> INSTANCE = new SetupAccountRequestSerializer();
    private final SetupAccountRequestFieldSerializer mFieldSerializer = new SetupAccountRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SetupAccountRequestFieldSerializer implements JsonFieldSerializer<SetupAccountRequest> {
        SetupAccountRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SetupAccountRequestFieldSerializer) ((SetupAccountRequest) obj), jsonGenerator);
        }

        public <U extends SetupAccountRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("termsOfUse");
            SimpleSerializers.serializeString(u.getTermsOfUse(), jsonGenerator);
        }
    }

    private SetupAccountRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SetupAccountRequest setupAccountRequest, JsonGenerator jsonGenerator) throws IOException {
        if (setupAccountRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SetupAccountRequestFieldSerializer) setupAccountRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
