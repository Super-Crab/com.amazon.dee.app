package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.model.OverwriteFileRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class OverwriteRequestSerializer implements JsonSerializer<OverwriteFileRequest> {
    public static final JsonSerializer<OverwriteFileRequest> INSTANCE = new OverwriteRequestSerializer();
    private final PostNodeRequestFieldSerializer mFieldSerializer = new PostNodeRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class PostNodeRequestFieldSerializer implements JsonFieldSerializer<OverwriteFileRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PostNodeRequestFieldSerializer) ((OverwriteFileRequest) obj), jsonGenerator);
        }

        public <U extends OverwriteFileRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            String md5 = u.getMD5();
            if (md5 != null) {
                jsonGenerator.writeFieldName(SierraContentProviderContract.MD5_VALUE);
                SimpleSerializers.serializeString(md5, jsonGenerator);
            }
            jsonGenerator.writeFieldName("size");
            SimpleSerializers.serializePrimitiveLong(u.getContentLength(), jsonGenerator);
        }
    }

    private OverwriteRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(OverwriteFileRequest overwriteFileRequest, JsonGenerator jsonGenerator) throws IOException {
        if (overwriteFileRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PostNodeRequestFieldSerializer) overwriteFileRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
