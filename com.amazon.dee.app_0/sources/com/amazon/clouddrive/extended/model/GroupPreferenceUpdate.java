package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class GroupPreferenceUpdate {
    private String id;
    private String value;

    /* loaded from: classes11.dex */
    public static class Builder {
        private String id;
        private String value;

        public GroupPreferenceUpdate build() {
            return new GroupPreferenceUpdate(this.id, this.value);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Builder{id='");
            GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", value='");
            return GeneratedOutlineSupport1.outline90(outline107, this.value, Chars.QUOTE, JsonReaderKt.END_OBJ);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withValue(String str) {
            this.value = str;
            return this;
        }
    }

    public GroupPreferenceUpdate(String str, String str2) {
        this.id = str;
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupPreferenceUpdate)) {
            return false;
        }
        GroupPreferenceUpdate groupPreferenceUpdate = (GroupPreferenceUpdate) obj;
        if (getId() == null ? groupPreferenceUpdate.getId() == null : getId().equals(groupPreferenceUpdate.getId())) {
            if (getValue() != null) {
                if (getValue().equals(groupPreferenceUpdate.getValue())) {
                    return true;
                }
            } else if (groupPreferenceUpdate.getValue() == null) {
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getId() != null ? getId().hashCode() : 0) * 31;
        if (getValue() != null) {
            i = getValue().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreferenceUpdate{id='");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", value='");
        return GeneratedOutlineSupport1.outline90(outline107, this.value, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
