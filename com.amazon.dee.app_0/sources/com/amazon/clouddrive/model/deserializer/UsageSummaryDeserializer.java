package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.UsageSummary;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class UsageSummaryDeserializer implements JsonDeserializer<UsageSummary> {
    public static final JsonDeserializer<UsageSummary> INSTANCE = new UsageSummaryDeserializer();
    private final UsageSummaryFieldDeserializer mFieldHandler = new UsageSummaryFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UsageSummaryFieldDeserializer implements JsonFieldDeserializer<UsageSummary> {
        UsageSummaryFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((UsageSummary) obj));
        }

        public <U extends UsageSummary> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("billable".equals(str)) {
                u.setBillable(UsageDetailDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"total".equals(str)) {
                return false;
            } else {
                u.setTotal(UsageDetailDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private UsageSummaryDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public UsageSummary mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            UsageSummary usageSummary = new UsageSummary();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) usageSummary)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return usageSummary;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
