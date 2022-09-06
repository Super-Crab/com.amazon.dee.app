package com.amazon.comms.models.scrubber;

import com.amazon.comms.models.scrubber.ScrubbedStringSerializationDeserializationSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.JsonAdapter;
@JsonDeserialize(using = ScrubbedStringSerializationDeserializationSupport.JacksonDeserializer.class)
@JsonSerialize(using = ScrubbedStringSerializationDeserializationSupport.JacksonSerializer.class)
@JsonAdapter(ScrubbedStringSerializationDeserializationSupport.GsonSerializerDeserializer.class)
/* loaded from: classes11.dex */
public class ScrubbedString {
    static final String SCRUB_END_TAG = "</scrub>";
    static final String SCRUB_START_TAG = "<scrub>";
    private String value;

    public ScrubbedString() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScrubbedString unscrub(String str) {
        return new ScrubbedString(str.replaceAll(SCRUB_START_TAG, "").replaceAll(SCRUB_END_TAG, ""));
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ScrubbedString;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScrubbedString)) {
            return false;
        }
        ScrubbedString scrubbedString = (ScrubbedString) obj;
        if (!scrubbedString.canEqual(this)) {
            return false;
        }
        String value = getValue();
        String value2 = scrubbedString.getValue();
        return value != null ? value.equals(value2) : value2 == null;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        String value = getValue();
        return 59 + (value == null ? 43 : value.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String scrubbed() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(SCRUB_START_TAG), this.value, SCRUB_END_TAG);
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ScrubbedString(value=");
        outline107.append(getValue());
        outline107.append(")");
        return outline107.toString();
    }

    public ScrubbedString(String str) {
        this.value = str;
    }
}
