package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class Inventory implements JsonObjectSerializable {
    private String softwareComponentId;
    private int softwareComponentVersionCode;

    /* loaded from: classes.dex */
    public static final class Builder {
        String softwareComponentId;
        int softwareComponentVersionCode;

        public Inventory build() {
            Preconditions.notNull(this.softwareComponentId, "softwareComponentId");
            return new Inventory(this);
        }

        public Builder softwareComponentId(String str) {
            this.softwareComponentId = str;
            return this;
        }

        public Builder softwareComponentVersionCode(int i) {
            this.softwareComponentVersionCode = i;
            return this;
        }
    }

    Inventory(Builder builder) {
        this.softwareComponentId = builder.softwareComponentId;
        this.softwareComponentVersionCode = builder.softwareComponentVersionCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Inventory.class != obj.getClass()) {
            return false;
        }
        Inventory inventory = (Inventory) obj;
        if (this.softwareComponentVersionCode == inventory.softwareComponentVersionCode) {
            return this.softwareComponentId.equals(inventory.softwareComponentId);
        }
        return false;
    }

    public String getSoftwareComponentId() {
        return this.softwareComponentId;
    }

    public int getSoftwareComponentVersionCode() {
        return this.softwareComponentVersionCode;
    }

    public int hashCode() {
        return (this.softwareComponentId.hashCode() * 31) + this.softwareComponentVersionCode;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("softwareComponentId", this.softwareComponentId).put("softwareComponentVersionCode", this.softwareComponentVersionCode);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inventory{softwareComponentId='");
        GeneratedOutlineSupport1.outline176(outline107, this.softwareComponentId, Chars.QUOTE, ", softwareComponentVersionCode=");
        return GeneratedOutlineSupport1.outline85(outline107, this.softwareComponentVersionCode, JsonReaderKt.END_OBJ);
    }
}
