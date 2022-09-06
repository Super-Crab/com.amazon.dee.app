package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListReactionsResponse;
import com.amazon.clouddrive.extended.model.Reaction;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListReactionsResponseDeserializer implements JsonDeserializer<ListReactionsResponse> {
    private static final ListDeserializer<Reaction> REACTIONS_DESERIALIZER = new ListDeserializer<>(ReactionDeserializer.INSTANCE);
    public static JsonDeserializer<ListReactionsResponse> INSTANCE = new ListReactionsResponseDeserializer();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListReactionsResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListReactionsResponse.Builder builder = new ListReactionsResponse.Builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("nextToken".equals(currentName)) {
                            builder.withToken(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("count".equals(currentName)) {
                            builder.withCount(SimpleDeserializers.deserializeInteger(jsonParser));
                        } else if ("reactions".equals(currentName)) {
                            builder.withReactions(REACTIONS_DESERIALIZER.mo3229deserialize(jsonParser));
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
