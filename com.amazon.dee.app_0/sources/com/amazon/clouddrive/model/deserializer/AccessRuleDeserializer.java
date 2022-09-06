package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.AccessRule;
import com.amazon.clouddrive.model.NodeAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.HashSet;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class AccessRuleDeserializer implements JsonDeserializer<AccessRule> {
    public static final AccessRuleDeserializer INSTANCE = new AccessRuleDeserializer();

    private AccessRuleDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public AccessRule mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        String str = null;
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            AbstractSet allOf = EnumSet.allOf(NodeAction.class);
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (currentName.equals("ruleId")) {
                            str = SimpleDeserializers.deserializeString(jsonParser);
                        } else if (currentName.equals("allowedNodeActions")) {
                            allOf = new HashSet(new ListDeserializer(NodeActionDeserializer.INSTANCE).mo3229deserialize(jsonParser));
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
            return new AccessRule(str, allOf);
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
