package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ReactionSummaries;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ReactionSummariesDeserializer implements JsonDeserializer<ReactionSummaries> {
    public static JsonDeserializer<ReactionSummaries> INSTANCE = new ReactionSummariesDeserializer();
    private final ReactionSummariesFieldDeserializer mFieldHandler = new ReactionSummariesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ReactionSummariesFieldDeserializer implements JsonFieldDeserializer<ReactionSummaries> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ReactionSummaries) obj));
        }

        public <U extends ReactionSummaries> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("errorMap".equals(str)) {
                u.setErrorMap(StringToStringMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"summaryMap".equals(str)) {
                return false;
            } else {
                u.setSummaryMap(ReactionSummaryMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ReactionSummaries mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ReactionSummaries reactionSummaries = new ReactionSummaries();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) reactionSummaries)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return reactionSummaries;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
