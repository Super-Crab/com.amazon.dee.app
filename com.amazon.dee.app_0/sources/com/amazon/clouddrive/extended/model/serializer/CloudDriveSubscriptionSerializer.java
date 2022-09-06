package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.CloudDriveSubscription;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CloudDriveSubscriptionSerializer implements JsonSerializer<CloudDriveSubscription> {
    public static final JsonSerializer<CloudDriveSubscription> INSTANCE = new CloudDriveSubscriptionSerializer();
    private final CloudDriveSubscriptionFieldSerializer mFieldSerializer = new CloudDriveSubscriptionFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CloudDriveSubscriptionFieldSerializer implements JsonFieldSerializer<CloudDriveSubscription> {
        CloudDriveSubscriptionFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CloudDriveSubscriptionFieldSerializer) ((CloudDriveSubscription) obj), jsonGenerator);
        }

        public <U extends CloudDriveSubscription> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("baseCurrencyCode");
            SimpleSerializers.serializeString(u.getBaseCurrencyCode(), jsonGenerator);
            jsonGenerator.writeFieldName("autoRenewEnabled");
            SimpleSerializers.serializeBoolean(u.isAutoRenewEnabled(), jsonGenerator);
            jsonGenerator.writeFieldName("currentContractPeriod");
            SimpleSerializers.serializeLong(u.getCurrentContractPeriod(), jsonGenerator);
            jsonGenerator.writeFieldName(WebConstants.WebviewConstants.SUBSCRIPTION_ID);
            SimpleSerializers.serializeString(u.getSubscriptionId(), jsonGenerator);
            jsonGenerator.writeFieldName("subscriptionStartDate");
            SimpleSerializers.serializeString(u.getSubscriptionStartDate(), jsonGenerator);
            jsonGenerator.writeFieldName(WebConstants.WebviewConstants.MARKETPLACE_ID);
            SimpleSerializers.serializeString(u.getMarketplaceId(), jsonGenerator);
            jsonGenerator.writeFieldName("pendingPlan");
            PendingPlanSerializer.INSTANCE.serialize(u.getPendingPlan(), jsonGenerator);
            jsonGenerator.writeFieldName("expectedStatusEndDate");
            SimpleSerializers.serializeString(u.getExpectedStatusEndDate(), jsonGenerator);
            jsonGenerator.writeFieldName("subscriptionProblemsList");
            SubscriptionProblemListSerializer.INSTANCE.serialize(u.getSubscriptionProblemsList(), jsonGenerator);
            jsonGenerator.writeFieldName("planId");
            SimpleSerializers.serializeString(u.getPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName("preferredPaymentPlanId");
            SimpleSerializers.serializeString(u.getPreferredPaymentPlanId(), jsonGenerator);
            jsonGenerator.writeFieldName("gracePeriodBeforeCancellation");
            SimpleSerializers.serializeLong(u.getGracePeriodBeforeCancellation(), jsonGenerator);
            jsonGenerator.writeFieldName("currentBillingPeriod");
            SimpleSerializers.serializeLong(u.getCurrentBillingPeriod(), jsonGenerator);
            jsonGenerator.writeFieldName("nextBillDate");
            SimpleSerializers.serializeString(u.getNextBillDate(), jsonGenerator);
            jsonGenerator.writeFieldName("nextBillAmount");
            SimpleSerializers.serializeString(u.getNextBillAmount(), jsonGenerator);
            jsonGenerator.writeFieldName("contractEndDate");
            SimpleSerializers.serializeString(u.getContractEndDate(), jsonGenerator);
            jsonGenerator.writeFieldName("subscriptionStatus");
            SimpleSerializers.serializeString(u.getSubscriptionStatus(), jsonGenerator);
            jsonGenerator.writeFieldName("hasOpenProblems");
            SimpleSerializers.serializeBoolean(u.isHasOpenProblems(), jsonGenerator);
            jsonGenerator.writeFieldName("statusStartDate");
            SimpleSerializers.serializeString(u.getStatusStartDate(), jsonGenerator);
            jsonGenerator.writeFieldName("isMFARequired");
            SimpleSerializers.serializeBoolean(u.isMFARequired(), jsonGenerator);
        }
    }

    private CloudDriveSubscriptionSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(CloudDriveSubscription cloudDriveSubscription, JsonGenerator jsonGenerator) throws IOException {
        if (cloudDriveSubscription == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CloudDriveSubscriptionFieldSerializer) cloudDriveSubscription, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
