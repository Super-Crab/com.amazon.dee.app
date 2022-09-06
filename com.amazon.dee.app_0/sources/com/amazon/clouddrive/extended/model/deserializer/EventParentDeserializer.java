package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.EventParent;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class EventParentDeserializer implements JsonDeserializer<EventParent> {
    public static final JsonDeserializer<EventParent> INSTANCE = new EventParentDeserializer();
    private final EventParentFieldDeserializer mFieldHandler = new EventParentFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class EventParentFieldDeserializer implements JsonFieldDeserializer<EventParent> {
        EventParentFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((EventParent) obj));
        }

        public <U extends EventParent> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME.equals(str)) {
                u.setKind(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("id".equals(str)) {
                u.setId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("parentReaction".equals(str)) {
                u.setParentReaction(ReactionDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"parentCollection".equals(str)) {
                return false;
            } else {
                u.setParentCollection(GroupCollectionDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private EventParentDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public EventParent mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            EventParent eventParent = new EventParent();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) eventParent)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return eventParent;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
