package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.ServicePlan;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
class ServicePlansListSerializer implements JsonSerializer<List<ServicePlan>> {
    public static final JsonSerializer<List<ServicePlan>> INSTANCE = new ServicePlansListSerializer();

    private ServicePlansListSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(List<ServicePlan> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (ServicePlan servicePlan : list) {
            ServicePlanSerializer.INSTANCE.serialize(servicePlan, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
