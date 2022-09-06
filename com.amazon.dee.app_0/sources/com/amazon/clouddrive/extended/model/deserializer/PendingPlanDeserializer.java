package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.PendingPlan;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class PendingPlanDeserializer implements JsonDeserializer<PendingPlan> {
    public static final JsonDeserializer<PendingPlan> INSTANCE = new PendingPlanDeserializer();
    private final PendingPlanFieldDeserializer mFieldHandler = new PendingPlanFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PendingPlanFieldDeserializer implements JsonFieldDeserializer<PendingPlan> {
        PendingPlanFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((PendingPlan) obj));
        }

        public <U extends PendingPlan> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("planId".equals(str)) {
                u.setPlanId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("endDate".equals(str)) {
                u.setEndDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"startDate".equals(str)) {
                return false;
            } else {
                u.setStartDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private PendingPlanDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public PendingPlan mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            PendingPlan pendingPlan = new PendingPlan();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) pendingPlan)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return pendingPlan;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
