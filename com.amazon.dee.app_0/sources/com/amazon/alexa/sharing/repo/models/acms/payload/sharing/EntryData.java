package com.amazon.alexa.sharing.repo.models.acms.payload.sharing;

import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.enums.EntryDataType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes10.dex */
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EntryData{type=");
        outline107.append(this.type);
        outline107.append(", url=");
        outline107.append(this.url);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
