package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ForeignKeyBundle implements SchemaEquality<ForeignKeyBundle> {
    @SerializedName("columns")
    private List<String> mColumns;
    @SerializedName("onDelete")
    private String mOnDelete;
    @SerializedName("onUpdate")
    private String mOnUpdate;
    @SerializedName("referencedColumns")
    private List<String> mReferencedColumns;
    @SerializedName("table")
    private String mTable;

    public ForeignKeyBundle(String str, String str2, String str3, List<String> list, List<String> list2) {
        this.mTable = str;
        this.mOnDelete = str2;
        this.mOnUpdate = str3;
        this.mColumns = list;
        this.mReferencedColumns = list2;
    }

    public List<String> getColumns() {
        return this.mColumns;
    }

    public String getOnDelete() {
        return this.mOnDelete;
    }

    public String getOnUpdate() {
        return this.mOnUpdate;
    }

    public List<String> getReferencedColumns() {
        return this.mReferencedColumns;
    }

    public String getTable() {
        return this.mTable;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(ForeignKeyBundle foreignKeyBundle) {
        String str = this.mTable;
        if (str == null ? foreignKeyBundle.mTable == null : str.equals(foreignKeyBundle.mTable)) {
            String str2 = this.mOnDelete;
            if (str2 == null ? foreignKeyBundle.mOnDelete != null : !str2.equals(foreignKeyBundle.mOnDelete)) {
                return false;
            }
            String str3 = this.mOnUpdate;
            if (str3 == null ? foreignKeyBundle.mOnUpdate != null : !str3.equals(foreignKeyBundle.mOnUpdate)) {
                return false;
            }
            return this.mColumns.equals(foreignKeyBundle.mColumns) && this.mReferencedColumns.equals(foreignKeyBundle.mReferencedColumns);
        }
        return false;
    }
}
