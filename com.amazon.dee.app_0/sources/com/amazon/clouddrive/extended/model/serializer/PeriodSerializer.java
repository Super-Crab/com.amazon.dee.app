package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.Period;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PeriodSerializer implements JsonSerializer<Period> {
    public static final JsonSerializer<Period> INSTANCE = new PeriodSerializer();
    private final PeriodFieldSerializer mFieldSerializer = new PeriodFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PeriodFieldSerializer implements JsonFieldSerializer<Period> {
        PeriodFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PeriodFieldSerializer) ((Period) obj), jsonGenerator);
        }

        public <U extends Period> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("timeInterval");
            SimpleSerializers.serializeString(u.getTimeInterval(), jsonGenerator);
            jsonGenerator.writeFieldName("numberOfIntervals");
            SimpleSerializers.serializePrimitiveLong(u.getNumberOfIntervals(), jsonGenerator);
        }
    }

    private PeriodSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(Period period, JsonGenerator jsonGenerator) throws IOException {
        if (period == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PeriodFieldSerializer) period, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
