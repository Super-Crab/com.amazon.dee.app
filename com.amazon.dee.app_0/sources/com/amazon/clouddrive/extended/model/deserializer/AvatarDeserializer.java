package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Avatar;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class AvatarDeserializer implements JsonDeserializer<Avatar> {
    public static final JsonDeserializer<Avatar> INSTANCE = new AvatarDeserializer();
    private final AvatarFieldDeserializer mFieldHandler = new AvatarFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AvatarFieldDeserializer implements JsonFieldDeserializer<Avatar> {
        AvatarFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Avatar) obj));
        }

        public <U extends Avatar> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("color".equals(str)) {
                u.setColor(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"photo".equals(str)) {
                return false;
            } else {
                u.setPhoto(GroupCoverPhotoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private AvatarDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Avatar mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Avatar avatar = new Avatar();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) avatar)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return avatar;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
