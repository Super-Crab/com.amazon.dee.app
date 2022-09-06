package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class GroupPreferenceSetUpdate {
    private String id;
    private List<GroupPreferenceUpdate> preferences;

    /* loaded from: classes11.dex */
    public static class Builder {
        private String id;
        private List<GroupPreferenceUpdate> preferences;

        public GroupPreferenceSetUpdate build() {
            return new GroupPreferenceSetUpdate(this.id, this.preferences);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Builder{id='");
            GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", preferences=");
            return GeneratedOutlineSupport1.outline94(outline107, this.preferences, JsonReaderKt.END_OBJ);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withPreferences(List<GroupPreferenceUpdate> list) {
            this.preferences = list;
            return this;
        }
    }

    public GroupPreferenceSetUpdate(String str, List<GroupPreferenceUpdate> list) {
        this.id = str;
        this.preferences = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupPreferenceSetUpdate)) {
            return false;
        }
        GroupPreferenceSetUpdate groupPreferenceSetUpdate = (GroupPreferenceSetUpdate) obj;
        if (getId() == null ? groupPreferenceSetUpdate.getId() == null : getId().equals(groupPreferenceSetUpdate.getId())) {
            if (getPreferences() != null) {
                if (getPreferences().equals(groupPreferenceSetUpdate.getPreferences())) {
                    return true;
                }
            } else if (groupPreferenceSetUpdate.getPreferences() == null) {
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public List<GroupPreferenceUpdate> getPreferences() {
        return this.preferences;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getId() != null ? getId().hashCode() : 0) * 31;
        if (getPreferences() != null) {
            i = getPreferences().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreferenceSetUpdate{id='");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", preferences=");
        return GeneratedOutlineSupport1.outline94(outline107, this.preferences, JsonReaderKt.END_OBJ);
    }
}
