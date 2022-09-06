package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.UsageSummary;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UsageSummarySerializer implements JsonSerializer<UsageSummary> {
    public static final JsonSerializer<UsageSummary> INSTANCE = new UsageSummarySerializer();
    private final UsageSummaryFieldSerializer mFieldSerializer = new UsageSummaryFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UsageSummaryFieldSerializer implements JsonFieldSerializer<UsageSummary> {
        UsageSummaryFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((UsageSummaryFieldSerializer) ((UsageSummary) obj), jsonGenerator);
        }

        public <U extends UsageSummary> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("billable");
            UsageDetailSerializer.INSTANCE.serialize(u.getBillable(), jsonGenerator);
            jsonGenerator.writeFieldName("total");
            UsageDetailSerializer.INSTANCE.serialize(u.getTotal(), jsonGenerator);
        }
    }

    private UsageSummarySerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(UsageSummary usageSummary, JsonGenerator jsonGenerator) throws IOException {
        if (usageSummary == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((UsageSummaryFieldSerializer) usageSummary, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
