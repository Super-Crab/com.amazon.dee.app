package androidx.room.util;

import android.database.Cursor;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.amazon.deecomms.common.Constants;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class TableInfo {
    public static final int CREATED_FROM_DATABASE = 2;
    public static final int CREATED_FROM_ENTITY = 1;
    public static final int CREATED_FROM_UNKNOWN = 0;
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    @Nullable
    public final Set<Index> indices;
    public final String name;

    /* loaded from: classes.dex */
    public static final class Column {
        @ColumnInfo.SQLiteTypeAffinity
        public final int affinity;
        public final String defaultValue;
        private final int mCreatedFrom;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        @Deprecated
        public Column(String name, String type, boolean notNull, int primaryKeyPosition) {
            this(name, type, notNull, primaryKeyPosition, null, 0);
        }

        @ColumnInfo.SQLiteTypeAffinity
        private static int findAffinity(@Nullable String type) {
            if (type == null) {
                return 5;
            }
            String upperCase = type.toUpperCase(Locale.US);
            if (upperCase.contains("INT")) {
                return 3;
            }
            if (upperCase.contains("CHAR") || upperCase.contains("CLOB") || upperCase.contains(Constants.Calling.MEDIA_STREAM_TYPE_TEXT)) {
                return 2;
            }
            if (upperCase.contains("BLOB")) {
                return 5;
            }
            return (upperCase.contains("REAL") || upperCase.contains("FLOA") || upperCase.contains("DOUB")) ? 4 : 1;
        }

        public boolean equals(Object o) {
            String str;
            String str2;
            String str3;
            if (this == o) {
                return true;
            }
            if (!(o instanceof Column)) {
                return false;
            }
            Column column = (Column) o;
            int i = Build.VERSION.SDK_INT;
            if (this.primaryKeyPosition != column.primaryKeyPosition || !this.name.equals(column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.mCreatedFrom == 1 && column.mCreatedFrom == 2 && (str3 = this.defaultValue) != null && !str3.equals(column.defaultValue)) {
                return false;
            }
            if (this.mCreatedFrom == 2 && column.mCreatedFrom == 1 && (str2 = column.defaultValue) != null && !str2.equals(this.defaultValue)) {
                return false;
            }
            int i2 = this.mCreatedFrom;
            return (i2 == 0 || i2 != column.mCreatedFrom || ((str = this.defaultValue) == null ? column.defaultValue == null : str.equals(column.defaultValue))) && this.affinity == column.affinity;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Column{name='");
            GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", type='");
            GeneratedOutlineSupport1.outline176(outline107, this.type, Chars.QUOTE, ", affinity='");
            outline107.append(this.affinity);
            outline107.append(Chars.QUOTE);
            outline107.append(", notNull=");
            outline107.append(this.notNull);
            outline107.append(", primaryKeyPosition=");
            outline107.append(this.primaryKeyPosition);
            outline107.append(", defaultValue='");
            return GeneratedOutlineSupport1.outline90(outline107, this.defaultValue, Chars.QUOTE, JsonReaderKt.END_OBJ);
        }

        public Column(String name, String type, boolean notNull, int primaryKeyPosition, String defaultValue, int createdFrom) {
            this.name = name;
            this.type = type;
            this.notNull = notNull;
            this.primaryKeyPosition = primaryKeyPosition;
            this.affinity = findAffinity(type);
            this.defaultValue = defaultValue;
            this.mCreatedFrom = createdFrom;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public static final class ForeignKey {
        @NonNull
        public final List<String> columnNames;
        @NonNull
        public final String onDelete;
        @NonNull
        public final String onUpdate;
        @NonNull
        public final List<String> referenceColumnNames;
        @NonNull
        public final String referenceTable;

        public ForeignKey(@NonNull String referenceTable, @NonNull String onDelete, @NonNull String onUpdate, @NonNull List<String> columnNames, @NonNull List<String> referenceColumnNames) {
            this.referenceTable = referenceTable;
            this.onDelete = onDelete;
            this.onUpdate = onUpdate;
            this.columnNames = Collections.unmodifiableList(columnNames);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ForeignKey)) {
                return false;
            }
            ForeignKey foreignKey = (ForeignKey) o;
            if (!this.referenceTable.equals(foreignKey.referenceTable) || !this.onDelete.equals(foreignKey.onDelete) || !this.onUpdate.equals(foreignKey.onUpdate) || !this.columnNames.equals(foreignKey.columnNames)) {
                return false;
            }
            return this.referenceColumnNames.equals(foreignKey.referenceColumnNames);
        }

        public int hashCode() {
            int outline7 = GeneratedOutlineSupport1.outline7(this.onUpdate, GeneratedOutlineSupport1.outline7(this.onDelete, this.referenceTable.hashCode() * 31, 31), 31);
            return this.referenceColumnNames.hashCode() + ((this.columnNames.hashCode() + outline7) * 31);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ForeignKey{referenceTable='");
            GeneratedOutlineSupport1.outline176(outline107, this.referenceTable, Chars.QUOTE, ", onDelete='");
            GeneratedOutlineSupport1.outline176(outline107, this.onDelete, Chars.QUOTE, ", onUpdate='");
            GeneratedOutlineSupport1.outline176(outline107, this.onUpdate, Chars.QUOTE, ", columnNames=");
            outline107.append(this.columnNames);
            outline107.append(", referenceColumnNames=");
            return GeneratedOutlineSupport1.outline94(outline107, this.referenceColumnNames, JsonReaderKt.END_OBJ);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public static class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        final String mFrom;
        final int mId;
        final int mSequence;
        final String mTo;

        ForeignKeyWithSequence(int id, int sequence, String from, String to) {
            this.mId = id;
            this.mSequence = sequence;
            this.mFrom = from;
            this.mTo = to;
        }

        @Override // java.lang.Comparable
        public int compareTo(@NonNull ForeignKeyWithSequence o) {
            int i = this.mId - o.mId;
            return i == 0 ? this.mSequence - o.mSequence : i;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public static final class Index {
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public final boolean unique;

        public Index(String name, boolean unique, List<String> columns) {
            this.name = name;
            this.unique = unique;
            this.columns = columns;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Index)) {
                return false;
            }
            Index index = (Index) o;
            if (this.unique != index.unique || !this.columns.equals(index.columns)) {
                return false;
            }
            if (this.name.startsWith("index_")) {
                return index.name.startsWith("index_");
            }
            return this.name.equals(index.name);
        }

        public int hashCode() {
            int hashCode;
            if (this.name.startsWith("index_")) {
                hashCode = "index_".hashCode();
            } else {
                hashCode = this.name.hashCode();
            }
            return this.columns.hashCode() + (((hashCode * 31) + (this.unique ? 1 : 0)) * 31);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Index{name='");
            GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", unique=");
            outline107.append(this.unique);
            outline107.append(", columns=");
            return GeneratedOutlineSupport1.outline94(outline107, this.columns, JsonReaderKt.END_OBJ);
        }
    }

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys, Set<Index> indices) {
        this.name = name;
        this.columns = Collections.unmodifiableMap(columns);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys);
        this.indices = indices == null ? null : Collections.unmodifiableSet(indices);
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        return new TableInfo(tableName, readColumns(database, tableName), readForeignKeys(database, tableName), readIndices(database, tableName));
    }

    private static Map<String, Column> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor query = database.query("PRAGMA table_info(`" + tableName + "`)");
        HashMap hashMap = new HashMap();
        try {
            if (query.getColumnCount() > 0) {
                int columnIndex = query.getColumnIndex("name");
                int columnIndex2 = query.getColumnIndex("type");
                int columnIndex3 = query.getColumnIndex("notnull");
                int columnIndex4 = query.getColumnIndex("pk");
                int columnIndex5 = query.getColumnIndex("dflt_value");
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    hashMap.put(string, new Column(string, query.getString(columnIndex2), query.getInt(columnIndex3) != 0, query.getInt(columnIndex4), query.getString(columnIndex5), 2));
                }
            }
            return hashMap;
        } finally {
            query.close();
        }
    }

    private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex(ADMConstants.EXTRA_FROM);
        int columnIndex4 = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            arrayList.add(new ForeignKeyWithSequence(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        HashSet hashSet = new HashSet();
        Cursor query = database.query("PRAGMA foreign_key_list(`" + tableName + "`)");
        try {
            int columnIndex = query.getColumnIndex("id");
            int columnIndex2 = query.getColumnIndex("seq");
            int columnIndex3 = query.getColumnIndex("table");
            int columnIndex4 = query.getColumnIndex("on_delete");
            int columnIndex5 = query.getColumnIndex("on_update");
            List<ForeignKeyWithSequence> readForeignKeyFieldMappings = readForeignKeyFieldMappings(query);
            int count = query.getCount();
            for (int i = 0; i < count; i++) {
                query.moveToPosition(i);
                if (query.getInt(columnIndex2) == 0) {
                    int i2 = query.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (ForeignKeyWithSequence foreignKeyWithSequence : readForeignKeyFieldMappings) {
                        if (foreignKeyWithSequence.mId == i2) {
                            arrayList.add(foreignKeyWithSequence.mFrom);
                            arrayList2.add(foreignKeyWithSequence.mTo);
                        }
                    }
                    hashSet.add(new ForeignKey(query.getString(columnIndex3), query.getString(columnIndex4), query.getString(columnIndex5), arrayList, arrayList2));
                }
            }
            return hashSet;
        } finally {
            query.close();
        }
    }

    @Nullable
    private static Index readIndex(SupportSQLiteDatabase database, String name, boolean unique) {
        Cursor query = database.query("PRAGMA index_xinfo(`" + name + "`)");
        try {
            int columnIndex = query.getColumnIndex("seqno");
            int columnIndex2 = query.getColumnIndex("cid");
            int columnIndex3 = query.getColumnIndex("name");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                TreeMap treeMap = new TreeMap();
                while (query.moveToNext()) {
                    if (query.getInt(columnIndex2) >= 0) {
                        int i = query.getInt(columnIndex);
                        treeMap.put(Integer.valueOf(i), query.getString(columnIndex3));
                    }
                }
                ArrayList arrayList = new ArrayList(treeMap.size());
                arrayList.addAll(treeMap.values());
                return new Index(name, unique, arrayList);
            }
            return null;
        } finally {
            query.close();
        }
    }

    @Nullable
    private static Set<Index> readIndices(SupportSQLiteDatabase database, String tableName) {
        Cursor query = database.query("PRAGMA index_list(`" + tableName + "`)");
        try {
            int columnIndex = query.getColumnIndex("name");
            int columnIndex2 = query.getColumnIndex("origin");
            int columnIndex3 = query.getColumnIndex("unique");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                HashSet hashSet = new HashSet();
                while (query.moveToNext()) {
                    if ("c".equals(query.getString(columnIndex2))) {
                        String string = query.getString(columnIndex);
                        boolean z = true;
                        if (query.getInt(columnIndex3) != 1) {
                            z = false;
                        }
                        Index readIndex = readIndex(database, string, z);
                        if (readIndex == null) {
                            return null;
                        }
                        hashSet.add(readIndex);
                    }
                }
                return hashSet;
            }
            return null;
        } finally {
            query.close();
        }
    }

    public boolean equals(Object o) {
        Set<Index> set;
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableInfo)) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) o;
        String str = this.name;
        if (str == null ? tableInfo.name != null : !str.equals(tableInfo.name)) {
            return false;
        }
        Map<String, Column> map = this.columns;
        if (map == null ? tableInfo.columns != null : !map.equals(tableInfo.columns)) {
            return false;
        }
        Set<ForeignKey> set2 = this.foreignKeys;
        if (set2 == null ? tableInfo.foreignKeys != null : !set2.equals(tableInfo.foreignKeys)) {
            return false;
        }
        Set<Index> set3 = this.indices;
        if (set3 != null && (set = tableInfo.indices) != null) {
            return set3.equals(set);
        }
        return true;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, Column> map = this.columns;
        int hashCode2 = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set<ForeignKey> set = this.foreignKeys;
        if (set != null) {
            i = set.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TableInfo{name='");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", columns=");
        outline107.append(this.columns);
        outline107.append(", foreignKeys=");
        outline107.append(this.foreignKeys);
        outline107.append(", indices=");
        outline107.append(this.indices);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys) {
        this(name, columns, foreignKeys, Collections.emptySet());
    }
}
