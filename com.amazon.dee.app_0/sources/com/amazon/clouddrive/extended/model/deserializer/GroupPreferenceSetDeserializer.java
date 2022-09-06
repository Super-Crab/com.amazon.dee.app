package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.clouddrive.extended.model.GroupPreference;
import com.amazon.clouddrive.extended.model.GroupPreferenceSet;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupPreferenceSetDeserializer implements JsonDeserializer<GroupPreferenceSet> {
    public static final JsonDeserializer<GroupPreferenceSet> INSTANCE = new GroupPreferenceSetDeserializer();
    private static final ListDeserializer<GroupPreference> GROUP_PREFERENCE_LIST_DESERIALIZER = new ListDeserializer<>(GroupPreferenceDeserializer.INSTANCE);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupPreferenceSet mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupPreferenceSet.Builder builder = new GroupPreferenceSet.Builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("id".equals(currentName)) {
                            builder.withId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if (NetworkConstants.PREFERENCES_KEY.equals(currentName)) {
                            builder.withPreferences(GROUP_PREFERENCE_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                        } else if ("title".equals(currentName)) {
                            builder.withTitle(SimpleDeserializers.deserializeString(jsonParser));
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
