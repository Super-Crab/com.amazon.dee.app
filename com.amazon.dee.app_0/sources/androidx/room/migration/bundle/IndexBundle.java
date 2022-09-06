package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class IndexBundle implements SchemaEquality<IndexBundle> {
    public static final String DEFAULT_PREFIX = "index_";
    @SerializedName("columnNames")
    private List<String> mColumnNames;
    @SerializedName("createSql")
    private String mCreateSql;
    @SerializedName("name")
    private String mName;
    @SerializedName("unique")
    private boolean mUnique;

    public IndexBundle(String str, boolean z, List<String> list, String str2) {
        this.mName = str;
        this.mUnique = z;
        this.mColumnNames = list;
        this.mCreateSql = str2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public String create(String str) {
        return BundleUtil.replaceTableName(this.mCreateSql, str);
    }

    public List<String> getColumnNames() {
        return this.mColumnNames;
    }

    public String getName() {
        return this.mName;
    }

    public boolean isUnique() {
        return this.mUnique;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(IndexBundle indexBundle) {
        if (this.mUnique != indexBundle.mUnique) {
            return false;
        }
        if (this.mName.startsWith("index_")) {
            if (!indexBundle.mName.startsWith("index_")) {
                return false;
            }
        } else if (indexBundle.mName.startsWith("index_") || !this.mName.equals(indexBundle.mName)) {
            return false;
        }
        List<String> list = this.mColumnNames;
        List<String> list2 = indexBundle.mColumnNames;
        if (list != null) {
            if (list.equals(list2)) {
                return true;
            }
        } else if (list2 == null) {
            return true;
        }
        return false;
    }
}
