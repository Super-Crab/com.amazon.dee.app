package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Notifications;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class NotificationsDeserializer extends AbstractDeserializer<Notifications, Notifications> {
    public static NotificationsDeserializer INSTANCE = new NotificationsDeserializer();

    /* loaded from: classes11.dex */
    private static class NotificationsFieldDeserializer implements JsonFieldDeserializer<Notifications> {
        private NotificationsFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Notifications) obj));
        }

        public <U extends Notifications> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("muted".equals(str)) {
                u.setMuted(Boolean.valueOf(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser)));
                return true;
            } else if ("email".equals(str)) {
                u.setEmail(NotificationChannelPreferencesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"push".equals(str)) {
                return false;
            } else {
                u.setPush(NotificationChannelPreferencesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private NotificationsDeserializer() {
        super(new NotificationsFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public Notifications mo3156createValue() {
        return new Notifications();
    }
}
