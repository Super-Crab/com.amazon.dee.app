package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SubscriptionProblem;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class SubscriptionProblemDeserializer implements JsonDeserializer<SubscriptionProblem> {
    public static final JsonDeserializer<SubscriptionProblem> INSTANCE = new SubscriptionProblemDeserializer();
    private final SubscriptionProblemFieldDeserializer mFieldHandler = new SubscriptionProblemFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SubscriptionProblemFieldDeserializer implements JsonFieldDeserializer<SubscriptionProblem> {
        SubscriptionProblemFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((SubscriptionProblem) obj));
        }

        public <U extends SubscriptionProblem> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("effectiveDate".equals(str)) {
                u.setEffectiveDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("billingPeriodNumber".equals(str)) {
                u.setBillingPeriodNumber(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("problemID".equals(str)) {
                u.setProblemID(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("problemCode".equals(str)) {
                u.setProblemCode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"problemStatus".equals(str)) {
                return false;
            } else {
                u.setProblemStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private SubscriptionProblemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public SubscriptionProblem mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            SubscriptionProblem subscriptionProblem = new SubscriptionProblem();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) subscriptionProblem)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return subscriptionProblem;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
