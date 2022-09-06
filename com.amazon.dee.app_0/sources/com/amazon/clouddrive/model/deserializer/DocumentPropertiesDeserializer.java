package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.DocumentProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class DocumentPropertiesDeserializer implements JsonDeserializer<DocumentProperties> {
    public static final JsonDeserializer<DocumentProperties> INSTANCE = new DocumentPropertiesDeserializer();
    private final DocumentPropertiesFieldDeserializer mFieldHandler = new DocumentPropertiesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class DocumentPropertiesFieldDeserializer implements JsonFieldDeserializer<DocumentProperties> {
        DocumentPropertiesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((DocumentProperties) obj));
        }

        public <U extends DocumentProperties> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("title".equals(str)) {
                u.setTitle(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("authors".equals(str)) {
                u.setAuthors(AuthorListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"documentVersion".equals(str)) {
                return false;
            } else {
                u.setDocumentVersion(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private DocumentPropertiesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public DocumentProperties mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            DocumentProperties documentProperties = new DocumentProperties();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) documentProperties)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return documentProperties;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
