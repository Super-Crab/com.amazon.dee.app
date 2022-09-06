package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupRestriction;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupRestrictionDeserializer implements JsonDeserializer<GroupRestriction> {
    public static final JsonDeserializer<GroupRestriction> INSTANCE = new GroupRestrictionDeserializer();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupRestriction mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupRestriction.GroupRestrictionBuilder builder = GroupRestriction.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("isContentRestricted".equals(currentName)) {
                            builder.isContentRestricted(SimpleDeserializers.deserializeBoolean(jsonParser));
                        } else if ("isReactionRestricted".equals(currentName)) {
                            builder.isReactionRestricted(SimpleDeserializers.deserializeBoolean(jsonParser));
                        } else if ("isJoinRestricted".equals(currentName)) {
                            builder.isJoinRestricted(SimpleDeserializers.deserializeBoolean(jsonParser));
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
