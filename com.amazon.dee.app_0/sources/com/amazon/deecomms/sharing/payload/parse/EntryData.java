package com.amazon.deecomms.sharing.payload.parse;

import com.amazon.deecomms.sharing.payload.parse.enums.EntryDataType;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class EntryData {
    private EntryDataType type;
    private URLHolder url;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EntryData.class != obj.getClass()) {
            return false;
        }
        EntryData entryData = (EntryData) obj;
        return this.type == entryData.type && Objects.equals(this.url, entryData.url);
    }

    public EntryDataType getType() {
        return this.type;
    }

    public URLHolder getUrl() {
        return this.url;
    }

    public int hashCode() {
        return Objects.hash(this.type, this.url);
    }

    public void setType(EntryDataType entryDataType) {
        this.type = entryDataType;
    }

    public void setUrl(URLHolder uRLHolder) {
        this.url = uRLHolder;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("EntryData{type=");
        outline1.append(this.type);
        outline1.append(", url=");
        outline1.append(this.url);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }
}
