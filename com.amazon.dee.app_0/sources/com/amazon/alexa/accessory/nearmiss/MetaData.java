package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class MetaData implements JsonObjectSerializable {
    private final MetaDataPart metaDataPart;
    private final MetaDataPayload payload;

    /* loaded from: classes.dex */
    public static final class Builder {
        public MetaDataPart metaDataPart;
        public MetaDataPayload payload;

        public MetaData build() {
            return new MetaData(this);
        }

        public Builder metadataPart(MetaDataPart metaDataPart) {
            this.metaDataPart = metaDataPart;
            return this;
        }

        public Builder payload(MetaDataPayload metaDataPayload) {
            this.payload = metaDataPayload;
            return this;
        }
    }

    public MetaData(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.metaDataPart = builder.metaDataPart;
        this.payload = builder.payload;
    }

    public MetaDataPart getMetaDataPart() {
        return this.metaDataPart;
    }

    public MetaDataPayload getPayload() {
        return this.payload;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("part", this.metaDataPart.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetaData{metaDataPart=");
        outline107.append(this.metaDataPart);
        outline107.append(", payload=");
        outline107.append(this.payload);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
