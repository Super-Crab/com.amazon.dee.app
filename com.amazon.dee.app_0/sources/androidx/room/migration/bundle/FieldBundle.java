package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class FieldBundle implements SchemaEquality<FieldBundle> {
    @SerializedName("affinity")
    private String mAffinity;
    @SerializedName("columnName")
    private String mColumnName;
    @SerializedName("defaultValue")
    private String mDefaultValue;
    @SerializedName("fieldPath")
    private String mFieldPath;
    @SerializedName("notNull")
    private boolean mNonNull;

    @Deprecated
    public FieldBundle(String str, String str2, String str3, boolean z) {
        this(str, str2, str3, z, null);
    }

    public String getAffinity() {
        return this.mAffinity;
    }

    public String getColumnName() {
        return this.mColumnName;
    }

    public String getDefaultValue() {
        return this.mDefaultValue;
    }

    public String getFieldPath() {
        return this.mFieldPath;
    }

    public boolean isNonNull() {
        return this.mNonNull;
    }

    public FieldBundle(String str, String str2, String str3, boolean z, String str4) {
        this.mFieldPath = str;
        this.mColumnName = str2;
        this.mAffinity = str3;
        this.mNonNull = z;
        this.mDefaultValue = str4;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(FieldBundle fieldBundle) {
        if (this.mNonNull != fieldBundle.mNonNull) {
            return false;
        }
        String str = this.mColumnName;
        if (str == null ? fieldBundle.mColumnName != null : !str.equals(fieldBundle.mColumnName)) {
            return false;
        }
        String str2 = this.mDefaultValue;
        if (str2 == null ? fieldBundle.mDefaultValue != null : !str2.equals(fieldBundle.mDefaultValue)) {
            return false;
        }
        String str3 = this.mAffinity;
        String str4 = fieldBundle.mAffinity;
        return str3 != null ? str3.equals(str4) : str4 == null;
    }
}
