package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon;
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
public final class AutoValue_Icon extends C$AutoValue_Icon {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Icon> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;
        private volatile TypeAdapter<TintConfig> tintConfig_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("tintConfig", "url");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Icon.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Icon mo8353read(JsonReader jsonReader) throws IOException {
            TintConfig tintConfig = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("tintConfig").equals(nextName)) {
                        TypeAdapter<TintConfig> typeAdapter = this.tintConfig_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TintConfig.class);
                            this.tintConfig_adapter = typeAdapter;
                        }
                        tintConfig = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("url").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Icon(tintConfig, str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Icon icon) throws IOException {
            if (icon == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("tintConfig"));
            if (icon.getTintConfig() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<TintConfig> typeAdapter = this.tintConfig_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TintConfig.class);
                    this.tintConfig_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, icon.getTintConfig());
            }
            jsonWriter.name(this.realFieldNames.get("url"));
            if (icon.getUrl() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, icon.getUrl());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Icon(final TintConfig tintConfig, final String str) {
        new Icon(tintConfig, str) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_Icon
            private final TintConfig tintConfig;
            private final String url;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_Icon$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends Icon.Builder {
                private TintConfig tintConfig;
                private String url;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon.Builder
                public Icon build() {
                    String str = "";
                    if (this.tintConfig == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " tintConfig");
                    }
                    if (this.url == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " url");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Icon(this.tintConfig, this.url);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon.Builder
                public Icon.Builder setTintConfig(TintConfig tintConfig) {
                    if (tintConfig != null) {
                        this.tintConfig = tintConfig;
                        return this;
                    }
                    throw new NullPointerException("Null tintConfig");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon.Builder
                public Icon.Builder setUrl(String str) {
                    if (str != null) {
                        this.url = str;
                        return this;
                    }
                    throw new NullPointerException("Null url");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (tintConfig != null) {
                    this.tintConfig = tintConfig;
                    if (str != null) {
                        this.url = str;
                        return;
                    }
                    throw new NullPointerException("Null url");
                }
                throw new NullPointerException("Null tintConfig");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Icon)) {
                    return false;
                }
                Icon icon = (Icon) obj;
                return this.tintConfig.equals(icon.getTintConfig()) && this.url.equals(icon.getUrl());
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon
            public TintConfig getTintConfig() {
                return this.tintConfig;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon
            public String getUrl() {
                return this.url;
            }

            public int hashCode() {
                return ((this.tintConfig.hashCode() ^ 1000003) * 1000003) ^ this.url.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Icon{tintConfig=");
                outline107.append(this.tintConfig);
                outline107.append(", url=");
                return GeneratedOutlineSupport1.outline91(outline107, this.url, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
