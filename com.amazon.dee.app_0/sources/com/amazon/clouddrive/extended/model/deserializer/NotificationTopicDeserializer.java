package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.amazon.clouddrive.extended.model.NotificationTopic;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class NotificationTopicDeserializer implements JsonDeserializer<NotificationTopic> {
    public static final JsonDeserializer<NotificationTopic> INSTANCE = new NotificationTopicDeserializer();
    private final NotificationTopicFieldDeserializer mFieldHandler = new NotificationTopicFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NotificationTopicFieldDeserializer implements JsonFieldDeserializer<NotificationTopic> {
        NotificationTopicFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((NotificationTopic) obj));
        }

        public <U extends NotificationTopic> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (CamerasRouteParameter.DISPLAY_NAME.equals(str)) {
                u.setDisplayName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("description".equals(str)) {
                u.setDescription(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"notificationTopicId".equals(str)) {
                return false;
            } else {
                u.setNotificationTopicId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private NotificationTopicDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public NotificationTopic mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            NotificationTopic notificationTopic = new NotificationTopic();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) notificationTopic)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return notificationTopic;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
