package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.ResubscribeRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ResubscribeRequestSerializer implements JsonSerializer<ResubscribeRequest> {
    public static final JsonSerializer<ResubscribeRequest> INSTANCE = new ResubscribeRequestSerializer();
    private final ResubscribeRequestFieldSerializer mFieldSerializer = new ResubscribeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ResubscribeRequestFieldSerializer implements JsonFieldSerializer<ResubscribeRequest> {
        ResubscribeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ResubscribeRequestFieldSerializer) ((ResubscribeRequest) obj), jsonGenerator);
        }

        public <U extends ResubscribeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("planIdToSubscribe");
            SimpleSerializers.serializeString(u.getPlanIdToSubscribe(), jsonGenerator);
            jsonGenerator.writeFieldName("pendingPlanId");
            SimpleSerializers.serializeString(u.getPendingPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName(WebConstants.WebviewConstants.MARKETPLACE_ID);
            SimpleSerializers.serializeString(u.getMarketplaceId(), jsonGenerator);
            jsonGenerator.writeFieldName("paymentInstrumentId");
            SimpleSerializers.serializeString(u.getPaymentInstrumentId(), jsonGenerator);
            jsonGenerator.writeFieldName(WebConstants.WebviewConstants.SUBSCRIPTION_ID);
            SimpleSerializers.serializeString(u.getSubscriptionId(), jsonGenerator);
            jsonGenerator.writeFieldName("refundPercent");
            SimpleSerializers.serializeDouble(u.getRefundPercent(), jsonGenerator);
        }
    }

    private ResubscribeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ResubscribeRequest resubscribeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (resubscribeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ResubscribeRequestFieldSerializer) resubscribeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
