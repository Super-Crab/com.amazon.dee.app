package androidx.room.util;

import android.database.Cursor;
import androidx.annotation.RestrictTo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class ViewInfo {
    public final String name;
    public final String sql;

    public ViewInfo(String name, String sql) {
        this.name = name;
        this.sql = sql;
    }

    public static ViewInfo read(SupportSQLiteDatabase database, String viewName) {
        Cursor query = database.query("SELECT name, sql FROM sqlite_master WHERE type = 'view' AND name = '" + viewName + "'");
        try {
            if (query.moveToFirst()) {
                return new ViewInfo(query.getString(0), query.getString(1));
            }
            return new ViewInfo(viewName, null);
        } finally {
            query.close();
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewInfo)) {
            return false;
        }
        ViewInfo viewInfo = (ViewInfo) o;
        String str = this.name;
        if (str == null ? viewInfo.name == null : str.equals(viewInfo.name)) {
            String str2 = this.sql;
            if (str2 != null) {
                if (str2.equals(viewInfo.sql)) {
                    return true;
                }
            } else if (viewInfo.sql == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sql;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ViewInfo{name='");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", sql='");
        return GeneratedOutlineSupport1.outline90(outline107, this.sql, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
