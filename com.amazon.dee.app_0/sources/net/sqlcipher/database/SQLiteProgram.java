package net.sqlcipher.database;

import com.amazon.alexa.redesign.utils.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public abstract class SQLiteProgram extends SQLiteClosable {
    private static final String TAG = "SQLiteProgram";
    boolean mClosed = false;
    private SQLiteCompiledSql mCompiledSql;
    @Deprecated
    protected SQLiteDatabase mDatabase;
    final String mSql;
    @Deprecated
    protected long nHandle;
    @Deprecated
    protected long nStatement;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteProgram(SQLiteDatabase sQLiteDatabase, String str) {
        this.nHandle = 0L;
        this.nStatement = 0L;
        this.mDatabase = sQLiteDatabase;
        this.mSql = str.trim();
        sQLiteDatabase.acquireReference();
        sQLiteDatabase.addSQLiteClosable(this);
        this.nHandle = sQLiteDatabase.mNativeHandle;
        String substring = this.mSql.length() >= 6 ? this.mSql.substring(0, 6) : this.mSql;
        if (!substring.equalsIgnoreCase("INSERT") && !substring.equalsIgnoreCase("UPDATE") && !substring.equalsIgnoreCase("REPLAC") && !substring.equalsIgnoreCase(Constants.REQUEST_METHOD_DELETE) && !substring.equalsIgnoreCase("SELECT")) {
            this.mCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            this.nStatement = this.mCompiledSql.nStatement;
            return;
        }
        this.mCompiledSql = sQLiteDatabase.getCompiledStatementForSql(str);
        SQLiteCompiledSql sQLiteCompiledSql = this.mCompiledSql;
        if (sQLiteCompiledSql == null) {
            this.mCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            this.mCompiledSql.acquire();
            sQLiteDatabase.addToCompiledQueries(str, this.mCompiledSql);
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Created DbObj (id#");
                outline107.append(this.mCompiledSql.nStatement);
                outline107.append(") for sql: ");
                outline107.append(str);
                outline107.toString();
            }
        } else if (!sQLiteCompiledSql.acquire()) {
            long j = this.mCompiledSql.nStatement;
            this.mCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("** possible bug ** Created NEW DbObj (id#");
                outline1072.append(this.mCompiledSql.nStatement);
                outline1072.append(") because the previously created DbObj (id#");
                outline1072.append(j);
                outline1072.append(") was not released for sql:");
                outline1072.append(str);
                outline1072.toString();
            }
        }
        this.nStatement = this.mCompiledSql.nStatement;
    }

    private final native void native_clear_bindings();

    private void releaseCompiledSqlIfNotInCache() {
        if (this.mCompiledSql == null) {
            return;
        }
        synchronized (this.mDatabase.mCompiledQueries) {
            if (!this.mDatabase.mCompiledQueries.containsValue(this.mCompiledSql)) {
                this.mCompiledSql.releaseSqlStatement();
                this.mCompiledSql = null;
                this.nStatement = 0L;
            } else {
                this.mCompiledSql.release();
            }
        }
    }

    public void bindBlob(int i, byte[] bArr) {
        if (bArr != null) {
            if (!this.mClosed) {
                if (this.mDatabase.isOpen()) {
                    acquireReference();
                    try {
                        native_bind_blob(i, bArr);
                        return;
                    } finally {
                        releaseReference();
                    }
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
                outline107.append(this.mDatabase.getPath());
                outline107.append(" already closed");
                throw new IllegalStateException(outline107.toString());
            }
            throw new IllegalStateException("program already closed");
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("the bind value at index ", i, " is null"));
    }

    public void bindDouble(int i, double d) {
        if (!this.mClosed) {
            if (this.mDatabase.isOpen()) {
                acquireReference();
                try {
                    native_bind_double(i, d);
                    return;
                } finally {
                    releaseReference();
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
            outline107.append(this.mDatabase.getPath());
            outline107.append(" already closed");
            throw new IllegalStateException(outline107.toString());
        }
        throw new IllegalStateException("program already closed");
    }

    public void bindLong(int i, long j) {
        if (!this.mClosed) {
            if (this.mDatabase.isOpen()) {
                acquireReference();
                try {
                    native_bind_long(i, j);
                    return;
                } finally {
                    releaseReference();
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
            outline107.append(this.mDatabase.getPath());
            outline107.append(" already closed");
            throw new IllegalStateException(outline107.toString());
        }
        throw new IllegalStateException("program already closed");
    }

    public void bindNull(int i) {
        if (!this.mClosed) {
            if (this.mDatabase.isOpen()) {
                acquireReference();
                try {
                    native_bind_null(i);
                    return;
                } finally {
                    releaseReference();
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
            outline107.append(this.mDatabase.getPath());
            outline107.append(" already closed");
            throw new IllegalStateException(outline107.toString());
        }
        throw new IllegalStateException("program already closed");
    }

    public void bindString(int i, String str) {
        if (str != null) {
            if (!this.mClosed) {
                if (this.mDatabase.isOpen()) {
                    acquireReference();
                    try {
                        native_bind_string(i, str);
                        return;
                    } finally {
                        releaseReference();
                    }
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
                outline107.append(this.mDatabase.getPath());
                outline107.append(" already closed");
                throw new IllegalStateException(outline107.toString());
            }
            throw new IllegalStateException("program already closed");
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("the bind value at index ", i, " is null"));
    }

    public void clearBindings() {
        if (!this.mClosed) {
            if (this.mDatabase.isOpen()) {
                acquireReference();
                try {
                    native_clear_bindings();
                    return;
                } finally {
                    releaseReference();
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
            outline107.append(this.mDatabase.getPath());
            outline107.append(" already closed");
            throw new IllegalStateException(outline107.toString());
        }
        throw new IllegalStateException("program already closed");
    }

    public void close() {
        if (!this.mClosed && this.mDatabase.isOpen()) {
            this.mDatabase.lock();
            try {
                releaseReference();
                this.mDatabase.unlock();
                this.mClosed = true;
            } catch (Throwable th) {
                this.mDatabase.unlock();
                throw th;
            }
        }
    }

    @Deprecated
    protected void compile(String str, boolean z) {
    }

    String getSqlString() {
        return this.mSql;
    }

    public final long getUniqueId() {
        return this.nStatement;
    }

    protected final native void native_bind_blob(int i, byte[] bArr);

    protected final native void native_bind_double(int i, double d);

    protected final native void native_bind_long(int i, long j);

    protected final native void native_bind_null(int i);

    protected final native void native_bind_string(int i, String str);

    @Deprecated
    protected final native void native_compile(String str);

    @Deprecated
    protected final native void native_finalize();

    @Override // net.sqlcipher.database.SQLiteClosable
    protected void onAllReferencesReleased() {
        releaseCompiledSqlIfNotInCache();
        this.mDatabase.releaseReference();
        this.mDatabase.removeSQLiteClosable(this);
    }

    @Override // net.sqlcipher.database.SQLiteClosable
    protected void onAllReferencesReleasedFromContainer() {
        releaseCompiledSqlIfNotInCache();
        this.mDatabase.releaseReference();
    }
}
