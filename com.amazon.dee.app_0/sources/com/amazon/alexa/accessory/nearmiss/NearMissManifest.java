package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class NearMissManifest implements JsonObjectSerializable {
    private final Parts parts;
    private final UploadType uploadType;
    private final int version;

    /* loaded from: classes.dex */
    public enum UploadType {
        WAKE_WORD_NEAR_MISS
    }

    public NearMissManifest(int i, UploadType uploadType, Parts parts) {
        Preconditions.notNull(uploadType, "uploadType");
        Preconditions.notNull(parts, "parts");
        this.version = i;
        this.uploadType = uploadType;
        this.parts = parts;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NearMissManifest.class != obj.getClass()) {
            return false;
        }
        NearMissManifest nearMissManifest = (NearMissManifest) obj;
        if (this.version != nearMissManifest.version || this.uploadType != nearMissManifest.uploadType) {
            return false;
        }
        return this.parts.equals(nearMissManifest.parts);
    }

    public Parts getParts() {
        return this.parts;
    }

    public int hashCode() {
        return ((this.parts.hashCode() + (this.uploadType.hashCode() * 31)) * 31) + this.version;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("version", this.version).put("uploadType", this.uploadType).put("parts", this.parts.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Manifest{uploadType=");
        outline107.append(this.uploadType);
        outline107.append(", parts=");
        outline107.append(this.parts);
        outline107.append(", version=");
        return GeneratedOutlineSupport1.outline85(outline107, this.version, JsonReaderKt.END_OBJ);
    }
}
