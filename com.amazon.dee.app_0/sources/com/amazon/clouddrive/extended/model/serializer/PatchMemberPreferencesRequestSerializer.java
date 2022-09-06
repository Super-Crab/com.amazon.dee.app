package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.GroupPreferenceSetUpdate;
import com.amazon.clouddrive.extended.model.PatchMemberPreferencesRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.ListSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PatchMemberPreferencesRequestSerializer implements JsonSerializer<PatchMemberPreferencesRequest> {
    public static PatchMemberPreferencesRequestSerializer INSTANCE = new PatchMemberPreferencesRequestSerializer();
    public static final ListSerializer<GroupPreferenceSetUpdate> GROUP_PREFERENCE_SET_UPDATE_SERIALIZER_LIST_SERIALIZER = new ListSerializer<>(GroupPreferenceSetUpdateSerializer.INSTANCE);

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(PatchMemberPreferencesRequest patchMemberPreferencesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (patchMemberPreferencesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (patchMemberPreferencesRequest.getPreferenceSets() != null) {
            jsonGenerator.writeFieldName("preferenceSets");
            GROUP_PREFERENCE_SET_UPDATE_SERIALIZER_LIST_SERIALIZER.serialize(patchMemberPreferencesRequest.getPreferenceSets(), jsonGenerator);
        }
        if (patchMemberPreferencesRequest.getLang() != null) {
            jsonGenerator.writeFieldName("lang");
            SimpleSerializers.serializeString(patchMemberPreferencesRequest.getLang(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
