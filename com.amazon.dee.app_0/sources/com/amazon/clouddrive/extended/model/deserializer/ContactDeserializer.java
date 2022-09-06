package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Contact;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ContactDeserializer implements JsonDeserializer<Contact> {
    public static final JsonDeserializer<Contact> INSTANCE = new ContactDeserializer();
    private final ContactFieldDeserializer fieldDeserializer = new ContactFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ContactFieldDeserializer implements JsonFieldDeserializer<Contact.ContactBuilder> {
        private ContactFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Contact.ContactBuilder) obj));
        }

        public <U extends Contact.ContactBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("id".equals(str)) {
                u.id(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME.equals(str)) {
                u.kind(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("name".equals(str)) {
                u.name(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("description".equals(str)) {
                u.description(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("coverPhoto".equals(str)) {
                u.coverPhoto(GroupCoverPhotoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"tags".equals(str)) {
                return false;
            } else {
                u.tags(new MapDeserializer($$Lambda$lXlJC41MFIklcFIwGYK4iV8JLtc.INSTANCE).mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    protected ContactDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Contact mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Contact.ContactBuilder builder = Contact.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.fieldDeserializer.handleField(jsonParser, currentName, (String) builder)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return builder.build();
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
