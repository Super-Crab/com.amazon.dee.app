package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.clouddrive.extended.model.GroupPreferenceSetUpdate;
import com.amazon.clouddrive.extended.model.GroupPreferenceUpdate;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.ListSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GroupPreferenceSetUpdateSerializer implements JsonSerializer<GroupPreferenceSetUpdate> {
    public static final JsonSerializer<GroupPreferenceSetUpdate> INSTANCE = new GroupPreferenceSetUpdateSerializer();
    public static final ListSerializer<GroupPreferenceUpdate> GROUP_PREFERENCE_UPDATE_SERIALIZER_LIST_SERIALIZER = new ListSerializer<>(GroupPreferenceUpdateSerializer.INSTANCE);

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GroupPreferenceSetUpdate groupPreferenceSetUpdate, JsonGenerator jsonGenerator) throws IOException {
        if (groupPreferenceSetUpdate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (groupPreferenceSetUpdate.getId() != null) {
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(groupPreferenceSetUpdate.getId(), jsonGenerator);
        }
        if (groupPreferenceSetUpdate.getPreferences() != null) {
            jsonGenerator.writeFieldName(NetworkConstants.PREFERENCES_KEY);
            GROUP_PREFERENCE_UPDATE_SERIALIZER_LIST_SERIALIZER.serialize(groupPreferenceSetUpdate.getPreferences(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
