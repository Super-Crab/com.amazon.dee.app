package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.ServicePlan;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ServicePlanSerializer implements JsonSerializer<ServicePlan> {
    public static final JsonSerializer<ServicePlan> INSTANCE = new ServicePlanSerializer();
    private final ServicePlanFieldSerializer mFieldSerializer = new ServicePlanFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ServicePlanFieldSerializer implements JsonFieldSerializer<ServicePlan> {
        ServicePlanFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ServicePlanFieldSerializer) ((ServicePlan) obj), jsonGenerator);
        }

        public <U extends ServicePlan> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("planType");
            SimpleSerializers.serializeString(u.getPlanType(), jsonGenerator);
            jsonGenerator.writeFieldName("planId");
            SimpleSerializers.serializeString(u.getPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName("renewable");
            SimpleSerializers.serializePrimitiveBoolean(u.isRenewable(), jsonGenerator);
            jsonGenerator.writeFieldName("planDescription");
            SimpleSerializers.serializeString(u.getPlanDescription(), jsonGenerator);
            jsonGenerator.writeFieldName("storageMap");
            StorageMapSerializer.INSTANCE.serialize(u.getStorageMap(), jsonGenerator);
            jsonGenerator.writeFieldName("isPromotion");
            SimpleSerializers.serializePrimitiveBoolean(u.isPromotion(), jsonGenerator);
            jsonGenerator.writeFieldName(WebConstants.WebviewConstants.MARKETPLACE_ID);
            SimpleSerializers.serializeString(u.getMarketplaceId(), jsonGenerator);
            jsonGenerator.writeFieldName("billingSchedule");
            BillingScheduleSerializer.INSTANCE.serialize(u.getBillingSchedule(), jsonGenerator);
            jsonGenerator.writeFieldName("priceAttribute");
            SimpleSerializers.serializeString(u.getPriceAttribute(), jsonGenerator);
            jsonGenerator.writeFieldName("available");
            SimpleSerializers.serializePrimitiveBoolean(u.isAvailable(), jsonGenerator);
            jsonGenerator.writeFieldName("contractLength");
            PeriodSerializer.INSTANCE.serialize(u.getContractLength(), jsonGenerator);
            jsonGenerator.writeFieldName("planGroupId");
            SimpleSerializers.serializeString(u.getPlanGroupId(), jsonGenerator);
        }
    }

    private ServicePlanSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(ServicePlan servicePlan, JsonGenerator jsonGenerator) throws IOException {
        if (servicePlan == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ServicePlanFieldSerializer) servicePlan, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
