package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.Target;
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
public final class AutoValue_Target extends C$AutoValue_Target {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Target> {
        private volatile TypeAdapter<CatalogType> catalogType_adapter;
        private final Gson gson;
        private volatile TypeAdapter<IdentifierType> identifierType_adapter;
        private volatile TypeAdapter<LaunchConfig> launchConfig_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("catalogId", "catalogType", "launchConfig", "identifier", "identifierType");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Target.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Target mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            CatalogType catalogType = null;
            LaunchConfig launchConfig = null;
            String str2 = null;
            IdentifierType identifierType = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("catalogId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("catalogType").equals(nextName)) {
                        TypeAdapter<CatalogType> typeAdapter2 = this.catalogType_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(CatalogType.class);
                            this.catalogType_adapter = typeAdapter2;
                        }
                        catalogType = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("launchConfig").equals(nextName)) {
                        TypeAdapter<LaunchConfig> typeAdapter3 = this.launchConfig_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(LaunchConfig.class);
                            this.launchConfig_adapter = typeAdapter3;
                        }
                        launchConfig = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("identifier").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str2 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("identifierType").equals(nextName)) {
                        TypeAdapter<IdentifierType> typeAdapter5 = this.identifierType_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(IdentifierType.class);
                            this.identifierType_adapter = typeAdapter5;
                        }
                        identifierType = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Target(str, catalogType, launchConfig, str2, identifierType);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Target target) throws IOException {
            if (target == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("catalogId"));
            if (target.getCatalogId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, target.getCatalogId());
            }
            jsonWriter.name(this.realFieldNames.get("catalogType"));
            if (target.getCatalogType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<CatalogType> typeAdapter2 = this.catalogType_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(CatalogType.class);
                    this.catalogType_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, target.getCatalogType());
            }
            jsonWriter.name(this.realFieldNames.get("launchConfig"));
            if (target.getLaunchConfig() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<LaunchConfig> typeAdapter3 = this.launchConfig_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(LaunchConfig.class);
                    this.launchConfig_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, target.getLaunchConfig());
            }
            jsonWriter.name(this.realFieldNames.get("identifier"));
            if (target.getIdentifier() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, target.getIdentifier());
            }
            jsonWriter.name(this.realFieldNames.get("identifierType"));
            if (target.getIdentifierType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IdentifierType> typeAdapter5 = this.identifierType_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(IdentifierType.class);
                    this.identifierType_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, target.getIdentifierType());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Target(@Nullable final String str, final CatalogType catalogType, @Nullable final LaunchConfig launchConfig, final String str2, final IdentifierType identifierType) {
        new Target(str, catalogType, launchConfig, str2, identifierType) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_Target
            private final String catalogId;
            private final CatalogType catalogType;
            private final String identifier;
            private final IdentifierType identifierType;
            private final LaunchConfig launchConfig;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_Target$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends Target.Builder {
                private String catalogId;
                private CatalogType catalogType;
                private String identifier;
                private IdentifierType identifierType;
                private LaunchConfig launchConfig;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target.Builder
                public Target build() {
                    String str = "";
                    if (this.catalogType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " catalogType");
                    }
                    if (this.identifier == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " identifier");
                    }
                    if (this.identifierType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " identifierType");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Target(this.catalogId, this.catalogType, this.launchConfig, this.identifier, this.identifierType);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target.Builder
                public Target.Builder setCatalogId(@Nullable String str) {
                    this.catalogId = str;
                    return this;
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target.Builder
                public Target.Builder setCatalogType(CatalogType catalogType) {
                    if (catalogType != null) {
                        this.catalogType = catalogType;
                        return this;
                    }
                    throw new NullPointerException("Null catalogType");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target.Builder
                public Target.Builder setIdentifier(String str) {
                    if (str != null) {
                        this.identifier = str;
                        return this;
                    }
                    throw new NullPointerException("Null identifier");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target.Builder
                public Target.Builder setIdentifierType(IdentifierType identifierType) {
                    if (identifierType != null) {
                        this.identifierType = identifierType;
                        return this;
                    }
                    throw new NullPointerException("Null identifierType");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target.Builder
                public Target.Builder setLaunchConfig(@Nullable LaunchConfig launchConfig) {
                    this.launchConfig = launchConfig;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.catalogId = str;
                if (catalogType != null) {
                    this.catalogType = catalogType;
                    this.launchConfig = launchConfig;
                    if (str2 != null) {
                        this.identifier = str2;
                        if (identifierType != null) {
                            this.identifierType = identifierType;
                            return;
                        }
                        throw new NullPointerException("Null identifierType");
                    }
                    throw new NullPointerException("Null identifier");
                }
                throw new NullPointerException("Null catalogType");
            }

            public boolean equals(Object obj) {
                LaunchConfig launchConfig2;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Target)) {
                    return false;
                }
                Target target = (Target) obj;
                String str3 = this.catalogId;
                if (str3 != null ? str3.equals(target.getCatalogId()) : target.getCatalogId() == null) {
                    if (this.catalogType.equals(target.getCatalogType()) && ((launchConfig2 = this.launchConfig) != null ? launchConfig2.equals(target.getLaunchConfig()) : target.getLaunchConfig() == null) && this.identifier.equals(target.getIdentifier()) && this.identifierType.equals(target.getIdentifierType())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target
            @Nullable
            public String getCatalogId() {
                return this.catalogId;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target
            public CatalogType getCatalogType() {
                return this.catalogType;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target
            public String getIdentifier() {
                return this.identifier;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target
            public IdentifierType getIdentifierType() {
                return this.identifierType;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Target
            @Nullable
            public LaunchConfig getLaunchConfig() {
                return this.launchConfig;
            }

            public int hashCode() {
                String str3 = this.catalogId;
                int i = 0;
                int hashCode = ((((str3 == null ? 0 : str3.hashCode()) ^ 1000003) * 1000003) ^ this.catalogType.hashCode()) * 1000003;
                LaunchConfig launchConfig2 = this.launchConfig;
                if (launchConfig2 != null) {
                    i = launchConfig2.hashCode();
                }
                return ((((hashCode ^ i) * 1000003) ^ this.identifier.hashCode()) * 1000003) ^ this.identifierType.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Target{catalogId=");
                outline107.append(this.catalogId);
                outline107.append(", catalogType=");
                outline107.append(this.catalogType);
                outline107.append(", launchConfig=");
                outline107.append(this.launchConfig);
                outline107.append(", identifier=");
                outline107.append(this.identifier);
                outline107.append(", identifierType=");
                outline107.append(this.identifierType);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
