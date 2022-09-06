package com.amazon.alexa.accessory.davs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class DavsArtifactSignature implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();
    static final String JSON_ALGORITHM_KEY = "algorithm";
    static final String JSON_CREATOR_ID_KEY = "creatorId";
    static final String JSON_SIGNATURE_KEY = "signature";
    static final String JSON_VERSION_KEY = "version";
    private final String algorithm;
    private final String creatorId;
    private final String signature;
    private final String version;

    /* loaded from: classes.dex */
    public static final class Builder {
        private String algorithm;
        private String creatorId;
        private String signature;
        private String version;

        public Builder algorithm(String str) {
            this.algorithm = str;
            return this;
        }

        public DavsArtifactSignature build() {
            return new DavsArtifactSignature(this);
        }

        public Builder creatorId(String str) {
            this.creatorId = str;
            return this;
        }

        public Builder signature(String str) {
            this.signature = str;
            return this;
        }

        public Builder version(String str) {
            this.version = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DavsArtifactSignature> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DavsArtifactSignature mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().algorithm(jSONObject.getString(DavsArtifactSignature.JSON_ALGORITHM_KEY)).creatorId(jSONObject.optString(DavsArtifactSignature.JSON_CREATOR_ID_KEY)).signature(jSONObject.getString(DavsArtifactSignature.JSON_SIGNATURE_KEY)).version(jSONObject.optString("version")).build();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DavsArtifactSignature.class != obj.getClass()) {
            return false;
        }
        DavsArtifactSignature davsArtifactSignature = (DavsArtifactSignature) obj;
        if (!this.algorithm.equals(davsArtifactSignature.algorithm) || !this.creatorId.equals(davsArtifactSignature.creatorId) || !this.signature.equals(davsArtifactSignature.signature)) {
            return false;
        }
        return this.version.equals(davsArtifactSignature.version);
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return Objects.hash(this.algorithm, this.creatorId, this.signature, this.version);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(JSON_ALGORITHM_KEY, this.algorithm).put(JSON_CREATOR_ID_KEY, this.creatorId).put(JSON_SIGNATURE_KEY, this.signature).put("version", this.version);
    }

    @NonNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DavsArtifactSignature={\"algorithm\":");
        outline107.append(this.algorithm);
        outline107.append(", \"creatorId\":");
        outline107.append(this.creatorId);
        outline107.append(", \"signature\":");
        outline107.append(this.signature);
        outline107.append(", \"version\":");
        return GeneratedOutlineSupport1.outline91(outline107, this.version, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private DavsArtifactSignature(Builder builder) {
        this.algorithm = builder.algorithm;
        this.creatorId = builder.creatorId;
        this.signature = builder.signature;
        this.version = builder.version;
    }
}
