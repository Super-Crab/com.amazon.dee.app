package androidx.room.util;

import android.database.Cursor;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class FtsTableInfo {
    private static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set<String> columns;
    public final String name;
    public final Set<String> options;

    public FtsTableInfo(String name, Set<String> columns, Set<String> options) {
        this.name = name;
        this.columns = columns;
        this.options = options;
    }

    @VisibleForTesting
    static Set<String> parseOptions(String createStatement) {
        if (createStatement.isEmpty()) {
            return new HashSet();
        }
        String substring = createStatement.substring(createStatement.indexOf(40) + 1, createStatement.lastIndexOf(41));
        ArrayList<String> arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        int i = -1;
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt != '\"' && charAt != '\'') {
                if (charAt != ',') {
                    if (charAt != '[') {
                        if (charAt != ']') {
                            if (charAt != '`') {
                            }
                        } else if (!arrayDeque.isEmpty() && ((Character) arrayDeque.peek()).charValue() == '[') {
                            arrayDeque.pop();
                        }
                    } else if (arrayDeque.isEmpty()) {
                        arrayDeque.push(Character.valueOf(charAt));
                    }
                } else if (arrayDeque.isEmpty()) {
                    arrayList.add(substring.substring(i + 1, i2).trim());
                    i = i2;
                }
            }
            if (arrayDeque.isEmpty()) {
                arrayDeque.push(Character.valueOf(charAt));
            } else if (((Character) arrayDeque.peek()).charValue() == charAt) {
                arrayDeque.pop();
            }
        }
        arrayList.add(substring.substring(i + 1).trim());
        HashSet hashSet = new HashSet();
        for (String str : arrayList) {
            for (String str2 : FTS_OPTIONS) {
                if (str.startsWith(str2)) {
                    hashSet.add(str);
                }
            }
        }
        return hashSet;
    }

    public static FtsTableInfo read(SupportSQLiteDatabase database, String tableName) {
        return new FtsTableInfo(tableName, readColumns(database, tableName), readOptions(database, tableName));
    }

    private static Set<String> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor query = database.query("PRAGMA table_info(`" + tableName + "`)");
        HashSet hashSet = new HashSet();
        try {
            if (query.getColumnCount() > 0) {
                int columnIndex = query.getColumnIndex("name");
                while (query.moveToNext()) {
                    hashSet.add(query.getString(columnIndex));
                }
            }
            return hashSet;
        } finally {
            query.close();
        }
    }

    private static Set<String> readOptions(SupportSQLiteDatabase database, String tableName) {
        Cursor query = database.query("SELECT * FROM sqlite_master WHERE `name` = '" + tableName + "'");
        try {
            String string = query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("sql")) : "";
            query.close();
            return parseOptions(string);
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FtsTableInfo)) {
            return false;
        }
        FtsTableInfo ftsTableInfo = (FtsTableInfo) o;
        String str = this.name;
        if (str == null ? ftsTableInfo.name != null : !str.equals(ftsTableInfo.name)) {
            return false;
        }
        Set<String> set = this.columns;
        if (set == null ? ftsTableInfo.columns != null : !set.equals(ftsTableInfo.columns)) {
            return false;
        }
        Set<String> set2 = this.options;
        Set<String> set3 = ftsTableInfo.options;
        return set2 != null ? set2.equals(set3) : set3 == null;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Set<String> set = this.columns;
        int hashCode2 = (hashCode + (set != null ? set.hashCode() : 0)) * 31;
        Set<String> set2 = this.options;
        if (set2 != null) {
            i = set2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FtsTableInfo{name='");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", columns=");
        outline107.append(this.columns);
        outline107.append(", options=");
        outline107.append(this.options);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public FtsTableInfo(String name, Set<String> columns, String createSql) {
        this.name = name;
        this.columns = columns;
        this.options = parseOptions(createSql);
    }
}
