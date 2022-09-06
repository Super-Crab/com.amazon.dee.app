package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class AutoValue_CustomData extends C$AutoValue_CustomData {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<CustomData> {
        private volatile TypeAdapter<Actions> actions_adapter;
        private final Gson gson;
        private volatile TypeAdapter<Icon> icon_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("version", "token", "icon", "title", "subtitle");
            outline129.add(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS);
            outline129.add("metricId");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_CustomData.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public CustomData mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            Icon icon = null;
            String str3 = null;
            String str4 = null;
            Actions actions = null;
            String str5 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("version").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("token").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("icon").equals(nextName)) {
                        TypeAdapter<Icon> typeAdapter3 = this.icon_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(Icon.class);
                            this.icon_adapter = typeAdapter3;
                        }
                        icon = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("title").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str3 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("subtitle").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str4 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS).equals(nextName)) {
                        TypeAdapter<Actions> typeAdapter6 = this.actions_adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(Actions.class);
                            this.actions_adapter = typeAdapter6;
                        }
                        actions = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("metricId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter7 = this.string_adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter7;
                        }
                        str5 = typeAdapter7.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_CustomData(str, str2, icon, str3, str4, actions, str5);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, CustomData customData) throws IOException {
            if (customData == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("version"));
            if (customData.getVersion() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, customData.getVersion());
            }
            jsonWriter.name(this.realFieldNames.get("token"));
            if (customData.getToken() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, customData.getToken());
            }
            jsonWriter.name(this.realFieldNames.get("icon"));
            if (customData.getIcon() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Icon> typeAdapter3 = this.icon_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(Icon.class);
                    this.icon_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, customData.getIcon());
            }
            jsonWriter.name(this.realFieldNames.get("title"));
            if (customData.getTitle() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, customData.getTitle());
            }
            jsonWriter.name(this.realFieldNames.get("subtitle"));
            if (customData.getSubtitle() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, customData.getSubtitle());
            }
            jsonWriter.name(this.realFieldNames.get(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS));
            if (customData.getActions() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Actions> typeAdapter6 = this.actions_adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(Actions.class);
                    this.actions_adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, customData.getActions());
            }
            jsonWriter.name(this.realFieldNames.get("metricId"));
            if (customData.getMetricId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter7 = this.string_adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, customData.getMetricId());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_CustomData(final String str, final String str2, final Icon icon, final String str3, final String str4, final Actions actions, final String str5) {
        new CustomData(str, str2, icon, str3, str4, actions, str5) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_CustomData
            private final Actions actions;
            private final Icon icon;
            private final String metricId;
            private final String subtitle;
            private final String title;
            private final String token;
            private final String version;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_CustomData$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends CustomData.Builder {
                private Actions actions;
                private Icon icon;
                private String metricId;
                private String subtitle;
                private String title;
                private String token;
                private String version;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData build() {
                    String str = "";
                    if (this.version == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " version");
                    }
                    if (this.token == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " token");
                    }
                    if (this.icon == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " icon");
                    }
                    if (this.title == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " title");
                    }
                    if (this.subtitle == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " subtitle");
                    }
                    if (this.actions == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " actions");
                    }
                    if (this.metricId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " metricId");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_CustomData(this.version, this.token, this.icon, this.title, this.subtitle, this.actions, this.metricId);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setActions(Actions actions) {
                    if (actions != null) {
                        this.actions = actions;
                        return this;
                    }
                    throw new NullPointerException("Null actions");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setIcon(Icon icon) {
                    if (icon != null) {
                        this.icon = icon;
                        return this;
                    }
                    throw new NullPointerException("Null icon");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setMetricId(String str) {
                    if (str != null) {
                        this.metricId = str;
                        return this;
                    }
                    throw new NullPointerException("Null metricId");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setSubtitle(String str) {
                    if (str != null) {
                        this.subtitle = str;
                        return this;
                    }
                    throw new NullPointerException("Null subtitle");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setTitle(String str) {
                    if (str != null) {
                        this.title = str;
                        return this;
                    }
                    throw new NullPointerException("Null title");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setToken(String str) {
                    if (str != null) {
                        this.token = str;
                        return this;
                    }
                    throw new NullPointerException("Null token");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData.Builder
                public CustomData.Builder setVersion(String str) {
                    if (str != null) {
                        this.version = str;
                        return this;
                    }
                    throw new NullPointerException("Null version");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.version = str;
                    if (str2 != null) {
                        this.token = str2;
                        if (icon != null) {
                            this.icon = icon;
                            if (str3 != null) {
                                this.title = str3;
                                if (str4 != null) {
                                    this.subtitle = str4;
                                    if (actions != null) {
                                        this.actions = actions;
                                        if (str5 != null) {
                                            this.metricId = str5;
                                            return;
                                        }
                                        throw new NullPointerException("Null metricId");
                                    }
                                    throw new NullPointerException("Null actions");
                                }
                                throw new NullPointerException("Null subtitle");
                            }
                            throw new NullPointerException("Null title");
                        }
                        throw new NullPointerException("Null icon");
                    }
                    throw new NullPointerException("Null token");
                }
                throw new NullPointerException("Null version");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof CustomData)) {
                    return false;
                }
                CustomData customData = (CustomData) obj;
                return this.version.equals(customData.getVersion()) && this.token.equals(customData.getToken()) && this.icon.equals(customData.getIcon()) && this.title.equals(customData.getTitle()) && this.subtitle.equals(customData.getSubtitle()) && this.actions.equals(customData.getActions()) && this.metricId.equals(customData.getMetricId());
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public Actions getActions() {
                return this.actions;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public Icon getIcon() {
                return this.icon;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public String getMetricId() {
                return this.metricId;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public String getSubtitle() {
                return this.subtitle;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public String getTitle() {
                return this.title;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public String getToken() {
                return this.token;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData
            public String getVersion() {
                return this.version;
            }

            public int hashCode() {
                return ((((((((((((this.version.hashCode() ^ 1000003) * 1000003) ^ this.token.hashCode()) * 1000003) ^ this.icon.hashCode()) * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.subtitle.hashCode()) * 1000003) ^ this.actions.hashCode()) * 1000003) ^ this.metricId.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CustomData{version=");
                outline107.append(this.version);
                outline107.append(", token=");
                outline107.append(this.token);
                outline107.append(", icon=");
                outline107.append(this.icon);
                outline107.append(", title=");
                outline107.append(this.title);
                outline107.append(", subtitle=");
                outline107.append(this.subtitle);
                outline107.append(", actions=");
                outline107.append(this.actions);
                outline107.append(", metricId=");
                return GeneratedOutlineSupport1.outline91(outline107, this.metricId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
