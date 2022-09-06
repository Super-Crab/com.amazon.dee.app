package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class DataPart implements JsonObjectSerializable {
    public static final String AUDIO_TYPE = "AUDIO";
    private final AudioContentType audioContentType;
    private final int id;
    private final String type;

    /* loaded from: classes.dex */
    public static final class Builder {
        public AudioContentType audioContentType;
        public int id;
        public String type;

        public Builder audioContentType(AudioContentType audioContentType) {
            this.audioContentType = audioContentType;
            return this;
        }

        public DataPart build() {
            Preconditions.notNull(this.type, "type");
            Preconditions.notNull(this.audioContentType, "audioContentType");
            return new DataPart(this);
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

    public DataPart(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.type = builder.type;
        this.id = builder.id;
        this.audioContentType = builder.audioContentType;
    }

    public AudioContentType getContentType() {
        return this.audioContentType;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("type", this.type).put("id", this.id);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataPart{type='");
        GeneratedOutlineSupport1.outline176(outline107, this.type, Chars.QUOTE, ", id=");
        outline107.append(this.id);
        outline107.append(", audioContentType=");
        outline107.append(this.audioContentType);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
