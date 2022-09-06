package com.amazon.alexa.accessory.nearmiss;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class Parts implements JsonObjectSerializable {
    private final Audio audio;

    /* loaded from: classes.dex */
    public static final class Builder {
        Audio audio;

        public Builder audio(Audio audio) {
            this.audio = audio;
            return this;
        }

        public Parts build() {
            Preconditions.notNull(this.audio, "audio");
            return new Parts(this);
        }
    }

    Parts(Builder builder) {
        this.audio = builder.audio;
    }

    public Audio getAudio() {
        return this.audio;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("audio", this.audio.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parts{audio=");
        outline107.append(this.audio);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
