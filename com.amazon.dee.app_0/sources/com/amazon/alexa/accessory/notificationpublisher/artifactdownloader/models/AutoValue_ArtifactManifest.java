package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
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
/* loaded from: classes.dex */
public final class AutoValue_ArtifactManifest extends C$AutoValue_ArtifactManifest {

    /* loaded from: classes.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ArtifactManifest> {
        private volatile TypeAdapter<Checksum> checksum_adapter;
        private final Gson gson;
        private volatile TypeAdapter<Long> long__adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("artifactType", "artifactKey", "downloadUrl", "artifactTimeToLive", "artifactIdentifier");
            outline129.add("urlExpiryEpoch");
            outline129.add("artifactSize");
            outline129.add("checksum");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_ArtifactManifest.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ArtifactManifest mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            Long l = null;
            String str4 = null;
            Long l2 = null;
            Long l3 = null;
            Checksum checksum = null;
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
                    } else if (this.realFieldNames.get("downloadUrl").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("artifactTimeToLive").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter4 = this.long__adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(Long.class);
                            this.long__adapter = typeAdapter4;
                        }
                        l = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("artifactIdentifier").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str4 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("urlExpiryEpoch").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter6 = this.long__adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(Long.class);
                            this.long__adapter = typeAdapter6;
                        }
                        l2 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("artifactSize").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter7 = this.long__adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(Long.class);
                            this.long__adapter = typeAdapter7;
                        }
                        l3 = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("checksum").equals(nextName)) {
                        TypeAdapter<Checksum> typeAdapter8 = this.checksum_adapter;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.gson.getAdapter(Checksum.class);
                            this.checksum_adapter = typeAdapter8;
                        }
                        checksum = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ArtifactManifest(str, str2, str3, l, str4, l2, l3, checksum);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ArtifactManifest artifactManifest) throws IOException {
            if (artifactManifest == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("artifactType"));
            if (artifactManifest.getArtifactType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, artifactManifest.getArtifactType());
            }
            jsonWriter.name(this.realFieldNames.get("artifactKey"));
            if (artifactManifest.getArtifactKey() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, artifactManifest.getArtifactKey());
            }
            jsonWriter.name(this.realFieldNames.get("downloadUrl"));
            if (artifactManifest.getDownloadUrl() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, artifactManifest.getDownloadUrl());
            }
            jsonWriter.name(this.realFieldNames.get("artifactTimeToLive"));
            if (artifactManifest.getArtifactTimeToLive() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter4 = this.long__adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(Long.class);
                    this.long__adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, artifactManifest.getArtifactTimeToLive());
            }
            jsonWriter.name(this.realFieldNames.get("artifactIdentifier"));
            if (artifactManifest.getArtifactIdentifier() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, artifactManifest.getArtifactIdentifier());
            }
            jsonWriter.name(this.realFieldNames.get("urlExpiryEpoch"));
            if (artifactManifest.getUrlExpiryEpoch() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter6 = this.long__adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(Long.class);
                    this.long__adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, artifactManifest.getUrlExpiryEpoch());
            }
            jsonWriter.name(this.realFieldNames.get("artifactSize"));
            if (artifactManifest.getArtifactSize() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter7 = this.long__adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(Long.class);
                    this.long__adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, artifactManifest.getArtifactSize());
            }
            jsonWriter.name(this.realFieldNames.get("checksum"));
            if (artifactManifest.getChecksum() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Checksum> typeAdapter8 = this.checksum_adapter;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.gson.getAdapter(Checksum.class);
                    this.checksum_adapter = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, artifactManifest.getChecksum());
            }
            jsonWriter.endObject();
        }
    }

    AutoValue_ArtifactManifest(final String str, final String str2, final String str3, final Long l, final String str4, final Long l2, final Long l3, final Checksum checksum) {
        new ArtifactManifest(str, str2, str3, l, str4, l2, l3, checksum) { // from class: com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.$AutoValue_ArtifactManifest
            private final String artifactIdentifier;
            private final String artifactKey;
            private final Long artifactSize;
            private final Long artifactTimeToLive;
            private final String artifactType;
            private final Checksum checksum;
            private final String downloadUrl;
            private final Long urlExpiryEpoch;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.artifactType = str;
                    if (str2 != null) {
                        this.artifactKey = str2;
                        if (str3 != null) {
                            this.downloadUrl = str3;
                            if (l != null) {
                                this.artifactTimeToLive = l;
                                if (str4 != null) {
                                    this.artifactIdentifier = str4;
                                    if (l2 != null) {
                                        this.urlExpiryEpoch = l2;
                                        if (l3 != null) {
                                            this.artifactSize = l3;
                                            if (checksum != null) {
                                                this.checksum = checksum;
                                                return;
                                            }
                                            throw new NullPointerException("Null checksum");
                                        }
                                        throw new NullPointerException("Null artifactSize");
                                    }
                                    throw new NullPointerException("Null urlExpiryEpoch");
                                }
                                throw new NullPointerException("Null artifactIdentifier");
                            }
                            throw new NullPointerException("Null artifactTimeToLive");
                        }
                        throw new NullPointerException("Null downloadUrl");
                    }
                    throw new NullPointerException("Null artifactKey");
                }
                throw new NullPointerException("Null artifactType");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ArtifactManifest)) {
                    return false;
                }
                ArtifactManifest artifactManifest = (ArtifactManifest) obj;
                return this.artifactType.equals(artifactManifest.getArtifactType()) && this.artifactKey.equals(artifactManifest.getArtifactKey()) && this.downloadUrl.equals(artifactManifest.getDownloadUrl()) && this.artifactTimeToLive.equals(artifactManifest.getArtifactTimeToLive()) && this.artifactIdentifier.equals(artifactManifest.getArtifactIdentifier()) && this.urlExpiryEpoch.equals(artifactManifest.getUrlExpiryEpoch()) && this.artifactSize.equals(artifactManifest.getArtifactSize()) && this.checksum.equals(artifactManifest.getChecksum());
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public String getArtifactIdentifier() {
                return this.artifactIdentifier;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public String getArtifactKey() {
                return this.artifactKey;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public Long getArtifactSize() {
                return this.artifactSize;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public Long getArtifactTimeToLive() {
                return this.artifactTimeToLive;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public String getArtifactType() {
                return this.artifactType;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public Checksum getChecksum() {
                return this.checksum;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public String getDownloadUrl() {
                return this.downloadUrl;
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.models.ArtifactManifest
            public Long getUrlExpiryEpoch() {
                return this.urlExpiryEpoch;
            }

            public int hashCode() {
                return ((((((((((((((this.artifactType.hashCode() ^ 1000003) * 1000003) ^ this.artifactKey.hashCode()) * 1000003) ^ this.downloadUrl.hashCode()) * 1000003) ^ this.artifactTimeToLive.hashCode()) * 1000003) ^ this.artifactIdentifier.hashCode()) * 1000003) ^ this.urlExpiryEpoch.hashCode()) * 1000003) ^ this.artifactSize.hashCode()) * 1000003) ^ this.checksum.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactManifest{artifactType=");
                outline107.append(this.artifactType);
                outline107.append(", artifactKey=");
                outline107.append(this.artifactKey);
                outline107.append(", downloadUrl=");
                outline107.append(this.downloadUrl);
                outline107.append(", artifactTimeToLive=");
                outline107.append(this.artifactTimeToLive);
                outline107.append(", artifactIdentifier=");
                outline107.append(this.artifactIdentifier);
                outline107.append(", urlExpiryEpoch=");
                outline107.append(this.urlExpiryEpoch);
                outline107.append(", artifactSize=");
                outline107.append(this.artifactSize);
                outline107.append(", checksum=");
                outline107.append(this.checksum);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
