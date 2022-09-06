package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class GroupPreferenceSet {
    private String id;
    private List<GroupPreference> preferences;
    private String title;

    /* loaded from: classes11.dex */
    public static class Builder {
        private String id;
        private List<GroupPreference> preferences;
        private String title;

        public GroupPreferenceSet build() {
            return new GroupPreferenceSet(this.id, this.preferences, this.title);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Builder{id='");
            GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", preferences=");
            outline107.append(this.preferences);
            outline107.append(", title='");
            return GeneratedOutlineSupport1.outline90(outline107, this.title, Chars.QUOTE, JsonReaderKt.END_OBJ);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withPreferences(List<GroupPreference> list) {
            this.preferences = list;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
            return this;
        }
    }

    public GroupPreferenceSet(String str, List<GroupPreference> list, String str2) {
        this.id = str;
        this.preferences = list;
        this.title = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupPreferenceSet)) {
            return false;
        }
        GroupPreferenceSet groupPreferenceSet = (GroupPreferenceSet) obj;
        if (getId() == null ? groupPreferenceSet.getId() == null : getId().equals(groupPreferenceSet.getId())) {
            if (getPreferences() == null ? groupPreferenceSet.getPreferences() == null : getPreferences().equals(groupPreferenceSet.getPreferences())) {
                if (getTitle() != null) {
                    if (getTitle().equals(groupPreferenceSet.getTitle())) {
                        return true;
                    }
                } else if (groupPreferenceSet.getTitle() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public List<GroupPreference> getPreferences() {
        return this.preferences;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((getId() != null ? getId().hashCode() : 0) * 31) + (getPreferences() != null ? getPreferences().hashCode() : 0)) * 31;
        if (getTitle() != null) {
            i = getTitle().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreferenceSet{id='");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", preferences=");
        outline107.append(this.preferences);
        outline107.append(", title='");
        return GeneratedOutlineSupport1.outline90(outline107, this.title, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
