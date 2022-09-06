package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ReactionSummary;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ReactionSummaryDeserializer implements JsonDeserializer<ReactionSummary> {
    public static final JsonDeserializer<ReactionSummary> INSTANCE = new ReactionSummaryDeserializer();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ReactionSummary mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ReactionSummary.ReactionSummaryBuilder builder = ReactionSummary.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("count".equals(currentName)) {
                            builder.count(SimpleDeserializers.deserializeInteger(jsonParser));
                        } else if ("commentCount".equals(currentName)) {
                            builder.commentCount(SimpleDeserializers.deserializeInteger(jsonParser));
                        } else if ("likeCount".equals(currentName)) {
                            builder.likeCount(SimpleDeserializers.deserializeInteger(jsonParser));
                        } else if ("isLikedByCaller".equals(currentName)) {
                            builder.isLikedByCaller(SimpleDeserializers.deserializeBoolean(jsonParser));
                        } else if ("counts".equals(currentName)) {
                            builder.counts(new MapDeserializer($$Lambda$IB_sMoHfzE0IBoiySLVw0Kj07wc.INSTANCE).mo3229deserialize(jsonParser));
                        } else if ("callerReaction".equals(currentName)) {
                            builder.callerReaction(SimpleDeserializers.deserializeString(jsonParser));
                        } else {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return builder.build();
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
