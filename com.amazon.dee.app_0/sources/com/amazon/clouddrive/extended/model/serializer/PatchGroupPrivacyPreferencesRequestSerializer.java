package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.GroupPreferenceSetUpdate;
import com.amazon.clouddrive.extended.model.PatchGroupPrivacyPreferencesRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.ListSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PatchGroupPrivacyPreferencesRequestSerializer implements JsonSerializer<PatchGroupPrivacyPreferencesRequest> {
    public static PatchGroupPrivacyPreferencesRequestSerializer INSTANCE = new PatchGroupPrivacyPreferencesRequestSerializer();
    public static final ListSerializer<GroupPreferenceSetUpdate> GROUP_PREFERENCE_SET_UPDATE_SERIALIZER_LIST_SERIALIZER = new ListSerializer<>(GroupPreferenceSetUpdateSerializer.INSTANCE);

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (patchGroupPrivacyPreferencesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (patchGroupPrivacyPreferencesRequest.getPreferenceSets() != null) {
            jsonGenerator.writeFieldName("preferenceSets");
            GROUP_PREFERENCE_SET_UPDATE_SERIALIZER_LIST_SERIALIZER.serialize(patchGroupPrivacyPreferencesRequest.getPreferenceSets(), jsonGenerator);
        }
        if (patchGroupPrivacyPreferencesRequest.getLang() != null) {
            jsonGenerator.writeFieldName("lang");
            SimpleSerializers.serializeString(patchGroupPrivacyPreferencesRequest.getLang(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
