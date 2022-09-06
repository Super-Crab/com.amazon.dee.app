package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Profile;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ProfileDeserializer implements JsonDeserializer<Profile> {
    public static final JsonDeserializer<Profile> INSTANCE = new ProfileDeserializer();
    private final ProfileFieldDeserializer mFieldHandler = new ProfileFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ProfileFieldDeserializer implements JsonFieldDeserializer<Profile> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Profile) obj));
        }

        public <U extends Profile> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("avatar".equals(str)) {
                u.setAvatar(AvatarDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("customerId".equals(str)) {
                u.setCustomerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("names".equals(str)) {
                u.setNames(NamesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"unseenAggregations".equals(str)) {
                return false;
            } else {
                u.setUnseenAggregations(UnseenAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private ProfileDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Profile mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Profile profile = new Profile();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) profile)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return profile;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
