package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetGroupResponse;
import com.amazon.clouddrive.extended.model.Group;
import com.amazon.clouddrive.extended.model.deserializer.GroupDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetGroupResponseDeserializer extends AbstractDeserializer<Group, GetGroupResponse> {
    public static final JsonDeserializer<GetGroupResponse> INSTANCE = new GetGroupResponseDeserializer();

    /* loaded from: classes11.dex */
    static class GetGroupResponseFieldDeserializer extends GroupDeserializer.GroupFieldDeserializer {
        GetGroupResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.GroupDeserializer.GroupFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Group) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.GroupDeserializer.GroupFieldDeserializer
        public <U extends Group> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            return super.handleField(jsonParser, str, (String) u);
        }
    }

    public GetGroupResponseDeserializer() {
        super(new GetGroupResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetGroupResponse mo3156createValue() {
        return new GetGroupResponse();
    }
}
