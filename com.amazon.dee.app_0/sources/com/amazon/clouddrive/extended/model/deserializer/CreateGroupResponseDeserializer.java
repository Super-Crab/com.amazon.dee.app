package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CreateGroupResponse;
import com.amazon.clouddrive.extended.model.Group;
import com.amazon.clouddrive.extended.model.deserializer.GroupDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class CreateGroupResponseDeserializer extends AbstractDeserializer<Group, CreateGroupResponse> {
    public static final JsonDeserializer<CreateGroupResponse> INSTANCE = new CreateGroupResponseDeserializer();

    /* loaded from: classes11.dex */
    static class CreateGroupResponseFieldDeserializer extends GroupDeserializer.GroupFieldDeserializer {
        CreateGroupResponseFieldDeserializer() {
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

    public CreateGroupResponseDeserializer() {
        super(new CreateGroupResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public CreateGroupResponse mo3156createValue() {
        return new CreateGroupResponse();
    }
}
