package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class MetaDataPart implements JsonObjectSerializable {
    public static final String JSON_TYPE = "JSON";
    private final Format format;
    private final int id;
    private final String type;

    /* loaded from: classes.dex */
    public static final class Builder {
        public Format format;
        public int id;
        public String type;

        public MetaDataPart build() {
            Preconditions.notNull(this.format, "format");
            Preconditions.notNull(this.type, "type");
            return new MetaDataPart(this);
        }

        public Builder format(Format format) {
            this.format = format;
            return this;
        }

        public Builder id(int i) {
            this.id = i;
            return this;
        }

        public Builder type(String str) {
            this.type = str;
            return this;
        }
    }

    public MetaDataPart(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.format = builder.format;
        this.id = builder.id;
        this.type = builder.type;
    }

    public Format getFormat() {
        return this.format;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("type", this.type).put("format", this.format.toJsonObject()).put("id", this.id);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetaDataPart{format=");
        outline107.append(this.format);
        outline107.append(", type='");
        GeneratedOutlineSupport1.outline176(outline107, this.type, Chars.QUOTE, ", id=");
        return GeneratedOutlineSupport1.outline85(outline107, this.id, JsonReaderKt.END_OBJ);
    }
}
