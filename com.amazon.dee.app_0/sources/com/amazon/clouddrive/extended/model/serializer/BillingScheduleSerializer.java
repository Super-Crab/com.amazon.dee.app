package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BillingSchedule;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BillingScheduleSerializer implements JsonSerializer<BillingSchedule> {
    public static final JsonSerializer<BillingSchedule> INSTANCE = new BillingScheduleSerializer();
    private final BillingScheduleFieldSerializer mFieldSerializer = new BillingScheduleFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BillingScheduleFieldSerializer implements JsonFieldSerializer<BillingSchedule> {
        BillingScheduleFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((BillingScheduleFieldSerializer) ((BillingSchedule) obj), jsonGenerator);
        }

        public <U extends BillingSchedule> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("billingPeriod");
            PeriodSerializer.INSTANCE.serialize(u.getBillingPeriod(), jsonGenerator);
            jsonGenerator.writeFieldName("currencyCode");
            SimpleSerializers.serializeString(u.getCurrencyCode(), jsonGenerator);
            jsonGenerator.writeFieldName("price");
            SimpleSerializers.serializeString(u.getPrice(), jsonGenerator);
        }
    }

    private BillingScheduleSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(BillingSchedule billingSchedule, JsonGenerator jsonGenerator) throws IOException {
        if (billingSchedule == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((BillingScheduleFieldSerializer) billingSchedule, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
