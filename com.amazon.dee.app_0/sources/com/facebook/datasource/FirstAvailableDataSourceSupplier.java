package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class FirstAvailableDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadSafe
    /* loaded from: classes2.dex */
    public class FirstAvailableDataSource extends AbstractDataSource<T> {
        private int mIndex = 0;
        @Nullable
        private DataSource<T> mCurrentDataSource = null;
        @Nullable
        private DataSource<T> mDataSourceWithResult = null;

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
                FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    FirstAvailableDataSource.this.onDataSourceNewResult(dataSource);
                } else if (!dataSource.isFinished()) {
                } else {
                    FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
                }
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                FirstAvailableDataSource.this.setProgress(Math.max(FirstAvailableDataSource.this.getProgress(), dataSource.getProgress()));
            }
        }

        public FirstAvailableDataSource() {
            if (!startNextDataSource()) {
                setFailure(new RuntimeException("No data source supplier or supplier returned null."));
            }
        }

        private synchronized boolean clearCurrentDataSource(DataSource<T> dataSource) {
            if (!isClosed() && dataSource == this.mCurrentDataSource) {
                this.mCurrentDataSource = null;
                return true;
            }
            return false;
        }

        private void closeSafely(@Nullable DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return this.mDataSourceWithResult;
        }

        @Nullable
        private synchronized Supplier<DataSource<T>> getNextSupplier() {
            if (isClosed() || this.mIndex >= FirstAvailableDataSourceSupplier.this.mDataSourceSuppliers.size()) {
                return null;
            }
            List list = FirstAvailableDataSourceSupplier.this.mDataSourceSuppliers;
            int i = this.mIndex;
            this.mIndex = i + 1;
            return (Supplier) list.get(i);
        }

        private void maybeSetDataSourceWithResult(DataSource<T> dataSource, boolean isFinished) {
            DataSource<T> dataSource2;
            synchronized (this) {
                if (dataSource == this.mCurrentDataSource && dataSource != this.mDataSourceWithResult) {
                    if (this.mDataSourceWithResult != null && !isFinished) {
                        dataSource2 = null;
                        closeSafely(dataSource2);
                    }
                    DataSource<T> dataSource3 = this.mDataSourceWithResult;
                    this.mDataSourceWithResult = dataSource;
                    dataSource2 = dataSource3;
                    closeSafely(dataSource2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceFailed(DataSource<T> dataSource) {
            if (!clearCurrentDataSource(dataSource)) {
                return;
            }
            if (dataSource != getDataSourceWithResult()) {
                closeSafely(dataSource);
            }
            if (startNextDataSource()) {
                return;
            }
            setFailure(dataSource.getFailureCause(), dataSource.getExtras());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceNewResult(DataSource<T> dataSource) {
            maybeSetDataSourceWithResult(dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult(null, dataSource.isFinished(), dataSource.getExtras());
            }
        }

        private synchronized boolean setCurrentDataSource(DataSource<T> dataSource) {
            if (isClosed()) {
                return false;
            }
            this.mCurrentDataSource = dataSource;
            return true;
        }

        private boolean startNextDataSource() {
            Supplier<DataSource<T>> nextSupplier = getNextSupplier();
            DataSource<T> mo6895get = nextSupplier != null ? nextSupplier.mo6895get() : null;
            if (setCurrentDataSource(mo6895get) && mo6895get != null) {
                mo6895get.subscribe(new InternalDataSubscriber(), CallerThreadExecutor.getInstance());
                return true;
            }
            closeSafely(mo6895get);
            return false;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public boolean close() {
            synchronized (this) {
                if (!super.close()) {
                    return false;
                }
                DataSource<T> dataSource = this.mCurrentDataSource;
                this.mCurrentDataSource = null;
                DataSource<T> dataSource2 = this.mDataSourceWithResult;
                this.mDataSourceWithResult = null;
                closeSafely(dataSource2);
                closeSafely(dataSource);
                return true;
            }
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        @Nullable
        /* renamed from: getResult */
        public synchronized T mo6898getResult() {
            DataSource<T> dataSourceWithResult;
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.mo6898getResult() : null;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public synchronized boolean hasResult() {
            boolean z;
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

    private FirstAvailableDataSourceSupplier(List<Supplier<DataSource<T>>> dataSourceSuppliers) {
        Preconditions.checkArgument(!dataSourceSuppliers.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = dataSourceSuppliers;
    }

    public static <T> FirstAvailableDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> dataSourceSuppliers) {
        return new FirstAvailableDataSourceSupplier<>(dataSourceSuppliers);
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof FirstAvailableDataSourceSupplier) {
            return Objects.equal(this.mDataSourceSuppliers, ((FirstAvailableDataSourceSupplier) other).mDataSourceSuppliers);
        }
        return false;
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("list", this.mDataSourceSuppliers).toString();
    }

    @Override // com.facebook.common.internal.Supplier
    /* renamed from: get */
    public DataSource<T> mo6895get() {
        return new FirstAvailableDataSource();
    }
}
