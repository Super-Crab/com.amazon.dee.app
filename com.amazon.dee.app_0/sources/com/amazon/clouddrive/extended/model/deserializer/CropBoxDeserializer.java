package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CropBox;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CropBoxDeserializer implements JsonDeserializer<CropBox> {
    public static final JsonDeserializer<CropBox> INSTANCE = new CropBoxDeserializer();
    private final CropBoxFieldDeserializer mFieldHandler = new CropBoxFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CropBoxFieldDeserializer implements JsonFieldDeserializer<CropBox> {
        CropBoxFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CropBox) obj));
        }

        public <U extends CropBox> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("offsetX".equals(str)) {
                u.setOffsetX(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("offsetY".equals(str)) {
                u.setOffsetY(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("cropWidth".equals(str)) {
                u.setCropWidth(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if (!"cropHeight".equals(str)) {
                return false;
            } else {
                u.setCropHeight(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            }
        }
    }

    private CropBoxDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CropBox mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CropBox cropBox = new CropBox();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) cropBox)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return cropBox;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
