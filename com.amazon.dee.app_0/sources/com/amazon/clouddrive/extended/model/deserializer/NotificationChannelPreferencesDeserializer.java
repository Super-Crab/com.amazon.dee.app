package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.NotificationChannelPreferences;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class NotificationChannelPreferencesDeserializer extends AbstractDeserializer<NotificationChannelPreferences, NotificationChannelPreferences> {
    public static NotificationChannelPreferencesDeserializer INSTANCE = new NotificationChannelPreferencesDeserializer();

    /* loaded from: classes11.dex */
    private static class NotificationChannelPreferencesFieldDeserializer implements JsonFieldDeserializer<NotificationChannelPreferences> {
        private NotificationChannelPreferencesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((NotificationChannelPreferences) obj));
        }

        public <U extends NotificationChannelPreferences> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("contentAdded".equals(str)) {
                u.setContentAdded(Boolean.valueOf(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser)));
                return true;
            } else if (!"memberJoined".equals(str)) {
                return false;
            } else {
                u.setMemberJoined(Boolean.valueOf(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser)));
                return true;
            }
        }
    }

    private NotificationChannelPreferencesDeserializer() {
        super(new NotificationChannelPreferencesFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public NotificationChannelPreferences mo3156createValue() {
        return new NotificationChannelPreferences();
    }
}
