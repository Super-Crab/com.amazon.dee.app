package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class EntityBundle implements SchemaEquality<EntityBundle> {
    static final String NEW_TABLE_PREFIX = "_new_";
    @SerializedName("createSql")
    private final String mCreateSql;
    @SerializedName("fields")
    private final List<FieldBundle> mFields;
    private transient Map<String, FieldBundle> mFieldsByColumnName;
    @SerializedName("foreignKeys")
    private final List<ForeignKeyBundle> mForeignKeys;
    @SerializedName("indices")
    private final List<IndexBundle> mIndices;
    private transient String mNewTableName;
    @SerializedName("primaryKey")
    private final PrimaryKeyBundle mPrimaryKey;
    @SerializedName("tableName")
    private final String mTableName;

    public EntityBundle(String str, String str2, List<FieldBundle> list, PrimaryKeyBundle primaryKeyBundle, List<IndexBundle> list2, List<ForeignKeyBundle> list3) {
        this.mTableName = str;
        this.mCreateSql = str2;
        this.mFields = list;
        this.mPrimaryKey = primaryKeyBundle;
        this.mIndices = list2;
        this.mForeignKeys = list3;
    }

    public Collection<String> buildCreateQueries() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(createTable());
        for (IndexBundle indexBundle : this.mIndices) {
            arrayList.add(indexBundle.create(getTableName()));
        }
        return arrayList;
    }

    public String createNewTable() {
        return BundleUtil.replaceTableName(this.mCreateSql, getNewTableName());
    }

    public String createTable() {
        return BundleUtil.replaceTableName(this.mCreateSql, getTableName());
    }

    public String getCreateSql() {
        return this.mCreateSql;
    }

    public List<FieldBundle> getFields() {
        return this.mFields;
    }

    public Map<String, FieldBundle> getFieldsByColumnName() {
        if (this.mFieldsByColumnName == null) {
            this.mFieldsByColumnName = new HashMap();
            for (FieldBundle fieldBundle : this.mFields) {
                this.mFieldsByColumnName.put(fieldBundle.getColumnName(), fieldBundle);
            }
        }
        return this.mFieldsByColumnName;
    }

    public List<ForeignKeyBundle> getForeignKeys() {
        return this.mForeignKeys;
    }

    public List<IndexBundle> getIndices() {
        return this.mIndices;
    }

    public String getNewTableName() {
        if (this.mNewTableName == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(NEW_TABLE_PREFIX);
            outline107.append(this.mTableName);
            this.mNewTableName = outline107.toString();
        }
        return this.mNewTableName;
    }

    public PrimaryKeyBundle getPrimaryKey() {
        return this.mPrimaryKey;
    }

    public String getTableName() {
        return this.mTableName;
    }

    @NotNull
    public String renameToOriginal() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ALTER TABLE ");
        outline107.append(getNewTableName());
        outline107.append(" RENAME TO ");
        outline107.append(getTableName());
        return outline107.toString();
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(EntityBundle entityBundle) {
        return this.mTableName.equals(entityBundle.mTableName) && SchemaEqualityUtil.checkSchemaEquality(getFieldsByColumnName(), entityBundle.getFieldsByColumnName()) && SchemaEqualityUtil.checkSchemaEquality(this.mPrimaryKey, entityBundle.mPrimaryKey) && SchemaEqualityUtil.checkSchemaEquality(this.mIndices, entityBundle.mIndices) && SchemaEqualityUtil.checkSchemaEquality(this.mForeignKeys, entityBundle.mForeignKeys);
    }
}
