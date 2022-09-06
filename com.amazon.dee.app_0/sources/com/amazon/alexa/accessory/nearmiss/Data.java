package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class Data implements JsonObjectSerializable {
    private final DataPart dataPart;
    private final Source payload;

    /* loaded from: classes.dex */
    public static final class Builder {
        public DataPart dataPart;
        public Source payload;

        public Data build() {
            Preconditions.notNull(this.dataPart, "datapart");
            Preconditions.notNull(this.payload, "payload");
            return new Data(this);
        }

        public Builder dataPart(DataPart dataPart) {
            this.dataPart = dataPart;
            return this;
        }

        public Builder payload(Source source) {
            this.payload = source;
            return this;
        }
    }

    public Data(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.dataPart = builder.dataPart;
        this.payload = builder.payload;
    }

    public DataPart getDataPart() {
        return this.dataPart;
    }

    public Source getPayload() {
        return this.payload;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("part", this.dataPart.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Data{dataPart=");
        outline107.append(this.dataPart);
        outline107.append(", payload=");
        outline107.append(this.payload);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
