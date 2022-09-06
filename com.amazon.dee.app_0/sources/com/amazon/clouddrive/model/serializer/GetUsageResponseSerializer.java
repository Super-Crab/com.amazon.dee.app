package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetAccountUsageResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetUsageResponseSerializer implements JsonSerializer<GetAccountUsageResponse> {
    public static final JsonSerializer<GetAccountUsageResponse> INSTANCE = new GetUsageResponseSerializer();
    private final GetUsageResponseFieldSerializer mFieldSerializer = new GetUsageResponseFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetUsageResponseFieldSerializer implements JsonFieldSerializer<GetAccountUsageResponse> {
        GetUsageResponseFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetUsageResponseFieldSerializer) ((GetAccountUsageResponse) obj), jsonGenerator);
        }

        public <U extends GetAccountUsageResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("video");
            UsageSummarySerializer.INSTANCE.serialize(u.getVideo(), jsonGenerator);
            jsonGenerator.writeFieldName("other");
            UsageSummarySerializer.INSTANCE.serialize(u.getOther(), jsonGenerator);
            jsonGenerator.writeFieldName("lastCalculated");
            SimpleSerializers.serializeString(u.getLastCalculated(), jsonGenerator);
            jsonGenerator.writeFieldName("doc");
            UsageSummarySerializer.INSTANCE.serialize(u.getDoc(), jsonGenerator);
            jsonGenerator.writeFieldName("photo");
            UsageSummarySerializer.INSTANCE.serialize(u.getPhoto(), jsonGenerator);
        }
    }

    private GetUsageResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetAccountUsageResponse getAccountUsageResponse, JsonGenerator jsonGenerator) throws IOException {
        if (getAccountUsageResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetUsageResponseFieldSerializer) getAccountUsageResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
