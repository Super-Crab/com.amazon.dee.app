package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.cdasdk.aps.account.FeatureName;
import com.amazon.clouddrive.extended.model.ListGroupsResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class ListGroupsResponseDeserializer extends AbstractDeserializer<ListGroupsResponse, ListGroupsResponse> {
    public static final JsonDeserializer<ListGroupsResponse> INSTANCE = new ListGroupsResponseDeserializer();

    /* loaded from: classes11.dex */
    static class ListGroupsResponseFieldDeserializer implements JsonFieldDeserializer<ListGroupsResponse> {
        ListGroupsResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ListGroupsResponse) obj));
        }

        public <U extends ListGroupsResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser));
                return true;
            } else if (FeatureName.GROUPS.equals(str)) {
                u.setGroups(GroupListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"nextToken".equals(str)) {
                return false;
            } else {
                u.setNextToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private ListGroupsResponseDeserializer() {
        super(new ListGroupsResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public ListGroupsResponse mo3156createValue() {
        return new ListGroupsResponse();
    }
}
