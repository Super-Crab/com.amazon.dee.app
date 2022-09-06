package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupShareResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GroupShareResponseDeserializer extends AbstractDeserializer<GroupShareResponse, GroupShareResponse> {
    public static final JsonDeserializer<GroupShareResponse> INSTANCE = new GroupShareResponseDeserializer();

    /* loaded from: classes11.dex */
    static class GroupShareResponseFieldDeserializer implements JsonFieldDeserializer<GroupShareResponse> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GroupShareResponse) obj));
        }

        public <U extends GroupShareResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (str.equals("shareUrl")) {
                u.setShareUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (str.equals("shareToken")) {
                u.setShareToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!str.equals("inviteUrl")) {
                return false;
            } else {
                u.setInviteUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GroupShareResponseDeserializer() {
        super(new GroupShareResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GroupShareResponse mo3156createValue() {
        return new GroupShareResponse();
    }
}
