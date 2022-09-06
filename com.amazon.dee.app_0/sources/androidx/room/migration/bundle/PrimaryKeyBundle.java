package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class PrimaryKeyBundle implements SchemaEquality<PrimaryKeyBundle> {
    @SerializedName("autoGenerate")
    private boolean mAutoGenerate;
    @SerializedName("columnNames")
    private List<String> mColumnNames;

    public PrimaryKeyBundle(boolean z, List<String> list) {
        this.mColumnNames = list;
        this.mAutoGenerate = z;
    }

    public List<String> getColumnNames() {
        return this.mColumnNames;
    }

    public boolean isAutoGenerate() {
        return this.mAutoGenerate;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(PrimaryKeyBundle primaryKeyBundle) {
        return this.mColumnNames.equals(primaryKeyBundle.mColumnNames) && this.mAutoGenerate == primaryKeyBundle.mAutoGenerate;
    }
}
