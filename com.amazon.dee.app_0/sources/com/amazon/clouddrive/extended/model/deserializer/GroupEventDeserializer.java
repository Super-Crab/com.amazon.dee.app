package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.clouddrive.extended.model.GroupEventResponse;
import com.amazon.clouddrive.extended.model.NodeExtended;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupEventDeserializer implements JsonDeserializer<GroupEventResponse> {
    public static final JsonDeserializer<GroupEventResponse> INSTANCE = new GroupEventDeserializer();
    public static final ListDeserializer<GroupEventResponse> LIST_DESERIALIZER = new ListDeserializer<>(INSTANCE);
    private static final ListDeserializer<NodeExtended> NODE_EXTENDED_LIST_DESERIALIZER = new ListDeserializer<>(NodeExtendedDeserializer.INSTANCE);
    private final GroupEventFieldDeserializer mFieldHandler = new GroupEventFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GroupEventFieldDeserializer implements JsonFieldDeserializer<GroupEventResponse> {
        GroupEventFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GroupEventResponse) obj));
        }

        public <U extends GroupEventResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("groupId".equals(str)) {
                u.setGroupId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("eventId".equals(str)) {
                u.setEventId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("addedBy".equals(str)) {
                u.setAddedBy(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("dateAdded".equals(str)) {
                u.setDateAdded(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("reactionTopic".equals(str)) {
                u.setReactionTopic(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("caption".equals(str)) {
                u.setCaption(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("subtitle".equals(str)) {
                u.setSubtitle(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("reactionSummary".equals(str)) {
                u.setReactionSummary(ReactionSummaryDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("contentAggregations".equals(str)) {
                u.setContentAggregations(ContentAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (RouteParameter.PARENT.equals(str)) {
                u.setParent(EventParentDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("addedByProfile".equals(str)) {
                u.setAddedByProfile(GetProfileResponseDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"coverNodes".equals(str)) {
                return false;
            } else {
                u.setCoverNodes(GroupEventDeserializer.NODE_EXTENDED_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GroupEventDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupEventResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupEventResponse groupEventResponse = new GroupEventResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) groupEventResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return groupEventResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
