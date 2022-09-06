package com.amazon.clouddrive.model.deserializer;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.model.ContentProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ContentPropertiesDeserializer implements JsonDeserializer<ContentProperties> {
    public static final JsonDeserializer<ContentProperties> INSTANCE = new ContentPropertiesDeserializer();
    private final ContentPropertiesFieldDeserializer mFieldHandler = new ContentPropertiesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ContentPropertiesFieldDeserializer implements JsonFieldDeserializer<ContentProperties> {
        ContentPropertiesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ContentProperties) obj));
        }

        public <U extends ContentProperties> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("contentType".equals(str)) {
                u.setContentType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("extension".equals(str)) {
                u.setExtension(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (SierraContentProviderContract.MD5_VALUE.equals(str)) {
                u.setMd5(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("document".equals(str)) {
                u.setDocument(DocumentPropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("video".equals(str)) {
                u.setVideo(VideoPropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("contentDate".equals(str)) {
                u.setContentDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("size".equals(str)) {
                u.setSize(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("version".equals(str)) {
                u.setVersion(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("image".equals(str)) {
                u.setImage(ImagePropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"contentSignatures".equals(str)) {
                return false;
            } else {
                u.setContentSignatures(ContentSignaturesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private ContentPropertiesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ContentProperties mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ContentProperties contentProperties = new ContentProperties();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) contentProperties)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return contentProperties;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
