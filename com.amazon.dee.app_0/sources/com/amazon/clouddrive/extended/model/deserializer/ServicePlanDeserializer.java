package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.extended.model.ServicePlan;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ServicePlanDeserializer implements JsonDeserializer<ServicePlan> {
    public static final JsonDeserializer<ServicePlan> INSTANCE = new ServicePlanDeserializer();
    private final ServicePlanFieldDeserializer mFieldHandler = new ServicePlanFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ServicePlanFieldDeserializer implements JsonFieldDeserializer<ServicePlan> {
        ServicePlanFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ServicePlan) obj));
        }

        public <U extends ServicePlan> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("planType".equals(str)) {
                u.setPlanType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("planId".equals(str)) {
                u.setPlanId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("renewable".equals(str)) {
                u.setRenewable(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                return true;
            } else if ("planDescription".equals(str)) {
                u.setPlanDescription(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("storageMap".equals(str)) {
                u.setStorageMap(StorageMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("isPromotion".equals(str)) {
                u.setPromotion(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                return true;
            } else if (WebConstants.WebviewConstants.MARKETPLACE_ID.equals(str)) {
                u.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("billingSchedule".equals(str)) {
                u.setBillingSchedule(BillingScheduleDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("priceAttribute".equals(str)) {
                u.setPriceAttribute(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("available".equals(str)) {
                u.setAvailable(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                return true;
            } else if ("contractLength".equals(str)) {
                u.setContractLength(PeriodDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"planGroupId".equals(str)) {
                return false;
            } else {
                u.setPlanGroupId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private ServicePlanDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ServicePlan mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ServicePlan servicePlan = new ServicePlan();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) servicePlan)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return servicePlan;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
