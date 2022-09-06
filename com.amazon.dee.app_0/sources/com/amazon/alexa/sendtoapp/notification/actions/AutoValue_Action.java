package com.amazon.alexa.sendtoapp.notification.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.notification.actions.Action;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class AutoValue_Action extends C$AutoValue_Action {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Action> {
        private final Gson gson;
        private volatile TypeAdapter<LaunchConfig> launchConfig_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("identifier", "catalogType", "identifierType", "catalogId", "launchConfig");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Action.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Action mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            LaunchConfig launchConfig = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -1618432855:
                            if (nextName.equals("identifier")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -1007177997:
                            if (nextName.equals("catalogType")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 482882435:
                            if (nextName.equals("identifierType")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 651346133:
                            if (nextName.equals("launchConfig")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1455933204:
                            if (nextName.equals("catalogId")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (c == 1) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (c == 2) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (c == 3) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (c != 4) {
                        jsonReader.skipValue();
                    } else {
                        TypeAdapter<LaunchConfig> typeAdapter5 = this.launchConfig_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(LaunchConfig.class);
                            this.launchConfig_adapter = typeAdapter5;
                        }
                        launchConfig = typeAdapter5.mo8353read(jsonReader);
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Action(str, str2, str3, str4, launchConfig);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Action action) throws IOException {
            if (action == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("identifier");
            if (action.identifier() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, action.identifier());
            }
            jsonWriter.name("catalogType");
            if (action.catalogType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, action.catalogType());
            }
            jsonWriter.name("identifierType");
            if (action.identifierType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, action.identifierType());
            }
            jsonWriter.name("catalogId");
            if (action.catalogId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, action.catalogId());
            }
            jsonWriter.name("launchConfig");
            if (action.launchConfig() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<LaunchConfig> typeAdapter5 = this.launchConfig_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(LaunchConfig.class);
                    this.launchConfig_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, action.launchConfig());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Action(final String str, final String str2, final String str3, @Nullable final String str4, @Nullable final LaunchConfig launchConfig) {
        new Action(str, str2, str3, str4, launchConfig) { // from class: com.amazon.alexa.sendtoapp.notification.actions.$AutoValue_Action
            private final String catalogId;
            private final String catalogType;
            private final String identifier;
            private final String identifierType;
            private final LaunchConfig launchConfig;

            /* renamed from: com.amazon.alexa.sendtoapp.notification.actions.$AutoValue_Action$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends Action.Builder {
                private String catalogId;
                private String catalogType;
                private String identifier;
                private String identifierType;
                private LaunchConfig launchConfig;

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Action.Builder
                public Action build() {
                    String str = "";
                    if (this.identifier == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " identifier");
                    }
                    if (this.catalogType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " catalogType");
                    }
                    if (this.identifierType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " identifierType");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Action(this.identifier, this.catalogType, this.identifierType, this.catalogId, this.launchConfig);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Action.Builder
                public Action.Builder setCatalogId(@Nullable String str) {
                    this.catalogId = str;
                    return this;
                }

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Action.Builder
                public Action.Builder setCatalogType(String str) {
                    if (str != null) {
                        this.catalogType = str;
                        return this;
                    }
                    throw new NullPointerException("Null catalogType");
                }

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Action.Builder
                public Action.Builder setIdentifier(String str) {
                    if (str != null) {
                        this.identifier = str;
                        return this;
                    }
                    throw new NullPointerException("Null identifier");
                }

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Action.Builder
                public Action.Builder setIdentifierType(String str) {
                    if (str != null) {
                        this.identifierType = str;
                        return this;
                    }
                    throw new NullPointerException("Null identifierType");
                }

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Action.Builder
                public Action.Builder setLaunchConfig(@Nullable LaunchConfig launchConfig) {
                    this.launchConfig = launchConfig;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.identifier = str;
                    if (str2 != null) {
                        this.catalogType = str2;
                        if (str3 != null) {
                            this.identifierType = str3;
                            this.catalogId = str4;
                            this.launchConfig = launchConfig;
                            return;
                        }
                        throw new NullPointerException("Null identifierType");
                    }
                    throw new NullPointerException("Null catalogType");
                }
                throw new NullPointerException("Null identifier");
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Action
            @Nullable
            @SerializedName("catalogId")
            public String catalogId() {
                return this.catalogId;
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Action
            @NonNull
            @SerializedName("catalogType")
            public String catalogType() {
                return this.catalogType;
            }

            public boolean equals(Object obj) {
                String str5;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Action)) {
                    return false;
                }
                Action action = (Action) obj;
                if (this.identifier.equals(action.identifier()) && this.catalogType.equals(action.catalogType()) && this.identifierType.equals(action.identifierType()) && ((str5 = this.catalogId) != null ? str5.equals(action.catalogId()) : action.catalogId() == null)) {
                    LaunchConfig launchConfig2 = this.launchConfig;
                    if (launchConfig2 == null) {
                        if (action.launchConfig() == null) {
                            return true;
                        }
                    } else if (launchConfig2.equals(action.launchConfig())) {
                        return true;
                    }
                }
                return false;
            }

            public int hashCode() {
                int hashCode = (((((this.identifier.hashCode() ^ 1000003) * 1000003) ^ this.catalogType.hashCode()) * 1000003) ^ this.identifierType.hashCode()) * 1000003;
                String str5 = this.catalogId;
                int i = 0;
                int hashCode2 = (hashCode ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
                LaunchConfig launchConfig2 = this.launchConfig;
                if (launchConfig2 != null) {
                    i = launchConfig2.hashCode();
                }
                return hashCode2 ^ i;
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Action
            @NonNull
            @SerializedName("identifier")
            public String identifier() {
                return this.identifier;
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Action
            @NonNull
            @SerializedName("identifierType")
            public String identifierType() {
                return this.identifierType;
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Action
            @Nullable
            @SerializedName("launchConfig")
            public LaunchConfig launchConfig() {
                return this.launchConfig;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Action{identifier=");
                outline107.append(this.identifier);
                outline107.append(", catalogType=");
                outline107.append(this.catalogType);
                outline107.append(", identifierType=");
                outline107.append(this.identifierType);
                outline107.append(", catalogId=");
                outline107.append(this.catalogId);
                outline107.append(", launchConfig=");
                outline107.append(this.launchConfig);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
