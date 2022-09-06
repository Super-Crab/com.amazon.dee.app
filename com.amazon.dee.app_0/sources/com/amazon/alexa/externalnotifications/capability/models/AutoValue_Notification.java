package com.amazon.alexa.externalnotifications.capability.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class AutoValue_Notification extends C$AutoValue_Notification {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Notification> {
        private volatile TypeAdapter<Application> application_adapter;
        private volatile TypeAdapter<Content> content_adapter;
        private volatile TypeAdapter<Date> date_adapter;
        private final Gson gson;
        private volatile TypeAdapter<NotificationId> notificationId_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<Source> source_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129(Constants.BUNDLE_KEY_NOTIFICATION_ID, "application", "source", "content", "timestamp");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Notification.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Notification mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            NotificationId notificationId = null;
            Application application = null;
            Source source = null;
            Content content = null;
            Date date = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get(Constants.BUNDLE_KEY_NOTIFICATION_ID).equals(nextName)) {
                        TypeAdapter<NotificationId> typeAdapter = this.notificationId_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(NotificationId.class);
                            this.notificationId_adapter = typeAdapter;
                        }
                        notificationId = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("application").equals(nextName)) {
                        TypeAdapter<Application> typeAdapter2 = this.application_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Application.class);
                            this.application_adapter = typeAdapter2;
                        }
                        application = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("source").equals(nextName)) {
                        TypeAdapter<Source> typeAdapter3 = this.source_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(Source.class);
                            this.source_adapter = typeAdapter3;
                        }
                        source = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("content").equals(nextName)) {
                        TypeAdapter<Content> typeAdapter4 = this.content_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(Content.class);
                            this.content_adapter = typeAdapter4;
                        }
                        content = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("timestamp").equals(nextName)) {
                        TypeAdapter<Date> typeAdapter5 = this.date_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(Date.class);
                            this.date_adapter = typeAdapter5;
                        }
                        date = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Notification(notificationId, application, source, content, date);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Notification notification) throws IOException {
            if (notification == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get(Constants.BUNDLE_KEY_NOTIFICATION_ID));
            if (notification.getNotificationId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<NotificationId> typeAdapter = this.notificationId_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(NotificationId.class);
                    this.notificationId_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, notification.getNotificationId());
            }
            jsonWriter.name(this.realFieldNames.get("application"));
            if (notification.getApplication() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Application> typeAdapter2 = this.application_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Application.class);
                    this.application_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, notification.getApplication());
            }
            jsonWriter.name(this.realFieldNames.get("source"));
            if (notification.getSource() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Source> typeAdapter3 = this.source_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(Source.class);
                    this.source_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, notification.getSource());
            }
            jsonWriter.name(this.realFieldNames.get("content"));
            if (notification.getContent() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Content> typeAdapter4 = this.content_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(Content.class);
                    this.content_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, notification.getContent());
            }
            jsonWriter.name(this.realFieldNames.get("timestamp"));
            if (notification.getTimestamp() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Date> typeAdapter5 = this.date_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(Date.class);
                    this.date_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, notification.getTimestamp());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Notification(final NotificationId notificationId, final Application application, @Nullable final Source source, final Content content, final Date date) {
        new Notification(notificationId, application, source, content, date) { // from class: com.amazon.alexa.externalnotifications.capability.models.$AutoValue_Notification
            private final Application application;
            private final Content content;
            private final NotificationId notificationId;
            private final Source source;
            private final Date timestamp;

            /* renamed from: com.amazon.alexa.externalnotifications.capability.models.$AutoValue_Notification$Builder */
            /* loaded from: classes7.dex */
            static final class Builder extends Notification.Builder {
                private Application application;
                private Content content;
                private NotificationId notificationId;
                private Source source;
                private Date timestamp;

                @Override // com.amazon.alexa.externalnotifications.capability.models.Notification.Builder
                public Notification build() {
                    String str = "";
                    if (this.notificationId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " notificationId");
                    }
                    if (this.application == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " application");
                    }
                    if (this.content == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " content");
                    }
                    if (this.timestamp == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " timestamp");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Notification(this.notificationId, this.application, this.source, this.content, this.timestamp);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.externalnotifications.capability.models.Notification.Builder
                public Notification.Builder setApplication(Application application) {
                    if (application != null) {
                        this.application = application;
                        return this;
                    }
                    throw new NullPointerException("Null application");
                }

                @Override // com.amazon.alexa.externalnotifications.capability.models.Notification.Builder
                public Notification.Builder setContent(Content content) {
                    if (content != null) {
                        this.content = content;
                        return this;
                    }
                    throw new NullPointerException("Null content");
                }

                @Override // com.amazon.alexa.externalnotifications.capability.models.Notification.Builder
                public Notification.Builder setNotificationId(NotificationId notificationId) {
                    if (notificationId != null) {
                        this.notificationId = notificationId;
                        return this;
                    }
                    throw new NullPointerException("Null notificationId");
                }

                @Override // com.amazon.alexa.externalnotifications.capability.models.Notification.Builder
                public Notification.Builder setSource(@Nullable Source source) {
                    this.source = source;
                    return this;
                }

                @Override // com.amazon.alexa.externalnotifications.capability.models.Notification.Builder
                public Notification.Builder setTimestamp(Date date) {
                    if (date != null) {
                        this.timestamp = date;
                        return this;
                    }
                    throw new NullPointerException("Null timestamp");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (notificationId != null) {
                    this.notificationId = notificationId;
                    if (application != null) {
                        this.application = application;
                        this.source = source;
                        if (content != null) {
                            this.content = content;
                            if (date != null) {
                                this.timestamp = date;
                                return;
                            }
                            throw new NullPointerException("Null timestamp");
                        }
                        throw new NullPointerException("Null content");
                    }
                    throw new NullPointerException("Null application");
                }
                throw new NullPointerException("Null notificationId");
            }

            public boolean equals(Object obj) {
                Source source2;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Notification)) {
                    return false;
                }
                Notification notification = (Notification) obj;
                return this.notificationId.equals(notification.getNotificationId()) && this.application.equals(notification.getApplication()) && ((source2 = this.source) != null ? source2.equals(notification.getSource()) : notification.getSource() == null) && this.content.equals(notification.getContent()) && this.timestamp.equals(notification.getTimestamp());
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Notification
            public Application getApplication() {
                return this.application;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Notification
            public Content getContent() {
                return this.content;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Notification
            public NotificationId getNotificationId() {
                return this.notificationId;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Notification
            @Nullable
            public Source getSource() {
                return this.source;
            }

            @Override // com.amazon.alexa.externalnotifications.capability.models.Notification
            public Date getTimestamp() {
                return this.timestamp;
            }

            public int hashCode() {
                int hashCode = (((this.notificationId.hashCode() ^ 1000003) * 1000003) ^ this.application.hashCode()) * 1000003;
                Source source2 = this.source;
                return ((((hashCode ^ (source2 == null ? 0 : source2.hashCode())) * 1000003) ^ this.content.hashCode()) * 1000003) ^ this.timestamp.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Notification{notificationId=");
                outline107.append(this.notificationId);
                outline107.append(", application=");
                outline107.append(this.application);
                outline107.append(", source=");
                outline107.append(this.source);
                outline107.append(", content=");
                outline107.append(this.content);
                outline107.append(", timestamp=");
                outline107.append(this.timestamp);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
