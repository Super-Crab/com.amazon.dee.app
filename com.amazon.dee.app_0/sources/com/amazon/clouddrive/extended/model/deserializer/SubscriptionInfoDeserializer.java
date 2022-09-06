package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.SubscriptionInfo;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class SubscriptionInfoDeserializer implements JsonDeserializer<SubscriptionInfo> {
    public static final JsonDeserializer<SubscriptionInfo> INSTANCE = new SubscriptionInfoDeserializer();
    private final SubscriptionInfoFieldDeserializer mFieldHandler = new SubscriptionInfoFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SubscriptionInfoFieldDeserializer implements JsonFieldDeserializer<SubscriptionInfo> {
        SubscriptionInfoFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((SubscriptionInfo) obj));
        }

        public <U extends SubscriptionInfo> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("contractEndDate".equals(str)) {
                u.setContractDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("autoRenewEnabled".equals(str)) {
                u.setAutoRenewEnabled(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("statusStartDate".equals(str)) {
                u.setStatusStartDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("subscriptionStartDate".equals(str)) {
                u.setSubscriptionStartDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("currentBillingPeriod".equals(str)) {
                u.setCurrentBillingPeriod(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if ("planId".equals(str)) {
                u.setPlanId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("currentContractPeriod".equals(str)) {
                u.setCurrentContractPeriod(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if (WebConstants.WebviewConstants.MARKETPLACE_ID.equals(str)) {
                u.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("hasOpenProblems".equals(str)) {
                u.setHasOpenProblems(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("subscriptionStatus".equals(str)) {
                u.setSubscriptionStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!WebConstants.WebviewConstants.SUBSCRIPTION_ID.equals(str)) {
                return false;
            } else {
                u.setSubscriptionId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private SubscriptionInfoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public SubscriptionInfo mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) subscriptionInfo)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return subscriptionInfo;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
