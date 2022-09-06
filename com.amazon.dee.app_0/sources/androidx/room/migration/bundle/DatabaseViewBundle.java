package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class DatabaseViewBundle implements SchemaEquality<DatabaseViewBundle> {
    @SerializedName("createSql")
    private String mCreateSql;
    @SerializedName("viewName")
    private String mViewName;

    public DatabaseViewBundle(String str, String str2) {
        this.mViewName = str;
        this.mCreateSql = str2;
    }

    public String createView() {
        return BundleUtil.replaceViewName(this.mCreateSql, getViewName());
    }

    public String getCreateSql() {
        return this.mCreateSql;
    }

    public String getViewName() {
        return this.mViewName;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(DatabaseViewBundle databaseViewBundle) {
        String str;
        String str2 = this.mViewName;
        return str2 != null && str2.equals(databaseViewBundle.mViewName) && (str = this.mCreateSql) != null && str.equals(databaseViewBundle.mCreateSql);
    }
}
