package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListNotificationTopicSubscriptionResponse;
import com.amazon.clouddrive.extended.model.NotificationTopicSubscription;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListNotificationTopicSubscriptionsResponseDeserializer implements JsonDeserializer<ListNotificationTopicSubscriptionResponse> {
    public static final JsonDeserializer<ListNotificationTopicSubscriptionResponse> INSTANCE = new ListNotificationTopicSubscriptionsResponseDeserializer();
    private static final JsonDeserializer<List<NotificationTopicSubscription>> SUBSCRIPTIONS_DESERIALIZER = new ListDeserializer(NotificationTopicSubscriptionDeserializer.INSTANCE);

    private ListNotificationTopicSubscriptionsResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListNotificationTopicSubscriptionResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListNotificationTopicSubscriptionResponse listNotificationTopicSubscriptionResponse = new ListNotificationTopicSubscriptionResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("notificationTopicSubscriptions".equals(currentName)) {
                            listNotificationTopicSubscriptionResponse.setNotificationTopicSubscriptions(SUBSCRIPTIONS_DESERIALIZER.mo3229deserialize(jsonParser));
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
            return listNotificationTopicSubscriptionResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
