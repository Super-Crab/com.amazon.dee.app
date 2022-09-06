package com.amazon.alexa.externalnotifications.capability.events;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
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
public final class AutoValue_NotificationsReadEventPayload extends C$AutoValue_NotificationsReadEventPayload {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<NotificationsReadEventPayload> {
        private final Gson gson;
        private volatile TypeAdapter<List<NotificationId>> list__notificationId_adapter;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("notificationIds");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_NotificationsReadEventPayload.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public NotificationsReadEventPayload mo8353read(JsonReader jsonReader) throws IOException {
            List<NotificationId> list = null;
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
                    if (this.realFieldNames.get("notificationIds").equals(nextName)) {
                        TypeAdapter<List<NotificationId>> typeAdapter = this.list__notificationId_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, NotificationId.class));
                            this.list__notificationId_adapter = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_NotificationsReadEventPayload(list);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, NotificationsReadEventPayload notificationsReadEventPayload) throws IOException {
            if (notificationsReadEventPayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("notificationIds"));
            if (notificationsReadEventPayload.getNotificationIds() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<NotificationId>> typeAdapter = this.list__notificationId_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, NotificationId.class));
                    this.list__notificationId_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, notificationsReadEventPayload.getNotificationIds());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_NotificationsReadEventPayload(final List<NotificationId> list) {
        new NotificationsReadEventPayload(list) { // from class: com.amazon.alexa.externalnotifications.capability.events.$AutoValue_NotificationsReadEventPayload
            private final List<NotificationId> notificationIds;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (list != null) {
                    this.notificationIds = list;
                    return;
                }
                throw new NullPointerException("Null notificationIds");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof NotificationsReadEventPayload)) {
                    return false;
                }
                return this.notificationIds.equals(((NotificationsReadEventPayload) obj).getNotificationIds());
            }

            @Override // com.amazon.alexa.externalnotifications.capability.events.NotificationsReadEventPayload
            public List<NotificationId> getNotificationIds() {
                return this.notificationIds;
            }

            public int hashCode() {
                return this.notificationIds.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("NotificationsReadEventPayload{notificationIds="), this.notificationIds, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
