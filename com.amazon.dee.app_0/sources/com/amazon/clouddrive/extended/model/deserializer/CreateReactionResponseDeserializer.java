package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CreateReactionResponse;
import com.amazon.clouddrive.extended.model.deserializer.ReactionDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CreateReactionResponseDeserializer implements JsonDeserializer<CreateReactionResponse> {
    public static final JsonDeserializer<CreateReactionResponse> INSTANCE = new CreateReactionResponseDeserializer();
    private final ReactionDeserializer.ReactionFieldDeserializer mFieldHandler = new ReactionDeserializer.ReactionFieldDeserializer();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CreateReactionResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CreateReactionResponse.Builder builder = new CreateReactionResponse.Builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) builder)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return builder.mo2999build();
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
