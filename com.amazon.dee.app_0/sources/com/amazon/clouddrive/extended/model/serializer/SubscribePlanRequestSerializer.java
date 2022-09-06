package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.SubscribePlanRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SubscribePlanRequestSerializer implements JsonSerializer<SubscribePlanRequest> {
    public static final JsonSerializer<SubscribePlanRequest> INSTANCE = new SubscribePlanRequestSerializer();
    private final SubscribePlanRequestFieldSerializer mFieldSerializer = new SubscribePlanRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SubscribePlanRequestFieldSerializer implements JsonFieldSerializer<SubscribePlanRequest> {
        SubscribePlanRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SubscribePlanRequestFieldSerializer) ((SubscribePlanRequest) obj), jsonGenerator);
        }

        public <U extends SubscribePlanRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("planId");
            SimpleSerializers.serializeString(u.getPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName("pendingPlanId");
            SimpleSerializers.serializeString(u.getPendingPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName("paymentInstrumentId");
            SimpleSerializers.serializeString(u.getPaymentInstrumentId(), jsonGenerator);
            jsonGenerator.writeFieldName(WebConstants.WebviewConstants.MARKETPLACE_ID);
            SimpleSerializers.serializeString(u.getMarketplaceId(), jsonGenerator);
        }
    }

    private SubscribePlanRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(SubscribePlanRequest subscribePlanRequest, JsonGenerator jsonGenerator) throws IOException {
        if (subscribePlanRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SubscribePlanRequestFieldSerializer) subscribePlanRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
