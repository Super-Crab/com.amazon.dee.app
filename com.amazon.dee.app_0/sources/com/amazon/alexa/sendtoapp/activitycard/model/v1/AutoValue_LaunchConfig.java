package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig;
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
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class AutoValue_LaunchConfig extends C$AutoValue_LaunchConfig {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<LaunchConfig> {
        private volatile TypeAdapter<Boolean> boolean__adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("mustLaunchTargetInGivenApp", "isStoreLink");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_LaunchConfig.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public LaunchConfig mo8353read(JsonReader jsonReader) throws IOException {
            Boolean bool = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Boolean bool2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("mustLaunchTargetInGivenApp").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter = this.boolean__adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Boolean.class);
                            this.boolean__adapter = typeAdapter;
                        }
                        bool = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("isStoreLink").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter2 = this.boolean__adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Boolean.class);
                            this.boolean__adapter = typeAdapter2;
                        }
                        bool2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_LaunchConfig(bool, bool2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, LaunchConfig launchConfig) throws IOException {
            if (launchConfig == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("mustLaunchTargetInGivenApp"));
            if (launchConfig.mustLaunchTargetInGivenApp() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter = this.boolean__adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Boolean.class);
                    this.boolean__adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, launchConfig.mustLaunchTargetInGivenApp());
            }
            jsonWriter.name(this.realFieldNames.get("isStoreLink"));
            if (launchConfig.isStoreLink() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter2 = this.boolean__adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Boolean.class);
                    this.boolean__adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, launchConfig.isStoreLink());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_LaunchConfig(@Nullable final Boolean bool, @Nullable final Boolean bool2) {
        new LaunchConfig(bool, bool2) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_LaunchConfig
            private final Boolean isStoreLink;
            private final Boolean mustLaunchTargetInGivenApp;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_LaunchConfig$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends LaunchConfig.Builder {
                private Boolean isStoreLink;
                private Boolean mustLaunchTargetInGivenApp;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig.Builder
                public LaunchConfig build() {
                    return new AutoValue_LaunchConfig(this.mustLaunchTargetInGivenApp, this.isStoreLink);
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig.Builder
                public LaunchConfig.Builder isStoreLink(@Nullable Boolean bool) {
                    this.isStoreLink = bool;
                    return this;
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig.Builder
                public LaunchConfig.Builder mustLaunchTargetInGivenApp(@Nullable Boolean bool) {
                    this.mustLaunchTargetInGivenApp = bool;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.mustLaunchTargetInGivenApp = bool;
                this.isStoreLink = bool2;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof LaunchConfig)) {
                    return false;
                }
                LaunchConfig launchConfig = (LaunchConfig) obj;
                Boolean bool3 = this.mustLaunchTargetInGivenApp;
                if (bool3 != null ? bool3.equals(launchConfig.mustLaunchTargetInGivenApp()) : launchConfig.mustLaunchTargetInGivenApp() == null) {
                    Boolean bool4 = this.isStoreLink;
                    if (bool4 == null) {
                        if (launchConfig.isStoreLink() == null) {
                            return true;
                        }
                    } else if (bool4.equals(launchConfig.isStoreLink())) {
                        return true;
                    }
                }
                return false;
            }

            public int hashCode() {
                Boolean bool3 = this.mustLaunchTargetInGivenApp;
                int i = 0;
                int hashCode = ((bool3 == null ? 0 : bool3.hashCode()) ^ 1000003) * 1000003;
                Boolean bool4 = this.isStoreLink;
                if (bool4 != null) {
                    i = bool4.hashCode();
                }
                return hashCode ^ i;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig
            @Nullable
            public Boolean isStoreLink() {
                return this.isStoreLink;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.LaunchConfig
            @Nullable
            public Boolean mustLaunchTargetInGivenApp() {
                return this.mustLaunchTargetInGivenApp;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LaunchConfig{mustLaunchTargetInGivenApp=");
                outline107.append(this.mustLaunchTargetInGivenApp);
                outline107.append(", isStoreLink=");
                outline107.append(this.isStoreLink);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
