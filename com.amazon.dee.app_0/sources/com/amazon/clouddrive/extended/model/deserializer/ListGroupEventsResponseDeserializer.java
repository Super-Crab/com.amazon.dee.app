package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListGroupEventsResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListGroupEventsResponseDeserializer implements JsonDeserializer<ListGroupEventsResponse> {
    public static final JsonDeserializer<ListGroupEventsResponse> INSTANCE = new ListGroupEventsResponseDeserializer();
    private final ListGroupEventsFieldDeserializer mFieldHandler = new ListGroupEventsFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ListGroupEventsFieldDeserializer implements JsonFieldDeserializer<ListGroupEventsResponse> {
        ListGroupEventsFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ListGroupEventsResponse) obj));
        }

        public <U extends ListGroupEventsResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("nextToken".equals(str)) {
                u.setNextToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("previousToken".equals(str)) {
                u.setPreviousToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!DefaultDeliveryClient.EVENTS_DIRECTORY.equals(str)) {
                return false;
            } else {
                u.setEvents(GroupEventDeserializer.LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private ListGroupEventsResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListGroupEventsResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListGroupEventsResponse listGroupEventsResponse = new ListGroupEventsResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) listGroupEventsResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return listGroupEventsResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
