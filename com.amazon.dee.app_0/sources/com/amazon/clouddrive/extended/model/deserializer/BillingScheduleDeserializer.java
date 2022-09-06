package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BillingSchedule;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class BillingScheduleDeserializer implements JsonDeserializer<BillingSchedule> {
    public static final JsonDeserializer<BillingSchedule> INSTANCE = new BillingScheduleDeserializer();
    private final BillingScheduleFieldDeserializer mFieldHandler = new BillingScheduleFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BillingScheduleFieldDeserializer implements JsonFieldDeserializer<BillingSchedule> {
        BillingScheduleFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((BillingSchedule) obj));
        }

        public <U extends BillingSchedule> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("billingPeriod".equals(str)) {
                u.setBillingPeriod(PeriodDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("currencyCode".equals(str)) {
                u.setCurrencyCode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"price".equals(str)) {
                return false;
            } else {
                u.setPrice(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private BillingScheduleDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public BillingSchedule mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            BillingSchedule billingSchedule = new BillingSchedule();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) billingSchedule)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return billingSchedule;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
