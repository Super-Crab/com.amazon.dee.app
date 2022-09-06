package androidx.room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class QueryInterceptorDatabase implements SupportSQLiteDatabase {
    private final SupportSQLiteDatabase mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueryInterceptorDatabase(@NonNull SupportSQLiteDatabase supportSQLiteDatabase, @NonNull RoomDatabase.QueryCallback queryCallback, @NonNull Executor queryCallbackExecutor) {
        this.mDelegate = supportSQLiteDatabase;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = queryCallbackExecutor;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransaction() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$Z0OvI2-Z3VdTh6bv2YiJeJHMtfU
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$beginTransaction$0$QueryInterceptorDatabase();
            }
        });
        this.mDelegate.beginTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionNonExclusive() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$JeYODuh_dQ67pcyPd7guitIWMEQ
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$beginTransactionNonExclusive$1$QueryInterceptorDatabase();
            }
        });
        this.mDelegate.beginTransactionNonExclusive();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionWithListener(@NonNull SQLiteTransactionListener transactionListener) {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$vOm7OPNexrxUEKMVpoGUI8X-XPQ
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$beginTransactionWithListener$2$QueryInterceptorDatabase();
            }
        });
        this.mDelegate.beginTransactionWithListener(transactionListener);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionWithListenerNonExclusive(@NonNull SQLiteTransactionListener transactionListener) {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$r1-mp2ViNyx6ZyQY-L6CqqUtPwQ
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$beginTransactionWithListenerNonExclusive$3$QueryInterceptorDatabase();
            }
        });
        this.mDelegate.beginTransactionWithListenerNonExclusive(transactionListener);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mDelegate.close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public SupportSQLiteStatement compileStatement(@NonNull String sql) {
        return new QueryInterceptorStatement(this.mDelegate.compileStatement(sql), this.mQueryCallback, sql, this.mQueryCallbackExecutor);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int delete(@NonNull String table, @NonNull String whereClause, @NonNull Object[] whereArgs) {
        return this.mDelegate.delete(table, whereClause, whereArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @RequiresApi(api = 16)
    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void endTransaction() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$FfzjNlPeZUqLzQxMHfmjBqizWqA
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$endTransaction$4$QueryInterceptorDatabase();
            }
        });
        this.mDelegate.endTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(@NonNull final String sql) throws SQLException {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$AQmWypxYKMTDEqtcskInY-GWa4w
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$execSQL$10$QueryInterceptorDatabase(sql);
            }
        });
        this.mDelegate.execSQL(sql);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public String getPath() {
        return this.mDelegate.getPath();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long insert(@NonNull String table, int conflictAlgorithm, @NonNull ContentValues values) throws SQLException {
        return this.mDelegate.insert(table, conflictAlgorithm, values);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @RequiresApi(api = 16)
    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
    }

    public /* synthetic */ void lambda$beginTransaction$0$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    public /* synthetic */ void lambda$beginTransactionNonExclusive$1$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    public /* synthetic */ void lambda$beginTransactionWithListener$2$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    public /* synthetic */ void lambda$beginTransactionWithListenerNonExclusive$3$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    public /* synthetic */ void lambda$endTransaction$4$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("END TRANSACTION", Collections.emptyList());
    }

    public /* synthetic */ void lambda$execSQL$10$QueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, new ArrayList(0));
    }

    public /* synthetic */ void lambda$execSQL$11$QueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    public /* synthetic */ void lambda$query$6$QueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, Collections.emptyList());
    }

    public /* synthetic */ void lambda$query$7$QueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    public /* synthetic */ void lambda$query$8$QueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    public /* synthetic */ void lambda$query$9$QueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    public /* synthetic */ void lambda$setTransactionSuccessful$5$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("TRANSACTION SUCCESSFUL", Collections.emptyList());
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean needUpgrade(int newVersion) {
        return this.mDelegate.needUpgrade(newVersion);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public Cursor query(@NonNull final String query) {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$9efGQ7F9Dl7IUef2bt0_L2GloSA
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$query$6$QueryInterceptorDatabase(query);
            }
        });
        return this.mDelegate.query(query);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @RequiresApi(api = 16)
    public void setForeignKeyConstraintsEnabled(boolean enable) {
        this.mDelegate.setForeignKeyConstraintsEnabled(enable);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setLocale(@NonNull Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setMaxSqlCacheSize(int cacheSize) {
        this.mDelegate.setMaxSqlCacheSize(cacheSize);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long setMaximumSize(long numBytes) {
        return this.mDelegate.setMaximumSize(numBytes);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setPageSize(long numBytes) {
        this.mDelegate.setPageSize(numBytes);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setTransactionSuccessful() {
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$h-iFizAxeNml6DHj3f4BZwoQ3tA
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$setTransactionSuccessful$5$QueryInterceptorDatabase();
            }
        });
        this.mDelegate.setTransactionSuccessful();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setVersion(int version) {
        this.mDelegate.setVersion(version);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int update(@NonNull String table, int conflictAlgorithm, @NonNull ContentValues values, @NonNull String whereClause, @NonNull Object[] whereArgs) {
        return this.mDelegate.update(table, conflictAlgorithm, values, whereClause, whereArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) {
        return this.mDelegate.yieldIfContendedSafely(sleepAfterYieldDelay);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(@NonNull final String sql, @NonNull Object[] bindArgs) throws SQLException {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(bindArgs));
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$EAiDr4WGdUirbl0E2iQBRCgF3iE
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$execSQL$11$QueryInterceptorDatabase(sql, arrayList);
            }
        });
        this.mDelegate.execSQL(sql, arrayList.toArray());
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public Cursor query(@NonNull final String query, @NonNull Object[] bindArgs) {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(bindArgs));
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$mSirGVbzmuLwFcgBUOPgCPEAPgo
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$query$7$QueryInterceptorDatabase(query, arrayList);
            }
        });
        return this.mDelegate.query(query, bindArgs);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public Cursor query(@NonNull final SupportSQLiteQuery query) {
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        query.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$COOB8iArYNI30QreNCpToZEujsg
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$query$8$QueryInterceptorDatabase(query, queryInterceptorProgram);
            }
        });
        return this.mDelegate.query(query);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    @NonNull
    public Cursor query(@NonNull final SupportSQLiteQuery query, @NonNull CancellationSignal cancellationSignal) {
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        query.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.-$$Lambda$QueryInterceptorDatabase$3clm1M6-oq_3_2_5HK3EY2KR8mU
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.this.lambda$query$9$QueryInterceptorDatabase(query, queryInterceptorProgram);
            }
        });
        return this.mDelegate.query(query);
    }
}
