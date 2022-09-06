package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.PendingPlan;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PendingPlanSerializer implements JsonSerializer<PendingPlan> {
    public static final JsonSerializer<PendingPlan> INSTANCE = new PendingPlanSerializer();
    private final PendingPlanFieldSerializer mFieldSerializer = new PendingPlanFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PendingPlanFieldSerializer implements JsonFieldSerializer<PendingPlan> {
        PendingPlanFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PendingPlanFieldSerializer) ((PendingPlan) obj), jsonGenerator);
        }

        public <U extends PendingPlan> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("planId");
            SimpleSerializers.serializeString(u.getPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName("endDate");
            SimpleSerializers.serializeString(u.getEndDate(), jsonGenerator);
            jsonGenerator.writeFieldName("startDate");
            SimpleSerializers.serializeString(u.getStartDate(), jsonGenerator);
        }
    }

    private PendingPlanSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(PendingPlan pendingPlan, JsonGenerator jsonGenerator) throws IOException {
        if (pendingPlan == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PendingPlanFieldSerializer) pendingPlan, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
