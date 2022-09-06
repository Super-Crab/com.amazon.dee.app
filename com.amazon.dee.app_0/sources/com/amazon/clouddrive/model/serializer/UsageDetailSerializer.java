package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.UsageDetail;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UsageDetailSerializer implements JsonSerializer<UsageDetail> {
    public static final JsonSerializer<UsageDetail> INSTANCE = new UsageDetailSerializer();
    private final UsageDetailFieldSerializer mFieldSerializer = new UsageDetailFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UsageDetailFieldSerializer implements JsonFieldSerializer<UsageDetail> {
        UsageDetailFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((UsageDetailFieldSerializer) ((UsageDetail) obj), jsonGenerator);
        }

        public <U extends UsageDetail> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("bytes");
            SimpleSerializers.serializePrimitiveLong(u.getBytes(), jsonGenerator);
            jsonGenerator.writeFieldName("count");
            SimpleSerializers.serializePrimitiveLong(u.getCount(), jsonGenerator);
        }
    }

    private UsageDetailSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(UsageDetail usageDetail, JsonGenerator jsonGenerator) throws IOException {
        if (usageDetail == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((UsageDetailFieldSerializer) usageDetail, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
