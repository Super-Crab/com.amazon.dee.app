package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.DocumentProperties;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class DocumentPropertiesSerializer implements JsonSerializer<DocumentProperties> {
    public static final JsonSerializer<DocumentProperties> INSTANCE = new DocumentPropertiesSerializer();
    private final DocumentPropertiesFieldSerializer mFieldSerializer = new DocumentPropertiesFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class DocumentPropertiesFieldSerializer implements JsonFieldSerializer<DocumentProperties> {
        DocumentPropertiesFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((DocumentPropertiesFieldSerializer) ((DocumentProperties) obj), jsonGenerator);
        }

        public <U extends DocumentProperties> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("title");
            SimpleSerializers.serializeString(u.getTitle(), jsonGenerator);
            jsonGenerator.writeFieldName("authors");
            AuthorListSerializer.INSTANCE.serialize(u.getAuthors(), jsonGenerator);
            jsonGenerator.writeFieldName("documentVersion");
            SimpleSerializers.serializeString(u.getDocumentVersion(), jsonGenerator);
        }
    }

    private DocumentPropertiesSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(DocumentProperties documentProperties, JsonGenerator jsonGenerator) throws IOException {
        if (documentProperties == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((DocumentPropertiesFieldSerializer) documentProperties, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
