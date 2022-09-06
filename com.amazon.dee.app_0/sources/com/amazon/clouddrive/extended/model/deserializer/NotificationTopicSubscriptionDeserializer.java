package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.NotificationTopicSubscription;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class NotificationTopicSubscriptionDeserializer implements JsonDeserializer<NotificationTopicSubscription> {
    public static final JsonDeserializer<NotificationTopicSubscription> INSTANCE = new NotificationTopicSubscriptionDeserializer();
    private final NotificationTopicSubscriptionFieldDeserializer mFieldHandler = new NotificationTopicSubscriptionFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NotificationTopicSubscriptionFieldDeserializer implements JsonFieldDeserializer<NotificationTopicSubscription> {
        NotificationTopicSubscriptionFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((NotificationTopicSubscription) obj));
        }

        public <U extends NotificationTopicSubscription> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("notificationTopic".equals(str)) {
                u.setNotificationTopic(NotificationTopicDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"subscribed".equals(str)) {
                return false;
            } else {
                u.setSubscribed(SimpleDeserializers.deserializeBoolean(jsonParser).booleanValue());
                return true;
            }
        }
    }

    private NotificationTopicSubscriptionDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public NotificationTopicSubscription mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            NotificationTopicSubscription notificationTopicSubscription = new NotificationTopicSubscription();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) notificationTopicSubscription)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return notificationTopicSubscription;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
