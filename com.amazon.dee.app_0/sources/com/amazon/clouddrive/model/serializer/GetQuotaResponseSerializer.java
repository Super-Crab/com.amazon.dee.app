package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetAccountQuotaResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetQuotaResponseSerializer implements JsonSerializer<GetAccountQuotaResponse> {
    public static final JsonSerializer<GetAccountQuotaResponse> INSTANCE = new GetQuotaResponseSerializer();
    private final GetQuotaResponseFieldSerializer mFieldSerializer = new GetQuotaResponseFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetQuotaResponseFieldSerializer implements JsonFieldSerializer<GetAccountQuotaResponse> {
        GetQuotaResponseFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetQuotaResponseFieldSerializer) ((GetAccountQuotaResponse) obj), jsonGenerator);
        }

        public <U extends GetAccountQuotaResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("quota");
            SimpleSerializers.serializePrimitiveLong(u.getQuota(), jsonGenerator);
            jsonGenerator.writeFieldName("lastCalculated");
            SimpleSerializers.serializeString(u.getLastCalculated(), jsonGenerator);
            jsonGenerator.writeFieldName("available");
            SimpleSerializers.serializePrimitiveLong(u.getAvailable(), jsonGenerator);
        }
    }

    private GetQuotaResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetAccountQuotaResponse getAccountQuotaResponse, JsonGenerator jsonGenerator) throws IOException {
        if (getAccountQuotaResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetQuotaResponseFieldSerializer) getAccountQuotaResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
