package net.sqlcipher.database;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class SQLiteCompiledSql {
    private static final String TAG = "SQLiteCompiledSql";
    SQLiteDatabase mDatabase;
    private String mSqlStmt;
    private Throwable mStackTrace;
    long nHandle;
    long nStatement = 0;
    private boolean mInUse = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteCompiledSql(SQLiteDatabase sQLiteDatabase, String str) {
        this.nHandle = 0L;
        this.mSqlStmt = null;
        this.mStackTrace = null;
        if (sQLiteDatabase.isOpen()) {
            this.mDatabase = sQLiteDatabase;
            this.mSqlStmt = str;
            this.mStackTrace = new DatabaseObjectNotClosedException().fillInStackTrace();
            this.nHandle = sQLiteDatabase.mNativeHandle;
            compile(str, true);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
        outline107.append(sQLiteDatabase.getPath());
        outline107.append(" already closed");
        throw new IllegalStateException(outline107.toString());
    }

    private void compile(String str, boolean z) {
        if (!this.mDatabase.isOpen()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("database ");
            outline107.append(this.mDatabase.getPath());
            outline107.append(" already closed");
            throw new IllegalStateException(outline107.toString());
        } else if (!z) {
        } else {
            this.mDatabase.lock();
            try {
                native_compile(str);
            } finally {
                this.mDatabase.unlock();
            }
        }
    }

    private final native void native_compile(String str);

    private final native void native_finalize();

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean acquire() {
        if (this.mInUse) {
            return false;
        }
        this.mInUse = true;
        if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
            String str = "Acquired DbObj (id#" + this.nStatement + ") from DB cache";
        }
        return true;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.nStatement == 0) {
                return;
            }
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                String str = "** warning ** Finalized DbObj (id#" + this.nStatement + ")";
            }
            int length = this.mSqlStmt.length();
            StringBuilder sb = new StringBuilder();
            sb.append("Releasing statement in a finalizer. Please ensure that you explicitly call close() on your cursor: ");
            String str2 = this.mSqlStmt;
            if (length > 100) {
                length = 100;
            }
            sb.append(str2.substring(0, length));
            Log.w(TAG, sb.toString(), this.mStackTrace);
            releaseSqlStatement();
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void release() {
        if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
            String str = "Released DbObj (id#" + this.nStatement + ") back to DB cache";
        }
        this.mInUse = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseSqlStatement() {
        if (this.nStatement != 0) {
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("closed and deallocated DbObj (id#");
                outline107.append(this.nStatement);
                outline107.append(")");
                outline107.toString();
            }
            try {
                this.mDatabase.lock();
                native_finalize();
                this.nStatement = 0L;
            } finally {
                this.mDatabase.unlock();
            }
        }
    }
}
