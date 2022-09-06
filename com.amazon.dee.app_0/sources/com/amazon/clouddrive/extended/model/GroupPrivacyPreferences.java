package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public final class GroupPrivacyPreferences {
    private final List<GroupPreferenceSet> preferenceSets;

    /* loaded from: classes11.dex */
    public static class GroupPrivacyPreferencesBuilder {
        private List<GroupPreferenceSet> preferenceSets;

        GroupPrivacyPreferencesBuilder() {
        }

        public GroupPrivacyPreferences build() {
            return new GroupPrivacyPreferences(this.preferenceSets);
        }

        public GroupPrivacyPreferencesBuilder preferenceSets(List<GroupPreferenceSet> list) {
            this.preferenceSets = list;
            return this;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("GroupPrivacyPreferences.GroupPrivacyPreferencesBuilder(preferenceSets="), this.preferenceSets, ")");
        }
    }

    GroupPrivacyPreferences(List<GroupPreferenceSet> list) {
        this.preferenceSets = list;
    }

    public static GroupPrivacyPreferencesBuilder builder() {
        return new GroupPrivacyPreferencesBuilder();
    }

    public int compareTo(Object obj) {
        if (this == obj) {
            return 0;
        }
        if (obj == null || !(obj instanceof GroupPrivacyPreferences)) {
            return -1;
        }
        return ObjectComparator.compare(getPreferenceSets(), ((GroupPrivacyPreferences) obj).getPreferenceSets());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupPrivacyPreferences)) {
            return false;
        }
        List<GroupPreferenceSet> preferenceSets = getPreferenceSets();
        List<GroupPreferenceSet> preferenceSets2 = ((GroupPrivacyPreferences) obj).getPreferenceSets();
        return preferenceSets != null ? preferenceSets.equals(preferenceSets2) : preferenceSets2 == null;
    }

    public List<GroupPreferenceSet> getPreferenceSets() {
        return this.preferenceSets;
    }

    public int hashCode() {
        List<GroupPreferenceSet> preferenceSets = getPreferenceSets();
        return 59 + (preferenceSets == null ? 43 : preferenceSets.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPrivacyPreferences(preferenceSets=");
        outline107.append(getPreferenceSets());
        outline107.append(")");
        return outline107.toString();
    }
}
