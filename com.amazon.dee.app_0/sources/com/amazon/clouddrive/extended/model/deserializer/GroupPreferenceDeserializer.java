package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupPreference;
import com.amazon.clouddrive.extended.model.GroupPreferenceValue;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupPreferenceDeserializer implements JsonDeserializer<GroupPreference> {
    public static final JsonDeserializer<GroupPreference> INSTANCE = new GroupPreferenceDeserializer();
    private static final ListDeserializer<GroupPreferenceValue> AVAILABLE_VALUES_DESERIALIZER = new ListDeserializer<>(GroupPreferenceValueDeserializer.INSTANCE);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupPreference mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupPreference.GroupPreferenceBuilder builder = GroupPreference.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("id".equals(currentName)) {
                            builder.id(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("title".equals(currentName)) {
                            builder.title(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("value".equals(currentName)) {
                            builder.value(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("valueKind".equals(currentName)) {
                            builder.valueKind(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("availableValues".equals(currentName)) {
                            builder.availableValues(AVAILABLE_VALUES_DESERIALIZER.mo3229deserialize(jsonParser));
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
