package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class Format implements JsonObjectSerializable {
    public static final String ALEXA_WWNM_METADATA = "ALEXA_WWNM_METADATA";
    private final String name;
    private final int version;

    /* loaded from: classes.dex */
    public static final class Builder {
        public String name;
        public int version;

        public Format build() {
            Preconditions.notNull(this.name, "name");
            return new Format(this);
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder version(int i) {
            this.version = i;
            return this;
        }
    }

    public Format(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.version = builder.version;
        this.name = builder.name;
    }

    public String getName() {
        return this.name;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("version", 1).put("name", ALEXA_WWNM_METADATA);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Format{version=");
        outline107.append(this.version);
        outline107.append(", name='");
        return GeneratedOutlineSupport1.outline90(outline107, this.name, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
