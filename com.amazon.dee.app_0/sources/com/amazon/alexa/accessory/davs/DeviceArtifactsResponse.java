package com.amazon.alexa.accessory.davs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class DeviceArtifactsResponse implements JsonObjectSerializable {
    private static final String ARTIFACT_IDENTIFIER_KEY = "artifactIdentifier";
    private static final String ARTIFACT_KEY_KEY = "artifactKey";
    private static final String ARTIFACT_SIZE_KEY = "artifactSize";
    private static final String ARTIFACT_TYPE_KEY = "artifactType";
    private static final String CHECKSUM_KEY = "checksum";
    private static final String DOWNLOAD_URL_KEY = "downloadUrl";
    public static final Factory FACTORY = new Factory();
    private static final String MD5_VALUE_KEY = "md5";
    private static final String SIGNATURES_VALUE_KEY = "signatures";
    private static final String TIME_TO_LIVE_KEY = "artifactTimeToLive";
    private static final String URL_EXPIRY_KEY = "urlExpiryEpoch";
    private final String artifactIdentifier;
    private final String artifactKey;
    private final long artifactSize;
    private final long artifactTimeToLive;
    private final String artifactType;
    private final List<DavsArtifactSignature> davsArtifactSignatures;
    private final String downloadUrl;
    private final String md5Value;
    private final long urlExpiryEpoch;

    /* loaded from: classes.dex */
    public static final class Builder {
        String artifactIdentifier;
        String artifactKey;
        long artifactSize;
        long artifactTimeToLive;
        String artifactType;
        List<DavsArtifactSignature> davsArtifactSignatures;
        String downloadUrl;
        String md5Value;
        long urlExpiryEpoch;

        public Builder artifactIdentifier(String str) {
            this.artifactIdentifier = str;
            return this;
        }

        public Builder artifactKey(String str) {
            this.artifactKey = str;
            return this;
        }

        public Builder artifactSize(long j) {
            this.artifactSize = j;
            return this;
        }

        public Builder artifactTimeToLive(long j) {
            this.artifactTimeToLive = j;
            return this;
        }

        public Builder artifactType(String str) {
            this.artifactType = str;
            return this;
        }

        public DeviceArtifactsResponse build() {
            return new DeviceArtifactsResponse(this);
        }

        public Builder davsArtifactSignatures(List<DavsArtifactSignature> list) {
            this.davsArtifactSignatures = list;
            return this;
        }

        public Builder downloadUrl(String str) {
            this.downloadUrl = str;
            return this;
        }

        public Builder md5Value(String str) {
            this.md5Value = str;
            return this;
        }

        public Builder urlExpiryEpoch(long j) {
            this.urlExpiryEpoch = j;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceArtifactsResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceArtifactsResponse mo1239create(JSONObject jSONObject) throws JSONException {
            Builder md5Value = new Builder().artifactType(jSONObject.getString(DeviceArtifactsResponse.ARTIFACT_TYPE_KEY)).artifactKey(jSONObject.getString(DeviceArtifactsResponse.ARTIFACT_KEY_KEY)).downloadUrl(jSONObject.getString(DeviceArtifactsResponse.DOWNLOAD_URL_KEY)).artifactTimeToLive(jSONObject.getLong(DeviceArtifactsResponse.TIME_TO_LIVE_KEY)).artifactIdentifier(jSONObject.getString(DeviceArtifactsResponse.ARTIFACT_IDENTIFIER_KEY)).urlExpiryEpoch(jSONObject.getLong(DeviceArtifactsResponse.URL_EXPIRY_KEY)).artifactSize(jSONObject.getLong(DeviceArtifactsResponse.ARTIFACT_SIZE_KEY)).md5Value(jSONObject.getJSONObject(DeviceArtifactsResponse.CHECKSUM_KEY).getString("md5"));
            if (jSONObject.has(DeviceArtifactsResponse.SIGNATURES_VALUE_KEY)) {
                md5Value.davsArtifactSignatures(JsonUtils.fromJsonArray(jSONObject.getJSONArray(DeviceArtifactsResponse.SIGNATURES_VALUE_KEY), DavsArtifactSignature.FACTORY));
            }
            return md5Value.build();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceArtifactsResponse.class != obj.getClass()) {
            return false;
        }
        DeviceArtifactsResponse deviceArtifactsResponse = (DeviceArtifactsResponse) obj;
        if (!this.artifactType.equals(deviceArtifactsResponse.artifactType) || !this.artifactKey.equals(deviceArtifactsResponse.artifactKey) || !this.downloadUrl.equals(deviceArtifactsResponse.downloadUrl) || this.artifactTimeToLive != deviceArtifactsResponse.artifactTimeToLive || !this.artifactIdentifier.equals(deviceArtifactsResponse.artifactIdentifier) || this.urlExpiryEpoch != deviceArtifactsResponse.urlExpiryEpoch || this.artifactSize != deviceArtifactsResponse.artifactSize) {
            return false;
        }
        return this.md5Value.equals(deviceArtifactsResponse.md5Value);
    }

    public String getArtifactIdentifier() {
        return this.artifactIdentifier;
    }

    public String getArtifactKey() {
        return this.artifactKey;
    }

    public long getArtifactSize() {
        return this.artifactSize;
    }

    public long getArtifactTimeToLive() {
        return this.artifactTimeToLive;
    }

    public String getArtifactType() {
        return this.artifactType;
    }

    public List<DavsArtifactSignature> getDavsArtifactSignatures() {
        return this.davsArtifactSignatures;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getMd5Value() {
        return this.md5Value;
    }

    public long getUrlExpiryEpoch() {
        return this.urlExpiryEpoch;
    }

    public int hashCode() {
        return Objects.hash(this.artifactType, this.artifactKey, this.downloadUrl, Long.valueOf(this.artifactTimeToLive), this.artifactIdentifier, Long.valueOf(this.urlExpiryEpoch), Long.valueOf(this.artifactSize), this.md5Value, this.davsArtifactSignatures);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("md5", this.md5Value);
        JSONObject put = new JSONObject().put(ARTIFACT_TYPE_KEY, this.artifactType).put(ARTIFACT_KEY_KEY, this.artifactKey).put(DOWNLOAD_URL_KEY, this.downloadUrl).put(TIME_TO_LIVE_KEY, this.artifactTimeToLive).put(ARTIFACT_IDENTIFIER_KEY, this.artifactIdentifier).put(URL_EXPIRY_KEY, this.urlExpiryEpoch).put(ARTIFACT_SIZE_KEY, this.artifactSize).put(CHECKSUM_KEY, jSONObject);
        List<DavsArtifactSignature> list = this.davsArtifactSignatures;
        if (list != null && list.size() > 0) {
            put.put(SIGNATURES_VALUE_KEY, JsonUtils.toJsonArray(this.davsArtifactSignatures));
        }
        return put;
    }

    @NonNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceArtifactsResponse{artifactType=");
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
        outline107.append(", md5Value=");
        return GeneratedOutlineSupport1.outline89(outline107, this.md5Value, JsonReaderKt.END_OBJ);
    }

    private DeviceArtifactsResponse(Builder builder) {
        this.artifactType = builder.artifactType;
        this.artifactKey = builder.artifactKey;
        this.downloadUrl = builder.downloadUrl;
        this.artifactTimeToLive = builder.artifactTimeToLive;
        this.artifactIdentifier = builder.artifactIdentifier;
        this.urlExpiryEpoch = builder.urlExpiryEpoch;
        this.artifactSize = builder.artifactSize;
        this.md5Value = builder.md5Value;
        this.davsArtifactSignatures = builder.davsArtifactSignatures;
    }
}
