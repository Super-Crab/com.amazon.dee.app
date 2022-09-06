package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.CreateMemberRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateMemberRequestSerializer implements JsonSerializer<CreateMemberRequest> {
    public static CreateMemberRequestSerializer INSTANCE = new CreateMemberRequestSerializer();

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(CreateMemberRequest createMemberRequest, JsonGenerator jsonGenerator) throws IOException {
        if (createMemberRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (createMemberRequest.getGroupId() != null) {
            jsonGenerator.writeStringField("groupId", createMemberRequest.getGroupId());
        }
        if (createMemberRequest.getShareToken() != null) {
            jsonGenerator.writeStringField("shareToken", createMemberRequest.getShareToken());
        }
        if (createMemberRequest.getInviteToken() != null) {
            jsonGenerator.writeStringField("inviteToken", createMemberRequest.getInviteToken());
        }
        if (createMemberRequest.getInviteTag() != null) {
            jsonGenerator.writeStringField("inviteTag", createMemberRequest.getInviteTag());
        }
        jsonGenerator.writeEndObject();
    }
}
