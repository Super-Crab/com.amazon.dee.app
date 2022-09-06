package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListMembersResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class ListMembersResponseDeserializer extends AbstractDeserializer<ListMembersResponse, ListMembersResponse> {
    public static final JsonDeserializer<ListMembersResponse> INSTANCE = new ListMembersResponseDeserializer();

    /* loaded from: classes11.dex */
    static class ListMembersResponseFieldDeserializer implements JsonFieldDeserializer<ListMembersResponse> {
        ListMembersResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ListMembersResponse) obj));
        }

        public <U extends ListMembersResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser));
                return true;
            } else if ("members".equals(str)) {
                u.setMembers(FeaturedMemberListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"nextToken".equals(str)) {
                return false;
            } else {
                u.setNextToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private ListMembersResponseDeserializer() {
        super(new ListMembersResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public ListMembersResponse mo3156createValue() {
        return new ListMembersResponse();
    }
}
