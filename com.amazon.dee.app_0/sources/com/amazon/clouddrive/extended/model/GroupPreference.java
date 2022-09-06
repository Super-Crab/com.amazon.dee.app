package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public final class GroupPreference {
    private final List<GroupPreferenceValue> availableValues;
    private final String id;
    private final String title;
    private final String value;
    private final String valueKind;

    /* loaded from: classes11.dex */
    public static class GroupPreferenceBuilder {
        private List<GroupPreferenceValue> availableValues;
        private String id;
        private String title;
        private String value;
        private String valueKind;

        GroupPreferenceBuilder() {
        }

        public GroupPreferenceBuilder availableValues(List<GroupPreferenceValue> list) {
            this.availableValues = list;
            return this;
        }

        public GroupPreference build() {
            return new GroupPreference(this.id, this.title, this.value, this.valueKind, this.availableValues);
        }

        public GroupPreferenceBuilder id(String str) {
            this.id = str;
            return this;
        }

        public GroupPreferenceBuilder title(String str) {
            this.title = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreference.GroupPreferenceBuilder(id=");
            outline107.append(this.id);
            outline107.append(", title=");
            outline107.append(this.title);
            outline107.append(", value=");
            outline107.append(this.value);
            outline107.append(", valueKind=");
            outline107.append(this.valueKind);
            outline107.append(", availableValues=");
            return GeneratedOutlineSupport1.outline95(outline107, this.availableValues, ")");
        }

        public GroupPreferenceBuilder value(String str) {
            this.value = str;
            return this;
        }

        public GroupPreferenceBuilder valueKind(String str) {
            this.valueKind = str;
            return this;
        }
    }

    GroupPreference(String str, String str2, String str3, String str4, List<GroupPreferenceValue> list) {
        this.id = str;
        this.title = str2;
        this.value = str3;
        this.valueKind = str4;
        this.availableValues = list;
    }

    public static GroupPreferenceBuilder builder() {
        return new GroupPreferenceBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupPreference)) {
            return false;
        }
        GroupPreference groupPreference = (GroupPreference) obj;
        String id = getId();
        String id2 = groupPreference.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String title = getTitle();
        String title2 = groupPreference.getTitle();
        if (title != null ? !title.equals(title2) : title2 != null) {
            return false;
        }
        String value = getValue();
        String value2 = groupPreference.getValue();
        if (value != null ? !value.equals(value2) : value2 != null) {
            return false;
        }
        String valueKind = getValueKind();
        String valueKind2 = groupPreference.getValueKind();
        if (valueKind != null ? !valueKind.equals(valueKind2) : valueKind2 != null) {
            return false;
        }
        List<GroupPreferenceValue> availableValues = getAvailableValues();
        List<GroupPreferenceValue> availableValues2 = groupPreference.getAvailableValues();
        return availableValues != null ? availableValues.equals(availableValues2) : availableValues2 == null;
    }

    public List<GroupPreferenceValue> getAvailableValues() {
        return this.availableValues;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getValue() {
        return this.value;
    }

    public String getValueKind() {
        return this.valueKind;
    }

    public int hashCode() {
        String id = getId();
        int i = 43;
        int hashCode = id == null ? 43 : id.hashCode();
        String title = getTitle();
        int hashCode2 = ((hashCode + 59) * 59) + (title == null ? 43 : title.hashCode());
        String value = getValue();
        int hashCode3 = (hashCode2 * 59) + (value == null ? 43 : value.hashCode());
        String valueKind = getValueKind();
        int hashCode4 = (hashCode3 * 59) + (valueKind == null ? 43 : valueKind.hashCode());
        List<GroupPreferenceValue> availableValues = getAvailableValues();
        int i2 = hashCode4 * 59;
        if (availableValues != null) {
            i = availableValues.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupPreference(id=");
        outline107.append(getId());
        outline107.append(", title=");
        outline107.append(getTitle());
        outline107.append(", value=");
        outline107.append(getValue());
        outline107.append(", valueKind=");
        outline107.append(getValueKind());
        outline107.append(", availableValues=");
        outline107.append(getAvailableValues());
        outline107.append(")");
        return outline107.toString();
    }
}
