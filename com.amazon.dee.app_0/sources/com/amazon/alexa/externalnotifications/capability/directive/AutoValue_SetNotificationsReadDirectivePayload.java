package com.amazon.alexa.externalnotifications.capability.directive;

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
public final class AutoValue_SetNotificationsReadDirectivePayload extends C$AutoValue_SetNotificationsReadDirectivePayload {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SetNotificationsReadDirectivePayload> {
        private final Gson gson;
        private volatile TypeAdapter<List<NotificationId>> list__notificationId_adapter;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("notificationIds");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SetNotificationsReadDirectivePayload.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SetNotificationsReadDirectivePayload mo8353read(JsonReader jsonReader) throws IOException {
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
            return new AutoValue_SetNotificationsReadDirectivePayload(list);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SetNotificationsReadDirectivePayload setNotificationsReadDirectivePayload) throws IOException {
            if (setNotificationsReadDirectivePayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("notificationIds"));
            if (setNotificationsReadDirectivePayload.getNotificationIds() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<NotificationId>> typeAdapter = this.list__notificationId_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, NotificationId.class));
                    this.list__notificationId_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, setNotificationsReadDirectivePayload.getNotificationIds());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SetNotificationsReadDirectivePayload(final List<NotificationId> list) {
        new SetNotificationsReadDirectivePayload(list) { // from class: com.amazon.alexa.externalnotifications.capability.directive.$AutoValue_SetNotificationsReadDirectivePayload
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
                if (!(obj instanceof SetNotificationsReadDirectivePayload)) {
                    return false;
                }
                return this.notificationIds.equals(((SetNotificationsReadDirectivePayload) obj).getNotificationIds());
            }

            @Override // com.amazon.alexa.externalnotifications.capability.directive.SetNotificationsReadDirectivePayload
            public List<NotificationId> getNotificationIds() {
                return this.notificationIds;
            }

            public int hashCode() {
                return this.notificationIds.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("SetNotificationsReadDirectivePayload{notificationIds="), this.notificationIds, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
