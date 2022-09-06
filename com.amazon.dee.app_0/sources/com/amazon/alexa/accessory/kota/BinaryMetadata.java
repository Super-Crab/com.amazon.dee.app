package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class BinaryMetadata implements JsonObjectSerializable {
    private static final String JSON_BINARY_TYPE = "binaryType";
    private static final String JSON_MD5_SUM = "md5Sum";
    private final String binaryType;
    private final String md5Sum;

    /* loaded from: classes.dex */
    public static final class Builder {
        String binaryType;
        String md5Sum;

        public Builder binaryType(String str) {
            this.binaryType = str;
            return this;
        }

        public BinaryMetadata build() {
            Preconditions.notNull(this.binaryType, BinaryMetadata.JSON_BINARY_TYPE);
            Preconditions.notNull(this.md5Sum, BinaryMetadata.JSON_MD5_SUM);
            return new BinaryMetadata(this);
        }

        public Builder md5Sum(String str) {
            this.md5Sum = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonFactory implements JsonObjectSerializable.Factory<BinaryMetadata> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public BinaryMetadata mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().binaryType(jSONObject.getString(BinaryMetadata.JSON_BINARY_TYPE)).md5Sum(jSONObject.getString(BinaryMetadata.JSON_MD5_SUM)).build();
        }
    }

    BinaryMetadata(Builder builder) {
        this.binaryType = builder.binaryType;
        this.md5Sum = builder.md5Sum;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BinaryMetadata.class != obj.getClass()) {
            return false;
        }
        BinaryMetadata binaryMetadata = (BinaryMetadata) obj;
        if (this.binaryType.equals(binaryMetadata.binaryType)) {
            return this.md5Sum.equals(binaryMetadata.md5Sum);
        }
        return false;
    }

    public String getBinaryType() {
        return this.binaryType;
    }

    public String getMd5Sum() {
        return this.md5Sum;
    }

    public int hashCode() {
        return this.md5Sum.hashCode() + (this.binaryType.hashCode() * 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(JSON_BINARY_TYPE, this.binaryType).put(JSON_MD5_SUM, this.md5Sum);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BinaryMetadata{binaryType='");
        GeneratedOutlineSupport1.outline176(outline107, this.binaryType, Chars.QUOTE, ", md5Sum='");
        return GeneratedOutlineSupport1.outline90(outline107, this.md5Sum, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
