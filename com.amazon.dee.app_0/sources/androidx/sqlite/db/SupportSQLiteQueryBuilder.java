package androidx.sqlite.db;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class SupportSQLiteQueryBuilder {
    private static final Pattern sLimitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    private Object[] mBindArgs;
    private String mSelection;
    private final String mTable;
    private boolean mDistinct = false;
    private String[] mColumns = null;
    private String mGroupBy = null;
    private String mHaving = null;
    private String mOrderBy = null;
    private String mLimit = null;

    private SupportSQLiteQueryBuilder(String str) {
        this.mTable = str;
    }

    private static void appendClause(StringBuilder sb, String str, String str2) {
        if (!isEmpty(str2)) {
            sb.append(str);
            sb.append(str2);
        }
    }

    private static void appendColumns(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(Chars.SPACE);
    }

    public static SupportSQLiteQueryBuilder builder(String str) {
        return new SupportSQLiteQueryBuilder(str);
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public SupportSQLiteQueryBuilder columns(String[] strArr) {
        this.mColumns = strArr;
        return this;
    }

    public SupportSQLiteQuery create() {
        if (isEmpty(this.mGroupBy) && !isEmpty(this.mHaving)) {
            throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
        }
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(120, "SELECT ");
        if (this.mDistinct) {
            outline105.append("DISTINCT ");
        }
        String[] strArr = this.mColumns;
        if (strArr != null && strArr.length != 0) {
            appendColumns(outline105, strArr);
        } else {
            outline105.append(" * ");
        }
        outline105.append(" FROM ");
        outline105.append(this.mTable);
        appendClause(outline105, " WHERE ", this.mSelection);
        appendClause(outline105, " GROUP BY ", this.mGroupBy);
        appendClause(outline105, " HAVING ", this.mHaving);
        appendClause(outline105, " ORDER BY ", this.mOrderBy);
        appendClause(outline105, " LIMIT ", this.mLimit);
        return new SimpleSQLiteQuery(outline105.toString(), this.mBindArgs);
    }

    public SupportSQLiteQueryBuilder distinct() {
        this.mDistinct = true;
        return this;
    }

    public SupportSQLiteQueryBuilder groupBy(String str) {
        this.mGroupBy = str;
        return this;
    }

    public SupportSQLiteQueryBuilder having(String str) {
        this.mHaving = str;
        return this;
    }

    public SupportSQLiteQueryBuilder limit(String str) {
        if (!isEmpty(str) && !sLimitPattern.matcher(str).matches()) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid LIMIT clauses:", str));
        }
        this.mLimit = str;
        return this;
    }

    public SupportSQLiteQueryBuilder orderBy(String str) {
        this.mOrderBy = str;
        return this;
    }

    public SupportSQLiteQueryBuilder selection(String str, Object[] objArr) {
        this.mSelection = str;
        this.mBindArgs = objArr;
        return this;
    }
}
