package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.CloudDriveSubscription;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CloudDriveSubscriptionDeserializer implements JsonDeserializer<CloudDriveSubscription> {
    public static final JsonDeserializer<CloudDriveSubscription> INSTANCE = new CloudDriveSubscriptionDeserializer();
    private final CloudDriveSubscriptionFieldDeserializer mFieldHandler = new CloudDriveSubscriptionFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CloudDriveSubscriptionFieldDeserializer implements JsonFieldDeserializer<CloudDriveSubscription> {
        CloudDriveSubscriptionFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CloudDriveSubscription) obj));
        }

        public <U extends CloudDriveSubscription> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("baseCurrencyCode".equals(str)) {
                u.setBaseCurrencyCode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("autoRenewEnabled".equals(str)) {
                u.setAutoRenewEnabled(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("currentContractPeriod".equals(str)) {
                u.setCurrentContractPeriod(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if (WebConstants.WebviewConstants.SUBSCRIPTION_ID.equals(str)) {
                u.setSubscriptionId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("subscriptionStartDate".equals(str)) {
                u.setSubscriptionStartDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (WebConstants.WebviewConstants.MARKETPLACE_ID.equals(str)) {
                u.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("pendingPlan".equals(str)) {
                u.setPendingPlan(PendingPlanDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("expectedStatusEndDate".equals(str)) {
                u.setExpectedStatusEndDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("subscriptionProblemsList".equals(str)) {
                u.setSubscriptionProblemsList(SubscriptionProblemListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("planId".equals(str)) {
                u.setPlanId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("preferredPaymentPlanId".equals(str)) {
                u.setPreferredPaymentPlanId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("gracePeriodBeforeCancellation".equals(str)) {
                u.setGracePeriodBeforeCancellation(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("currentBillingPeriod".equals(str)) {
                u.setCurrentBillingPeriod(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("nextBillDate".equals(str)) {
                u.setNextBillDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("nextBillAmount".equals(str)) {
                u.setNextBillAmount(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("contractEndDate".equals(str)) {
                u.setContractEndDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("subscriptionStatus".equals(str)) {
                u.setSubscriptionStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("hasOpenProblems".equals(str)) {
                u.setHasOpenProblems(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("statusStartDate".equals(str)) {
                u.setStatusStartDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"isMFARequired".equals(str)) {
                return false;
            } else {
                u.setMFARequired(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            }
        }
    }

    private CloudDriveSubscriptionDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CloudDriveSubscription mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CloudDriveSubscription cloudDriveSubscription = new CloudDriveSubscription();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) cloudDriveSubscription)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return cloudDriveSubscription;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
