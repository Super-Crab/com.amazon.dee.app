package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.model.ContentProperties;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ContentPropertiesSerializer implements JsonSerializer<ContentProperties> {
    public static final JsonSerializer<ContentProperties> INSTANCE = new ContentPropertiesSerializer();
    private final ContentPropertiesFieldSerializer mFieldSerializer = new ContentPropertiesFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ContentPropertiesFieldSerializer implements JsonFieldSerializer<ContentProperties> {
        ContentPropertiesFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ContentPropertiesFieldSerializer) ((ContentProperties) obj), jsonGenerator);
        }

        public <U extends ContentProperties> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("contentType");
            SimpleSerializers.serializeString(u.getContentType(), jsonGenerator);
            jsonGenerator.writeFieldName("extension");
            SimpleSerializers.serializeString(u.getExtension(), jsonGenerator);
            jsonGenerator.writeFieldName(SierraContentProviderContract.MD5_VALUE);
            SimpleSerializers.serializeString(u.getMd5(), jsonGenerator);
            jsonGenerator.writeFieldName("document");
            DocumentPropertiesSerializer.INSTANCE.serialize(u.getDocument(), jsonGenerator);
            jsonGenerator.writeFieldName("video");
            VideoPropertiesSerializer.INSTANCE.serialize(u.getVideo(), jsonGenerator);
            jsonGenerator.writeFieldName("contentDate");
            SimpleSerializers.serializeString(u.getContentDate(), jsonGenerator);
            jsonGenerator.writeFieldName("size");
            SimpleSerializers.serializeLong(u.getSize(), jsonGenerator);
            jsonGenerator.writeFieldName("version");
            SimpleSerializers.serializeLong(u.getVersion(), jsonGenerator);
            jsonGenerator.writeFieldName("image");
            ImagePropertiesSerializer.INSTANCE.serialize(u.getImage(), jsonGenerator);
        }
    }

    private ContentPropertiesSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ContentProperties contentProperties, JsonGenerator jsonGenerator) throws IOException {
        if (contentProperties == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ContentPropertiesFieldSerializer) contentProperties, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
