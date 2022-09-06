package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.ResubscribeResponse;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ResubscribeResponseSerializer implements JsonSerializer<ResubscribeResponse> {
    public static final JsonSerializer<ResubscribeResponse> INSTANCE = new ResubscribeResponseSerializer();
    private final ResubscribeResponseFieldSerializer mFieldSerializer = new ResubscribeResponseFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ResubscribeResponseFieldSerializer implements JsonFieldSerializer<ResubscribeResponse> {
        ResubscribeResponseFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ResubscribeResponseFieldSerializer) ((ResubscribeResponse) obj), jsonGenerator);
        }

        public <U extends ResubscribeResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("subscription");
            CloudDriveSubscriptionSerializer.INSTANCE.serialize(u.getSubscription(), jsonGenerator);
        }
    }

    private ResubscribeResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ResubscribeResponse resubscribeResponse, JsonGenerator jsonGenerator) throws IOException {
        if (resubscribeResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ResubscribeResponseFieldSerializer) resubscribeResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
