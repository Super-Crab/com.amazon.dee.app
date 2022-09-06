package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupPreferenceSet;
import com.amazon.clouddrive.extended.model.GroupPrivacyPreferences;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupPrivacyPreferencesDeserializer implements JsonDeserializer<GroupPrivacyPreferences> {
    public static final JsonDeserializer<GroupPrivacyPreferences> INSTANCE = new GroupPrivacyPreferencesDeserializer();
    private static final ListDeserializer<GroupPreferenceSet> GROUP_PREFERENCE_SET_LIST_DESERIALIZER = new ListDeserializer<>(GroupPreferenceSetDeserializer.INSTANCE);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupPrivacyPreferences mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupPrivacyPreferences.GroupPrivacyPreferencesBuilder builder = GroupPrivacyPreferences.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("preferenceSets".equals(currentName)) {
                            builder.preferenceSets(GROUP_PREFERENCE_SET_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
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
