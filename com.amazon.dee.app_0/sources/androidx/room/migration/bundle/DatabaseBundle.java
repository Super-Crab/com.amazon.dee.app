package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class DatabaseBundle implements SchemaEquality<DatabaseBundle> {
    @SerializedName("entities")
    private List<EntityBundle> mEntities;
    private transient Map<String, EntityBundle> mEntitiesByTableName;
    @SerializedName("identityHash")
    private String mIdentityHash;
    @SerializedName("setupQueries")
    private List<String> mSetupQueries;
    @SerializedName("version")
    private int mVersion;
    @SerializedName("views")
    private List<DatabaseViewBundle> mViews;

    /* loaded from: classes.dex */
    static final class FtsEntityCreateComparator implements Comparator<EntityBundle> {
        FtsEntityCreateComparator() {
        }

        @Override // java.util.Comparator
        public int compare(EntityBundle entityBundle, EntityBundle entityBundle2) {
            return entityBundle instanceof FtsEntityBundle ? ((FtsEntityBundle) entityBundle).getFtsOptions().getContentTable().equals(entityBundle2.getTableName()) ? 1 : 0 : (!(entityBundle2 instanceof FtsEntityBundle) || !((FtsEntityBundle) entityBundle2).getFtsOptions().getContentTable().equals(entityBundle.getTableName())) ? 0 : -1;
        }
    }

    public DatabaseBundle(int i, String str, List<EntityBundle> list, List<DatabaseViewBundle> list2, List<String> list3) {
        this.mVersion = i;
        this.mIdentityHash = str;
        this.mEntities = list;
        this.mViews = list2;
        this.mSetupQueries = list3;
    }

    public List<String> buildCreateQueries() {
        ArrayList arrayList = new ArrayList();
        Collections.sort(this.mEntities, new FtsEntityCreateComparator());
        for (EntityBundle entityBundle : this.mEntities) {
            arrayList.addAll(entityBundle.buildCreateQueries());
        }
        for (DatabaseViewBundle databaseViewBundle : this.mViews) {
            arrayList.add(databaseViewBundle.createView());
        }
        arrayList.addAll(this.mSetupQueries);
        return arrayList;
    }

    public List<EntityBundle> getEntities() {
        return this.mEntities;
    }

    public Map<String, EntityBundle> getEntitiesByTableName() {
        if (this.mEntitiesByTableName == null) {
            this.mEntitiesByTableName = new HashMap();
            for (EntityBundle entityBundle : this.mEntities) {
                this.mEntitiesByTableName.put(entityBundle.getTableName(), entityBundle);
            }
        }
        return this.mEntitiesByTableName;
    }

    public String getIdentityHash() {
        return this.mIdentityHash;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public List<DatabaseViewBundle> getViews() {
        return this.mViews;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(DatabaseBundle databaseBundle) {
        return SchemaEqualityUtil.checkSchemaEquality(getEntitiesByTableName(), databaseBundle.getEntitiesByTableName());
    }

    public DatabaseBundle() {
        this.mViews = Collections.emptyList();
    }
}
