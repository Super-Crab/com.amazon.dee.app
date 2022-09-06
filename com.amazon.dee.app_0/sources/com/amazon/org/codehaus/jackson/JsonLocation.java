package com.amazon.org.codehaus.jackson;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import com.amazon.org.codehaus.jackson.annotate.JsonCreator;
import com.amazon.org.codehaus.jackson.annotate.JsonProperty;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class JsonLocation implements Serializable {
    public static final JsonLocation NA = new JsonLocation(CapabilityQueryConstants.TARGET_NOT_AVAILABLE, -1, -1, -1, -1);
    private static final long serialVersionUID = 1;
    final int _columnNr;
    final int _lineNr;
    final Object _sourceRef;
    final long _totalBytes;
    final long _totalChars;

    public JsonLocation(Object obj, long j, int i, int i2) {
        this(obj, -1L, j, i, i2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonLocation)) {
            return false;
        }
        JsonLocation jsonLocation = (JsonLocation) obj;
        Object obj2 = this._sourceRef;
        if (obj2 == null) {
            if (jsonLocation._sourceRef != null) {
                return false;
            }
        } else if (!obj2.equals(jsonLocation._sourceRef)) {
            return false;
        }
        return this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && getByteOffset() == jsonLocation.getByteOffset();
    }

    public long getByteOffset() {
        return this._totalBytes;
    }

    public long getCharOffset() {
        return this._totalChars;
    }

    public int getColumnNr() {
        return this._columnNr;
    }

    public int getLineNr() {
        return this._lineNr;
    }

    public Object getSourceRef() {
        return this._sourceRef;
    }

    public int hashCode() {
        Object obj = this._sourceRef;
        return ((((obj == null ? 1 : obj.hashCode()) ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    public String toString() {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(80, "[Source: ");
        Object obj = this._sourceRef;
        if (obj == null) {
            outline105.append("UNKNOWN");
        } else {
            outline105.append(obj.toString());
        }
        outline105.append("; line: ");
        outline105.append(this._lineNr);
        outline105.append(", column: ");
        return GeneratedOutlineSupport1.outline85(outline105, this._columnNr, JsonReaderKt.END_LIST);
    }

    @JsonCreator
    public JsonLocation(@JsonProperty("sourceRef") Object obj, @JsonProperty("byteOffset") long j, @JsonProperty("charOffset") long j2, @JsonProperty("lineNr") int i, @JsonProperty("columnNr") int i2) {
        this._sourceRef = obj;
        this._totalBytes = j;
        this._totalChars = j2;
        this._lineNr = i;
        this._columnNr = i2;
    }
}
