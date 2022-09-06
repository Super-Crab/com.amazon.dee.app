package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class FtsEntityBundle extends EntityBundle {
    private static final String[] SHADOW_TABLE_NAME_SUFFIXES = {"_content", "_segdir", "_segments", "_stat", "_docsize"};
    @SerializedName("contentSyncTriggers")
    private final List<String> mContentSyncSqlTriggers;
    @SerializedName("ftsOptions")
    private final FtsOptionsBundle mFtsOptions;
    @SerializedName("ftsVersion")
    private final String mFtsVersion;

    public FtsEntityBundle(String str, String str2, List<FieldBundle> list, PrimaryKeyBundle primaryKeyBundle, String str3, FtsOptionsBundle ftsOptionsBundle, List<String> list2) {
        super(str, str2, list, primaryKeyBundle, Collections.emptyList(), Collections.emptyList());
        this.mFtsVersion = str3;
        this.mFtsOptions = ftsOptionsBundle;
        this.mContentSyncSqlTriggers = list2;
    }

    @Override // androidx.room.migration.bundle.EntityBundle
    public Collection<String> buildCreateQueries() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(createTable());
        arrayList.addAll(this.mContentSyncSqlTriggers);
        return arrayList;
    }

    public FtsOptionsBundle getFtsOptions() {
        return this.mFtsOptions;
    }

    public List<String> getShadowTableNames() {
        String[] strArr;
        ArrayList arrayList = new ArrayList(SHADOW_TABLE_NAME_SUFFIXES.length);
        for (String str : SHADOW_TABLE_NAME_SUFFIXES) {
            arrayList.add(getTableName() + str);
        }
        return arrayList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.room.migration.bundle.EntityBundle, androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(EntityBundle entityBundle) {
        boolean isSchemaEqual = super.isSchemaEqual(entityBundle);
        if (entityBundle instanceof FtsEntityBundle) {
            FtsEntityBundle ftsEntityBundle = (FtsEntityBundle) entityBundle;
            return isSchemaEqual && this.mFtsVersion.equals(ftsEntityBundle.mFtsVersion) && SchemaEqualityUtil.checkSchemaEquality(this.mFtsOptions, ftsEntityBundle.mFtsOptions);
        }
        return isSchemaEqual;
    }
}
