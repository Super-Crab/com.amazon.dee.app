package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Group;
import com.amazon.clouddrive.extended.model.ViewGroupResponse;
import com.amazon.clouddrive.extended.model.deserializer.GroupDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class ViewGroupResponseDeserializer extends AbstractDeserializer<Group, ViewGroupResponse> {
    public static final JsonDeserializer<ViewGroupResponse> INSTANCE = new ViewGroupResponseDeserializer();

    /* loaded from: classes11.dex */
    static class ViewGroupResponseFieldDeserializer extends GroupDeserializer.GroupFieldDeserializer {
        ViewGroupResponseFieldDeserializer() {
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

    public ViewGroupResponseDeserializer() {
        super(new ViewGroupResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public ViewGroupResponse mo3156createValue() {
        return new ViewGroupResponse();
    }
}
