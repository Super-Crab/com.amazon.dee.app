package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
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
/* loaded from: classes.dex */
public final class AutoValue_ArtifactMetadata extends C$AutoValue_ArtifactMetadata {

    /* loaded from: classes.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ArtifactMetadata> {
        private final Gson gson;
        private volatile TypeAdapter<Map<String, List<String>>> map__string_list__string_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("artifactType", "artifactKey", "filters");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_ArtifactMetadata.class, outline128, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ArtifactMetadata mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            Map<String, List<String>> map = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("artifactType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("artifactKey").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("filters").equals(nextName)) {
                        TypeAdapter<Map<String, List<String>>> typeAdapter3 = this.map__string_list__string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(TypeToken.getParameterized(Map.class, String.class, TypeToken.getParameterized(List.class, String.class).getType()));
                            this.map__string_list__string_adapter = typeAdapter3;
                        }
                        map = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ArtifactMetadata(str, str2, map);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ArtifactMetadata artifactMetadata) throws IOException {
            if (artifactMetadata == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("artifactType"));
            if (artifactMetadata.getArtifactType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, artifactMetadata.getArtifactType());
            }
            jsonWriter.name(this.realFieldNames.get("artifactKey"));
            if (artifactMetadata.getArtifactKey() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, artifactMetadata.getArtifactKey());
            }
            jsonWriter.name(this.realFieldNames.get("filters"));
            if (artifactMetadata.getFilters() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Map<String, List<String>>> typeAdapter3 = this.map__string_list__string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(TypeToken.getParameterized(Map.class, String.class, TypeToken.getParameterized(List.class, String.class).getType()));
                    this.map__string_list__string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, artifactMetadata.getFilters());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ArtifactMetadata(final String str, final String str2, final Map<String, List<String>> map) {
        new ArtifactMetadata(str, str2, map) { // from class: com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.$AutoValue_ArtifactMetadata
            private final String artifactKey;
            private final String artifactType;
            private final Map<String, List<String>> filters;

            /* renamed from: com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.$AutoValue_ArtifactMetadata$Builder */
            /* loaded from: classes.dex */
            static final class Builder extends ArtifactMetadata.Builder {
                private String artifactKey;
                private String artifactType;
                private Map<String, List<String>> filters;

                @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata.Builder
                public ArtifactMetadata build() {
                    String str = "";
                    if (this.artifactType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " artifactType");
                    }
                    if (this.artifactKey == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " artifactKey");
                    }
                    if (this.filters == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " filters");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_ArtifactMetadata(this.artifactType, this.artifactKey, this.filters);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata.Builder
                public ArtifactMetadata.Builder setArtifactKey(String str) {
                    if (str != null) {
                        this.artifactKey = str;
                        return this;
                    }
                    throw new NullPointerException("Null artifactKey");
                }

                @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata.Builder
                public ArtifactMetadata.Builder setArtifactType(String str) {
                    if (str != null) {
                        this.artifactType = str;
                        return this;
                    }
                    throw new NullPointerException("Null artifactType");
                }

                @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata.Builder
                public ArtifactMetadata.Builder setFilters(Map<String, List<String>> map) {
                    if (map != null) {
                        this.filters = map;
                        return this;
                    }
                    throw new NullPointerException("Null filters");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.artifactType = str;
                    if (str2 != null) {
                        this.artifactKey = str2;
                        if (map != null) {
                            this.filters = map;
                            return;
                        }
                        throw new NullPointerException("Null filters");
                    }
                    throw new NullPointerException("Null artifactKey");
                }
                throw new NullPointerException("Null artifactType");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ArtifactMetadata)) {
                    return false;
                }
                ArtifactMetadata artifactMetadata = (ArtifactMetadata) obj;
                return this.artifactType.equals(artifactMetadata.getArtifactType()) && this.artifactKey.equals(artifactMetadata.getArtifactKey()) && this.filters.equals(artifactMetadata.getFilters());
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata
            public String getArtifactKey() {
                return this.artifactKey;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata
            public String getArtifactType() {
                return this.artifactType;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactMetadata
            public Map<String, List<String>> getFilters() {
                return this.filters;
            }

            public int hashCode() {
                return ((((this.artifactType.hashCode() ^ 1000003) * 1000003) ^ this.artifactKey.hashCode()) * 1000003) ^ this.filters.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactMetadata{artifactType=");
                outline107.append(this.artifactType);
                outline107.append(", artifactKey=");
                outline107.append(this.artifactKey);
                outline107.append(", filters=");
                outline107.append(this.filters);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
