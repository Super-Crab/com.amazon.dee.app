package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.NotificationChannelPreferences;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NotificationChannelPreferencesSerializer implements JsonSerializer<NotificationChannelPreferences> {
    public static NotificationChannelPreferencesSerializer INSTANCE = new NotificationChannelPreferencesSerializer();
    private final NotificationChannelPreferencesFieldSerializer fieldSerializer = new NotificationChannelPreferencesFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NotificationChannelPreferencesFieldSerializer implements JsonFieldSerializer<NotificationChannelPreferences> {
        NotificationChannelPreferencesFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((NotificationChannelPreferencesFieldSerializer) ((NotificationChannelPreferences) obj), jsonGenerator);
        }

        public <U extends NotificationChannelPreferences> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.isContentAdded() != null) {
                jsonGenerator.writeFieldName("contentAdded");
                SimpleSerializers.serializeBoolean(u.isContentAdded(), jsonGenerator);
            }
            if (u.isMemberJoined() != null) {
                jsonGenerator.writeFieldName("memberJoined");
                SimpleSerializers.serializeBoolean(u.isMemberJoined(), jsonGenerator);
            }
        }
    }

    private NotificationChannelPreferencesSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(NotificationChannelPreferences notificationChannelPreferences, JsonGenerator jsonGenerator) throws IOException {
        if (notificationChannelPreferences == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.fieldSerializer.serializeFields((NotificationChannelPreferencesFieldSerializer) notificationChannelPreferences, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
