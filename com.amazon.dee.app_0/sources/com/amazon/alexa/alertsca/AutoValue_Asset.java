package com.amazon.alexa.alertsca;

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
/* loaded from: classes6.dex */
public final class AutoValue_Asset extends C$AutoValue_Asset {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Asset> {
        private volatile TypeAdapter<AssetIdentifier> assetIdentifier_adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("assetId", "url");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Asset.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Asset mo8353read(JsonReader jsonReader) throws IOException {
            AssetIdentifier assetIdentifier = null;
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
                    if (this.realFieldNames.get("assetId").equals(nextName)) {
                        TypeAdapter<AssetIdentifier> typeAdapter = this.assetIdentifier_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(AssetIdentifier.class);
                            this.assetIdentifier_adapter = typeAdapter;
                        }
                        assetIdentifier = typeAdapter.mo8353read(jsonReader);
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
            return new AutoValue_Asset(assetIdentifier, str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Asset asset) throws IOException {
            if (asset == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("assetId"));
            if (asset.getAssetId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AssetIdentifier> typeAdapter = this.assetIdentifier_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(AssetIdentifier.class);
                    this.assetIdentifier_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, asset.getAssetId());
            }
            jsonWriter.name(this.realFieldNames.get("url"));
            if (asset.getUrl() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, asset.getUrl());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Asset(final AssetIdentifier assetIdentifier, final String str) {
        new Asset(assetIdentifier, str) { // from class: com.amazon.alexa.alertsca.$AutoValue_Asset
            private final AssetIdentifier assetId;
            private final String url;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (assetIdentifier != null) {
                    this.assetId = assetIdentifier;
                    if (str != null) {
                        this.url = str;
                        return;
                    }
                    throw new NullPointerException("Null url");
                }
                throw new NullPointerException("Null assetId");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Asset)) {
                    return false;
                }
                Asset asset = (Asset) obj;
                return this.assetId.equals(asset.getAssetId()) && this.url.equals(asset.getUrl());
            }

            @Override // com.amazon.alexa.alertsca.Asset
            public AssetIdentifier getAssetId() {
                return this.assetId;
            }

            @Override // com.amazon.alexa.alertsca.Asset
            public String getUrl() {
                return this.url;
            }

            public int hashCode() {
                return ((this.assetId.hashCode() ^ 1000003) * 1000003) ^ this.url.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Asset{assetId=");
                outline107.append(this.assetId);
                outline107.append(", url=");
                return GeneratedOutlineSupport1.outline91(outline107, this.url, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
