package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.UploadFileRequest;
import com.amazon.clouddrive.model.serializer.EditableNodeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UploadRequestSerializer implements JsonSerializer<UploadFileRequest> {
    public static final JsonSerializer<UploadFileRequest> INSTANCE = new UploadRequestSerializer();
    private final PostNodeRequestFieldSerializer mFieldSerializer = new PostNodeRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class PostNodeRequestFieldSerializer extends EditableNodeSerializer.EditableNodeFieldSerializer {
        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PostNodeRequestFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((PostNodeRequestFieldSerializer) u, jsonGenerator);
            UploadFileRequest uploadFileRequest = (UploadFileRequest) u;
            if (uploadFileRequest.getMD5() != null) {
                jsonGenerator.writeFieldName(SierraContentProviderContract.MD5_VALUE);
                SimpleSerializers.serializeString(uploadFileRequest.getMD5(), jsonGenerator);
            }
            if (uploadFileRequest.getContentDate() != null) {
                jsonGenerator.writeFieldName("contentDate");
                SimpleSerializers.serializeString(uploadFileRequest.getContentDate(), jsonGenerator);
            }
            jsonGenerator.writeFieldName("size");
            SimpleSerializers.serializePrimitiveLong(uploadFileRequest.getContentLength(), jsonGenerator);
            jsonGenerator.writeFieldName("conflictResolution");
            SimpleSerializers.serializeString(uploadFileRequest.getConflictResolution(), jsonGenerator);
        }
    }

    private UploadRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(UploadFileRequest uploadFileRequest, JsonGenerator jsonGenerator) throws IOException {
        if (uploadFileRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PostNodeRequestFieldSerializer) uploadFileRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
