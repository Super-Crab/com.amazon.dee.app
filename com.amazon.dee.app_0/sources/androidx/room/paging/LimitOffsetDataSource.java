package androidx.room.paging;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.paging.PositionalDataSource;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class LimitOffsetDataSource<T> extends PositionalDataSource<T> {
    private final String mCountQuery;
    private final RoomDatabase mDb;
    private final boolean mInTransaction;
    private final String mLimitOffsetQuery;
    private final InvalidationTracker.Observer mObserver;
    private final AtomicBoolean mRegisteredObserver;
    private final RoomSQLiteQuery mSourceQuery;

    protected LimitOffsetDataSource(@NonNull RoomDatabase db, @NonNull SupportSQLiteQuery query, boolean inTransaction, @NonNull String... tables) {
        this(db, RoomSQLiteQuery.copyFrom(query), inTransaction, tables);
    }

    private RoomSQLiteQuery getSQLiteQuery(int startPosition, int loadCount) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
        acquire.copyArgumentsFrom(this.mSourceQuery);
        acquire.bindLong(acquire.getArgCount() - 1, loadCount);
        acquire.bindLong(acquire.getArgCount(), startPosition);
        return acquire;
    }

    private void registerObserverIfNecessary() {
        if (this.mRegisteredObserver.compareAndSet(false, true)) {
            this.mDb.getInvalidationTracker().addWeakObserver(this.mObserver);
        }
    }

    @NonNull
    protected abstract List<T> convertRows(@NonNull Cursor cursor);

    public int countItems() {
        registerObserverIfNecessary();
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
        acquire.copyArgumentsFrom(this.mSourceQuery);
        Cursor query = this.mDb.query(acquire);
        try {
            if (!query.moveToFirst()) {
                return 0;
            }
            return query.getInt(0);
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.paging.DataSource
    public boolean isInvalid() {
        registerObserverIfNecessary();
        this.mDb.getInvalidationTracker().refreshVersionsSync();
        return super.isInvalid();
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams params, @NonNull PositionalDataSource.LoadInitialCallback<T> callback) {
        RoomSQLiteQuery roomSQLiteQuery;
        List<T> list;
        int i;
        registerObserverIfNecessary();
        List<T> emptyList = Collections.emptyList();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            int countItems = countItems();
            if (countItems != 0) {
                i = PositionalDataSource.computeInitialLoadPosition(params, countItems);
                roomSQLiteQuery = getSQLiteQuery(i, PositionalDataSource.computeInitialLoadSize(params, i, countItems));
                try {
                    cursor = this.mDb.query(roomSQLiteQuery);
                    list = convertRows(cursor);
                    this.mDb.setTransactionSuccessful();
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    this.mDb.endTransaction();
                    if (roomSQLiteQuery != null) {
                        roomSQLiteQuery.release();
                    }
                    throw th;
                }
            } else {
                list = emptyList;
                i = 0;
                roomSQLiteQuery = null;
            }
            if (cursor != null) {
                cursor.close();
            }
            this.mDb.endTransaction();
            if (roomSQLiteQuery != null) {
                roomSQLiteQuery.release();
            }
            callback.onResult(list, i, countItems);
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = null;
        }
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams params, @NonNull PositionalDataSource.LoadRangeCallback<T> callback) {
        callback.onResult(loadRange(params.startPosition, params.loadSize));
    }

    protected LimitOffsetDataSource(@NonNull RoomDatabase db, @NonNull SupportSQLiteQuery query, boolean inTransaction, boolean registerObserverImmediately, @NonNull String... tables) {
        this(db, RoomSQLiteQuery.copyFrom(query), inTransaction, registerObserverImmediately, tables);
    }

    @NonNull
    public List<T> loadRange(int startPosition, int loadCount) {
        RoomSQLiteQuery sQLiteQuery = getSQLiteQuery(startPosition, loadCount);
        if (this.mInTransaction) {
            this.mDb.beginTransaction();
            Cursor cursor = null;
            try {
                cursor = this.mDb.query(sQLiteQuery);
                List<T> convertRows = convertRows(cursor);
                this.mDb.setTransactionSuccessful();
                return convertRows;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                this.mDb.endTransaction();
                sQLiteQuery.release();
            }
        }
        Cursor query = this.mDb.query(sQLiteQuery);
        try {
            return convertRows(query);
        } finally {
            query.close();
            sQLiteQuery.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LimitOffsetDataSource(@NonNull RoomDatabase db, @NonNull RoomSQLiteQuery query, boolean inTransaction, @NonNull String... tables) {
        this(db, query, inTransaction, true, tables);
    }

    protected LimitOffsetDataSource(@NonNull RoomDatabase db, @NonNull RoomSQLiteQuery query, boolean inTransaction, boolean registerObserverImmediately, @NonNull String... tables) {
        this.mRegisteredObserver = new AtomicBoolean(false);
        this.mDb = db;
        this.mSourceQuery = query;
        this.mInTransaction = inTransaction;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SELECT COUNT(*) FROM ( ");
        outline107.append(this.mSourceQuery.getSql());
        outline107.append(" )");
        this.mCountQuery = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SELECT * FROM ( ");
        outline1072.append(this.mSourceQuery.getSql());
        outline1072.append(" ) LIMIT ? OFFSET ?");
        this.mLimitOffsetQuery = outline1072.toString();
        this.mObserver = new InvalidationTracker.Observer(tables) { // from class: androidx.room.paging.LimitOffsetDataSource.1
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> tables2) {
                LimitOffsetDataSource.this.invalidate();
            }
        };
        if (registerObserverImmediately) {
            registerObserverIfNecessary();
        }
    }
}
