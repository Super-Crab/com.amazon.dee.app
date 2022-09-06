package com.amazon.alexa.accessory.persistence;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class DatabaseQuery implements Predicate<Set<String>>, Function<Set<String>, DatabaseQuery> {
    private final String[] columns;
    private final SQLiteDatabase database;
    private final SQLiteOpenHelper helper;
    private final String selection;
    private final String[] selectionArgs;
    private final String sql;
    private final String[] tables;

    public DatabaseQuery(SQLiteOpenHelper sQLiteOpenHelper, String[] strArr, String[] strArr2, String str, String[] strArr3) {
        Preconditions.notNull(sQLiteOpenHelper, "helper");
        Preconditions.notNull(strArr, "tables");
        Preconditions.notNull(strArr2, "columns");
        this.helper = sQLiteOpenHelper;
        this.tables = strArr;
        this.columns = strArr2;
        this.selection = str;
        this.selectionArgs = strArr3;
        this.database = sQLiteOpenHelper.getReadableDatabase();
        this.sql = SQLiteQueryBuilder.buildQueryString(false, getTablesSql(), strArr2, str, null, null, null, null);
    }

    private String getTablesSql() {
        String[] strArr;
        String[] strArr2 = this.tables;
        if (strArr2.length == 1) {
            return strArr2[0];
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.tables) {
            if (sb.length() == 0) {
                sb.append(str);
            } else {
                sb.append(", ");
                sb.append(str);
            }
        }
        return sb.toString();
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public DatabaseQuery mo10358apply(@NonNull Set<String> set) throws Exception {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DatabaseQuery.class != obj.getClass()) {
            return false;
        }
        DatabaseQuery databaseQuery = (DatabaseQuery) obj;
        if (!this.helper.equals(databaseQuery.helper) || !Arrays.equals(this.tables, databaseQuery.tables) || !Arrays.equals(this.columns, databaseQuery.columns)) {
            return false;
        }
        String str = this.selection;
        if (str == null ? databaseQuery.selection != null : !str.equals(databaseQuery.selection)) {
            return false;
        }
        return Arrays.equals(this.selectionArgs, databaseQuery.selectionArgs);
    }

    public int hashCode() {
        int hashCode = ((Arrays.hashCode(this.tables) * 31) + Arrays.hashCode(this.columns)) * 31;
        String str = this.selection;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 31) + Arrays.hashCode(this.selectionArgs);
    }

    public Cursor run() {
        if (Logger.shouldLog(Logger.Level.VERBOSE)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DatabaseQuery.run: query = [");
            outline107.append(this.sql);
            outline107.append("]  selectionArgs = [");
            outline107.append(Arrays.toString(this.selectionArgs));
            outline107.append("]");
            Logger.d(outline107.toString());
            Cursor rawQuery = this.database.rawQuery(this.sql, this.selectionArgs);
            try {
                if (rawQuery.getCount() == 0) {
                    Logger.v("DatabaseQuery.run: >>>>> No rows");
                } else {
                    Logger.v("DatabaseQuery.run: " + DatabaseUtils.dumpCursorToString(rawQuery));
                }
                rawQuery.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        return this.database.rawQuery(this.sql, this.selectionArgs);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DatabaseQuery{tables=");
        outline107.append(Arrays.toString(this.tables));
        outline107.append(", columns=");
        outline107.append(Arrays.toString(this.columns));
        outline107.append(", selection='");
        GeneratedOutlineSupport1.outline176(outline107, this.selection, Chars.QUOTE, ", selectionArgs=");
        outline107.append(Arrays.toString(this.selectionArgs));
        outline107.append(", sql='");
        return GeneratedOutlineSupport1.outline90(outline107, this.sql, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public boolean test(@NonNull Set<String> set) throws Exception {
        for (String str : this.tables) {
            if (set.contains(str)) {
                return true;
            }
        }
        return false;
    }

    public DatabaseQuery(SQLiteOpenHelper sQLiteOpenHelper, String str, String[] strArr, String str2, String[] strArr2) {
        this(sQLiteOpenHelper, new String[]{str}, strArr, str2, strArr2);
    }

    public DatabaseQuery(SQLiteOpenHelper sQLiteOpenHelper, String[] strArr, String[] strArr2) {
        this(sQLiteOpenHelper, strArr, strArr2, (String) null, (String[]) null);
    }

    public DatabaseQuery(SQLiteOpenHelper sQLiteOpenHelper, String str, String[] strArr) {
        this(sQLiteOpenHelper, new String[]{str}, strArr, (String) null, (String[]) null);
    }

    public DatabaseQuery(SQLiteOpenHelper sQLiteOpenHelper, String str, List<String> list, String str2, String[] strArr) {
        this(sQLiteOpenHelper, new String[]{str}, (String[]) list.toArray(), str2, strArr);
    }

    public DatabaseQuery(SQLiteOpenHelper sQLiteOpenHelper, String str, List<String> list) {
        this(sQLiteOpenHelper, new String[]{str}, (String[]) list.toArray(), (String) null, (String[]) null);
    }
}
