package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupNode;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleListDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupNodeDeserializer implements JsonDeserializer<GroupNode> {
    public static final JsonDeserializer<GroupNode> INSTANCE = new GroupNodeDeserializer();
    public static final ListDeserializer<GroupNode> LIST_DESERIALIZER = new ListDeserializer<>(INSTANCE);
    private final GroupNodeFieldDeserializer mFieldHandler = new GroupNodeFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GroupNodeFieldDeserializer implements JsonFieldDeserializer<GroupNode> {
        GroupNodeFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GroupNode) obj));
        }

        public <U extends GroupNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("collectionIds".equals(str)) {
                u.setCollectionIds(SimpleListDeserializers.STRING_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            } else if ("node".equals(str)) {
                u.setNodeExtended(NodeExtendedDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"reactionSummary".equals(str)) {
                return false;
            } else {
                u.setReactionSummary(ReactionSummaryDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GroupNodeDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupNode mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupNode groupNode = new GroupNode();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) groupNode)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return groupNode;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
