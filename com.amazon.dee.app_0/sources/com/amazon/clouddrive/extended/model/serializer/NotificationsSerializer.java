package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.Notifications;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NotificationsSerializer implements JsonSerializer<Notifications> {
    public static NotificationsSerializer INSTANCE = new NotificationsSerializer();
    private final NotificationsFieldSerializer fieldSerializer = new NotificationsFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NotificationsFieldSerializer implements JsonFieldSerializer<Notifications> {
        NotificationsFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((NotificationsFieldSerializer) ((Notifications) obj), jsonGenerator);
        }

        public <U extends Notifications> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.isMuted() != null) {
                jsonGenerator.writeFieldName("muted");
                SimpleSerializers.serializeBoolean(u.isMuted(), jsonGenerator);
            }
            if (u.getPush() != null) {
                jsonGenerator.writeFieldName("push");
                NotificationChannelPreferencesSerializer.INSTANCE.serialize(u.getPush(), jsonGenerator);
            }
            if (u.getEmail() != null) {
                jsonGenerator.writeFieldName("email");
                NotificationChannelPreferencesSerializer.INSTANCE.serialize(u.getEmail(), jsonGenerator);
            }
        }
    }

    private NotificationsSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(Notifications notifications, JsonGenerator jsonGenerator) throws IOException {
        if (notifications == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.fieldSerializer.serializeFields((NotificationsFieldSerializer) notifications, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
