package com.facebook.react.modules.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
@ReactModule(name = AsyncStorageModule.NAME)
/* loaded from: classes2.dex */
public final class AsyncStorageModule extends NativeAsyncSQLiteDBStorageSpec implements ModuleDataCleaner.Cleanable {
    private static final int MAX_SQL_KEYS = 999;
    public static final String NAME = "AsyncSQLiteDBStorage";
    private final SerialExecutor executor;
    private ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown;

    /* loaded from: classes2.dex */
    private class SerialExecutor implements Executor {
        private final Executor executor;
        private Runnable mActive;
        private final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();

        SerialExecutor(Executor executor) {
            this.executor = executor;
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(final Runnable runnable) {
            this.mTasks.offer(new Runnable() { // from class: com.facebook.react.modules.storage.AsyncStorageModule.SerialExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        SerialExecutor.this.scheduleNext();
                    }
                }
            });
            if (this.mActive == null) {
                scheduleNext();
            }
        }

        synchronized void scheduleNext() {
            Runnable poll = this.mTasks.poll();
            this.mActive = poll;
            if (poll != null) {
                this.executor.execute(this.mActive);
            }
        }
    }

    public AsyncStorageModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.ensureDatabase();
    }

    @Override // com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                if (AsyncStorageModule.this.mReactDatabaseSupplier.ensureDatabase()) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.clear();
                        callback.invoke(new Object[0]);
                        return;
                    } catch (Exception e) {
                        FLog.w(ReactConstants.TAG, e.getMessage(), e);
                        callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()));
                        return;
                    }
                }
                callback.invoke(AsyncStorageErrorUtil.getDBError(null));
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @Override // com.facebook.react.modules.common.ModuleDataCleaner.Cleanable
    public void clearSensitiveData() {
        this.mReactDatabaseSupplier.clearAndCloseDatabase();
    }

    @Override // com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
                    return;
                }
                WritableArray createArray = Arguments.createArray();
                Cursor query = AsyncStorageModule.this.mReactDatabaseSupplier.get().query("catalystLocalStorage", new String[]{"key"}, null, null, null, null, null);
                try {
                    try {
                        if (query.moveToFirst()) {
                            do {
                                createArray.pushString(query.getString(0));
                            } while (query.moveToNext());
                            query.close();
                            callback.invoke(null, createArray);
                        }
                        query.close();
                        callback.invoke(null, createArray);
                    } catch (Exception e) {
                        FLog.w(ReactConstants.TAG, e.getMessage(), e);
                        callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()), null);
                        query.close();
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    @Override // com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null), null);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Removed duplicated region for block: B:23:0x00c2 A[LOOP:3: B:21:0x00bc->B:23:0x00c2, LOOP_END] */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void doInBackgroundGuarded(java.lang.Void... r20) {
                    /*
                        Method dump skipped, instructions count: 275
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.AsyncStorageModule.AnonymousClass1.doInBackgroundGuarded(java.lang.Void[]):void");
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                try {
                    if (AsyncStorageModule.this.ensureDatabase()) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i = 0; i < readableArray.size(); i++) {
                                if (readableArray.mo6943getArray(i).size() != 2) {
                                    WritableMap invalidValueError = AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e) {
                                        FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                        if (invalidValueError != null) {
                                            return;
                                        }
                                        AsyncStorageErrorUtil.getError(null, e.getMessage());
                                        return;
                                    }
                                } else if (readableArray.mo6943getArray(i).getString(0) == null) {
                                    WritableMap invalidKeyError = AsyncStorageErrorUtil.getInvalidKeyError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e2) {
                                        FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                        if (invalidKeyError != null) {
                                            return;
                                        }
                                        AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                        return;
                                    }
                                } else if (readableArray.mo6943getArray(i).getString(1) == null) {
                                    WritableMap invalidValueError2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e3) {
                                        FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                        if (invalidValueError2 != null) {
                                            return;
                                        }
                                        AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                        return;
                                    }
                                } else if (!AsyncLocalStorageUtil.mergeImpl(AsyncStorageModule.this.mReactDatabaseSupplier.get(), readableArray.mo6943getArray(i).getString(0), readableArray.mo6943getArray(i).getString(1))) {
                                    WritableMap dBError = AsyncStorageErrorUtil.getDBError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e4) {
                                        FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                                        if (dBError != null) {
                                            return;
                                        }
                                        AsyncStorageErrorUtil.getError(null, e4.getMessage());
                                        return;
                                    }
                                }
                            }
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e5) {
                                FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                                writableMap = AsyncStorageErrorUtil.getError(null, e5.getMessage());
                            }
                        } catch (Exception e6) {
                            FLog.w(ReactConstants.TAG, e6.getMessage(), e6);
                            WritableMap error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e7) {
                                FLog.w(ReactConstants.TAG, e7.getMessage(), e7);
                                if (error == null) {
                                    writableMap = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                                }
                            }
                            writableMap = error;
                        }
                        if (writableMap != null) {
                            callback.invoke(writableMap);
                            return;
                        } else {
                            callback.invoke(new Object[0]);
                            return;
                        }
                    }
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e8) {
                        FLog.w(ReactConstants.TAG, e8.getMessage(), e8);
                        AsyncStorageErrorUtil.getError(null, e8.getMessage());
                    }
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @Override // com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void... voidArr) {
                    WritableMap writableMap = null;
                    try {
                        if (AsyncStorageModule.this.ensureDatabase()) {
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                                for (int i = 0; i < readableArray.size(); i += 999) {
                                    int min = Math.min(readableArray.size() - i, 999);
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().delete("catalystLocalStorage", AsyncLocalStorageUtil.buildKeySelection(min), AsyncLocalStorageUtil.buildKeySelectionArgs(readableArray, i, min));
                                }
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e) {
                                    FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                    writableMap = AsyncStorageErrorUtil.getError(null, e.getMessage());
                                }
                            } catch (Exception e2) {
                                FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                WritableMap error = AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e3) {
                                    FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                    if (error == null) {
                                        writableMap = AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                    }
                                }
                                writableMap = error;
                            }
                            if (writableMap != null) {
                                callback.invoke(writableMap);
                                return;
                            } else {
                                callback.invoke(new Object[0]);
                                return;
                            }
                        }
                        callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    } catch (Throwable th) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e4) {
                            FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                            AsyncStorageErrorUtil.getError(null, e4.getMessage());
                        }
                        throw th;
                    }
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAsyncSQLiteDBStorageSpec
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void... voidArr) {
                    WritableMap writableMap = null;
                    if (AsyncStorageModule.this.ensureDatabase()) {
                        SQLiteStatement compileStatement = AsyncStorageModule.this.mReactDatabaseSupplier.get().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                        try {
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                                for (int i = 0; i < readableArray.size(); i++) {
                                    if (readableArray.mo6943getArray(i).size() != 2) {
                                        WritableMap invalidValueError = AsyncStorageErrorUtil.getInvalidValueError(null);
                                        try {
                                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                            return;
                                        } catch (Exception e) {
                                            FLog.w(ReactConstants.TAG, e.getMessage(), e);
                                            if (invalidValueError != null) {
                                                return;
                                            }
                                            AsyncStorageErrorUtil.getError(null, e.getMessage());
                                            return;
                                        }
                                    } else if (readableArray.mo6943getArray(i).getString(0) == null) {
                                        WritableMap invalidKeyError = AsyncStorageErrorUtil.getInvalidKeyError(null);
                                        try {
                                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                            return;
                                        } catch (Exception e2) {
                                            FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                            if (invalidKeyError != null) {
                                                return;
                                            }
                                            AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                            return;
                                        }
                                    } else if (readableArray.mo6943getArray(i).getString(1) == null) {
                                        WritableMap invalidValueError2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                                        try {
                                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                            return;
                                        } catch (Exception e3) {
                                            FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                            if (invalidValueError2 != null) {
                                                return;
                                            }
                                            AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                            return;
                                        }
                                    } else {
                                        compileStatement.clearBindings();
                                        compileStatement.bindString(1, readableArray.mo6943getArray(i).getString(0));
                                        compileStatement.bindString(2, readableArray.mo6943getArray(i).getString(1));
                                        compileStatement.execute();
                                    }
                                }
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e4) {
                                    FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                                    writableMap = AsyncStorageErrorUtil.getError(null, e4.getMessage());
                                }
                            } catch (Throwable th) {
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e5) {
                                    FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                                    AsyncStorageErrorUtil.getError(null, e5.getMessage());
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            FLog.w(ReactConstants.TAG, e6.getMessage(), e6);
                            WritableMap error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e7) {
                                FLog.w(ReactConstants.TAG, e7.getMessage(), e7);
                                if (error == null) {
                                    writableMap = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                                }
                            }
                            writableMap = error;
                        }
                        if (writableMap != null) {
                            callback.invoke(writableMap);
                            return;
                        } else {
                            callback.invoke(new Object[0]);
                            return;
                        }
                    }
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    @VisibleForTesting
    AsyncStorageModule(ReactApplicationContext reactApplicationContext, Executor executor) {
        super(reactApplicationContext);
        this.mShuttingDown = false;
        this.executor = new SerialExecutor(executor);
        this.mReactDatabaseSupplier = ReactDatabaseSupplier.getInstance(reactApplicationContext);
    }
}
