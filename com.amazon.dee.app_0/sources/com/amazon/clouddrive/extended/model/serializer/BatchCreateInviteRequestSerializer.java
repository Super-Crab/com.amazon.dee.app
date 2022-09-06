package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BatchCreateInviteRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BatchCreateInviteRequestSerializer implements JsonSerializer<BatchCreateInviteRequest> {
    public static final JsonSerializer<BatchCreateInviteRequest> INSTANCE = new BatchCreateInviteRequestSerializer();
    private final BatchCreateInviteRequestFieldSerializer mFieldSerializer = new BatchCreateInviteRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BatchCreateInviteRequestFieldSerializer implements JsonFieldSerializer<BatchCreateInviteRequest> {
        BatchCreateInviteRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((BatchCreateInviteRequestFieldSerializer) ((BatchCreateInviteRequest) obj), jsonGenerator);
        }

        public <U extends BatchCreateInviteRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getGroupId() != null) {
                jsonGenerator.writeFieldName("groupId");
                SimpleSerializers.serializeString(u.getGroupId(), jsonGenerator);
            }
            if (u.getInvitees() != null) {
                jsonGenerator.writeFieldName("invites");
                InviteeListSerializer.INSTANCE.serialize(u.getInvitees(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(BatchCreateInviteRequest batchCreateInviteRequest, JsonGenerator jsonGenerator) throws IOException {
        if (batchCreateInviteRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((BatchCreateInviteRequestFieldSerializer) batchCreateInviteRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
