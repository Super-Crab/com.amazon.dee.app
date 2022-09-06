package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class Audio implements JsonObjectSerializable {
    private final Data data;
    private final MetaData metaData;

    /* loaded from: classes.dex */
    public static final class Builder {
        public Data data;
        public MetaData metaData;

        public Audio build() {
            Preconditions.notNull(this.data, "data");
            Preconditions.notNull(this.metaData, "metaData");
            return new Audio(this);
        }

        public Builder data(Data data) {
            this.data = data;
            return this;
        }

        public Builder metaData(MetaData metaData) {
            this.metaData = metaData;
            return this;
        }
    }

    public Audio(Builder builder) {
        this.data = builder.data;
        this.metaData = builder.metaData;
    }

    public Data getData() {
        return this.data;
    }

    public MetaData getMetaData() {
        return this.metaData;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("data", this.data.toJsonObject()).put("metadata", this.metaData.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Audio{data=");
        outline107.append(this.data);
        outline107.append(", metaData=");
        outline107.append(this.metaData);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
