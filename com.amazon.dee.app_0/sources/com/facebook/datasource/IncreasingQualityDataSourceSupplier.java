package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class IncreasingQualityDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final boolean mDataSourceLazy;
    private final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadSafe
    /* loaded from: classes2.dex */
    public class IncreasingQualityDataSource extends AbstractDataSource<T> {
        @GuardedBy("IncreasingQualityDataSource.this")
        @Nullable
        private ArrayList<DataSource<T>> mDataSources;
        @Nullable
        private Throwable mDelayedError;
        @Nullable
        private Map<String, Object> mDelayedExtras;
        private AtomicInteger mFinishedDataSources;
        @GuardedBy("IncreasingQualityDataSource.this")
        private int mIndexOfDataSourceWithResult;
        private int mNumberOfDataSources;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public class InternalDataSubscriber implements DataSubscriber<T> {
            private int mIndex;

            public InternalDataSubscriber(int index) {
                this.mIndex = index;
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onCancellation(DataSource<T> dataSource) {
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onFailure(DataSource<T> dataSource) {
                IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    IncreasingQualityDataSource.this.onDataSourceNewResult(this.mIndex, dataSource);
                } else if (!dataSource.isFinished()) {
                } else {
                    IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
                }
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                if (this.mIndex == 0) {
                    IncreasingQualityDataSource.this.setProgress(dataSource.getProgress());
                }
            }
        }

        public IncreasingQualityDataSource() {
            if (!IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
        }

        private void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        private void ensureDataSourceInitialized() {
            if (this.mFinishedDataSources != null) {
                return;
            }
            synchronized (this) {
                if (this.mFinishedDataSources == null) {
                    this.mFinishedDataSources = new AtomicInteger(0);
                    int size = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.size();
                    this.mNumberOfDataSources = size;
                    this.mIndexOfDataSourceWithResult = size;
                    this.mDataSources = new ArrayList<>(size);
                    for (int i = 0; i < size; i++) {
                        DataSource<T> dataSource = (DataSource) ((Supplier) IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.get(i)).mo6895get();
                        this.mDataSources.add(dataSource);
                        dataSource.subscribe(new InternalDataSubscriber(i), CallerThreadExecutor.getInstance());
                        if (dataSource.hasResult()) {
                            break;
                        }
                    }
                }
            }
        }

        @Nullable
        private synchronized DataSource<T> getAndClearDataSource(int i) {
            DataSource<T> dataSource;
            dataSource = null;
            if (this.mDataSources != null && i < this.mDataSources.size()) {
                dataSource = this.mDataSources.set(i, null);
            }
            return dataSource;
        }

        @Nullable
        private synchronized DataSource<T> getDataSource(int i) {
            return (this.mDataSources == null || i >= this.mDataSources.size()) ? null : this.mDataSources.get(i);
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return getDataSource(this.mIndexOfDataSourceWithResult);
        }

        private void maybeSetFailure() {
            Throwable th;
            if (this.mFinishedDataSources.incrementAndGet() != this.mNumberOfDataSources || (th = this.mDelayedError) == null) {
                return;
            }
            setFailure(th, this.mDelayedExtras);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0024 A[LOOP:0: B:17:0x0022->B:18:0x0024, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void maybeSetIndexOfDataSourceWithResult(int r4, com.facebook.datasource.DataSource<T> r5, boolean r6) {
            /*
                r3 = this;
                monitor-enter(r3)
                int r0 = r3.mIndexOfDataSourceWithResult     // Catch: java.lang.Throwable -> L31
                int r1 = r3.mIndexOfDataSourceWithResult     // Catch: java.lang.Throwable -> L31
                com.facebook.datasource.DataSource r2 = r3.getDataSource(r4)     // Catch: java.lang.Throwable -> L31
                if (r5 != r2) goto L2f
                int r5 = r3.mIndexOfDataSourceWithResult     // Catch: java.lang.Throwable -> L31
                if (r4 != r5) goto L10
                goto L2f
            L10:
                com.facebook.datasource.DataSource r5 = r3.getDataSourceWithResult()     // Catch: java.lang.Throwable -> L31
                if (r5 == 0) goto L1f
                if (r6 == 0) goto L1d
                int r5 = r3.mIndexOfDataSourceWithResult     // Catch: java.lang.Throwable -> L31
                if (r4 >= r5) goto L1d
                goto L1f
            L1d:
                r4 = r1
                goto L21
            L1f:
                r3.mIndexOfDataSourceWithResult = r4     // Catch: java.lang.Throwable -> L31
            L21:
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L31
            L22:
                if (r0 <= r4) goto L2e
                com.facebook.datasource.DataSource r5 = r3.getAndClearDataSource(r0)
                r3.closeSafely(r5)
                int r0 = r0 + (-1)
                goto L22
            L2e:
                return
            L2f:
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L31
                return
            L31:
                r4 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L31
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.IncreasingQualityDataSourceSupplier.IncreasingQualityDataSource.maybeSetIndexOfDataSourceWithResult(int, com.facebook.datasource.DataSource, boolean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceFailed(int index, DataSource<T> dataSource) {
            closeSafely(tryGetAndClearDataSource(index, dataSource));
            if (index == 0) {
                this.mDelayedError = dataSource.getFailureCause();
                this.mDelayedExtras = dataSource.getExtras();
            }
            maybeSetFailure();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceNewResult(int index, DataSource<T> dataSource) {
            maybeSetIndexOfDataSourceWithResult(index, dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult(null, index == 0 && dataSource.isFinished(), dataSource.getExtras());
            }
            maybeSetFailure();
        }

        @Nullable
        private synchronized DataSource<T> tryGetAndClearDataSource(int i, DataSource<T> dataSource) {
            if (dataSource == getDataSourceWithResult()) {
                return null;
            }
            if (dataSource != getDataSource(i)) {
                return dataSource;
            }
            return getAndClearDataSource(i);
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public boolean close() {
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            synchronized (this) {
                if (!super.close()) {
                    return false;
                }
                ArrayList<DataSource<T>> arrayList = this.mDataSources;
                this.mDataSources = null;
                if (arrayList == null) {
                    return true;
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    closeSafely(arrayList.get(i));
                }
                return true;
            }
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        @Nullable
        /* renamed from: getResult */
        public synchronized T mo6898getResult() {
            DataSource<T> dataSourceWithResult;
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.mo6898getResult() : null;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public synchronized boolean hasResult() {
            boolean z;
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            DataSource<T> dataSourceWithResult = getDataSourceWithResult();
            if (dataSourceWithResult != null) {
                if (dataSourceWithResult.hasResult()) {
                    z = true;
                }
            }
            z = false;
            return z;
        }
    }

    private IncreasingQualityDataSourceSupplier(List<Supplier<DataSource<T>>> dataSourceSuppliers, boolean dataSourceLazy) {
        Preconditions.checkArgument(!dataSourceSuppliers.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = dataSourceSuppliers;
        this.mDataSourceLazy = dataSourceLazy;
    }

    public static <T> IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> dataSourceSuppliers) {
        return create(dataSourceSuppliers, false);
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof IncreasingQualityDataSourceSupplier) {
            return Objects.equal(this.mDataSourceSuppliers, ((IncreasingQualityDataSourceSupplier) other).mDataSourceSuppliers);
        }
        return false;
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("list", this.mDataSourceSuppliers).toString();
    }

    public static <T> IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> dataSourceSuppliers, boolean dataSourceLazy) {
        return new IncreasingQualityDataSourceSupplier<>(dataSourceSuppliers, dataSourceLazy);
    }

    @Override // com.facebook.common.internal.Supplier
    /* renamed from: get */
    public DataSource<T> mo6895get() {
        return new IncreasingQualityDataSource();
    }
}
