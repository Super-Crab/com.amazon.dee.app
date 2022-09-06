package com.amazon.alexa.externalnotifications.capability.events;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class AutoValue_NotificationsReceivedEventPayload extends C$AutoValue_NotificationsReceivedEventPayload {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<NotificationsReceivedEventPayload> {
        private final Gson gson;
        private volatile TypeAdapter<List<Notification>> list__notification_adapter;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("notifications");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_NotificationsReceivedEventPayload.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public NotificationsReceivedEventPayload mo8353read(JsonReader jsonReader) throws IOException {
            List<Notification> list = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("notifications").equals(nextName)) {
                        TypeAdapter<List<Notification>> typeAdapter = this.list__notification_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, Notification.class));
                            this.list__notification_adapter = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_NotificationsReceivedEventPayload(list);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, NotificationsReceivedEventPayload notificationsReceivedEventPayload) throws IOException {
            if (notificationsReceivedEventPayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("notifications"));
            if (notificationsReceivedEventPayload.getNotifications() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Notification>> typeAdapter = this.list__notification_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, Notification.class));
                    this.list__notification_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, notificationsReceivedEventPayload.getNotifications());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_NotificationsReceivedEventPayload(final List<Notification> list) {
        new NotificationsReceivedEventPayload(list) { // from class: com.amazon.alexa.externalnotifications.capability.events.$AutoValue_NotificationsReceivedEventPayload
            private final List<Notification> notifications;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (list != null) {
                    this.notifications = list;
                    return;
                }
                throw new NullPointerException("Null notifications");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof NotificationsReceivedEventPayload)) {
                    return false;
                }
                return this.notifications.equals(((NotificationsReceivedEventPayload) obj).getNotifications());
            }

            @Override // com.amazon.alexa.externalnotifications.capability.events.NotificationsReceivedEventPayload
            public List<Notification> getNotifications() {
                return this.notifications;
            }

            public int hashCode() {
                return this.notifications.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("NotificationsReceivedEventPayload{notifications="), this.notifications, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
