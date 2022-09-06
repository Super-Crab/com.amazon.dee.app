package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Supplier;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
/* loaded from: classes2.dex */
public class RetainingDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final Set<RetainingDataSource> mDataSources = Collections.newSetFromMap(new WeakHashMap());
    @Nullable
    private Supplier<DataSource<T>> mCurrentDataSourceSupplier = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RetainingDataSource<T> extends AbstractDataSource<T> {
        @GuardedBy("RetainingDataSource.this")
        @Nullable
        private DataSource<T> mDataSource;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public class InternalDataSubscriber implements DataSubscriber<T> {
            private InternalDataSubscriber() {
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onCancellation(DataSource<T> dataSource) {
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onFailure(DataSource<T> dataSource) {
                RetainingDataSource.this.onDataSourceFailed();
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    RetainingDataSource.this.onDataSourceNewResult(dataSource);
                } else if (!dataSource.isFinished()) {
                } else {
                    RetainingDataSource.this.onDataSourceFailed();
                }
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                RetainingDataSource.this.onDatasourceProgress(dataSource);
            }
        }

        private RetainingDataSource() {
            this.mDataSource = null;
        }

        private static <T> void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceFailed() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceNewResult(DataSource<T> dataSource) {
            if (dataSource == this.mDataSource) {
                setResult(null, false, dataSource.getExtras());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDatasourceProgress(DataSource<T> dataSource) {
            if (dataSource == this.mDataSource) {
                setProgress(dataSource.getProgress());
            }
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public boolean close() {
            synchronized (this) {
                if (!super.close()) {
                    return false;
                }
                DataSource<T> dataSource = this.mDataSource;
                this.mDataSource = null;
                closeSafely(dataSource);
                return true;
            }
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        @Nullable
        /* renamed from: getResult */
        public synchronized T mo6898getResult() {
            return this.mDataSource != null ? this.mDataSource.mo6898getResult() : null;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public boolean hasMultipleResults() {
            return true;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public synchronized boolean hasResult() {
            boolean z;
            if (this.mDataSource != null) {
                if (this.mDataSource.hasResult()) {
                    z = true;
                }
            }
            z = false;
            return z;
        }

        public void setSupplier(@Nullable Supplier<DataSource<T>> supplier) {
            if (isClosed()) {
                return;
            }
            DataSource<T> mo6895get = supplier != null ? supplier.mo6895get() : null;
            synchronized (this) {
                if (isClosed()) {
                    closeSafely(mo6895get);
                    return;
                }
                DataSource<T> dataSource = this.mDataSource;
                this.mDataSource = mo6895get;
                if (mo6895get != null) {
                    mo6895get.subscribe(new InternalDataSubscriber(), CallerThreadExecutor.getInstance());
                }
                closeSafely(dataSource);
            }
        }
    }

    public void replaceSupplier(Supplier<DataSource<T>> supplier) {
        this.mCurrentDataSourceSupplier = supplier;
        for (RetainingDataSource retainingDataSource : this.mDataSources) {
            if (!retainingDataSource.isClosed()) {
                retainingDataSource.setSupplier(supplier);
            }
        }
    }

    @Override // com.facebook.common.internal.Supplier
    /* renamed from: get */
    public DataSource<T> mo6895get() {
        RetainingDataSource retainingDataSource = new RetainingDataSource();
        retainingDataSource.setSupplier(this.mCurrentDataSourceSupplier);
        this.mDataSources.add(retainingDataSource);
        return retainingDataSource;
    }
}
