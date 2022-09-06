package com.facebook.datasource;

import com.facebook.common.internal.Supplier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DataSources {

    /* loaded from: classes2.dex */
    private static class ValueHolder<T> {
        @Nullable
        public T value;

        private ValueHolder() {
            this.value = null;
        }
    }

    private DataSources() {
    }

    public static <T> Supplier<DataSource<T>> getFailedDataSourceSupplier(final Throwable failure) {
        return new Supplier<DataSource<T>>() { // from class: com.facebook.datasource.DataSources.1
            @Override // com.facebook.common.internal.Supplier
            /* renamed from: get */
            public DataSource<T> mo6895get() {
                return DataSources.immediateFailedDataSource(failure);
            }
        };
    }

    public static <T> DataSource<T> immediateDataSource(T result) {
        SimpleDataSource create = SimpleDataSource.create();
        create.setResult(result);
        return create;
    }

    public static <T> DataSource<T> immediateFailedDataSource(Throwable failure) {
        SimpleDataSource create = SimpleDataSource.create();
        create.setFailure(failure);
        return create;
    }

    @Nullable
    public static <T> T waitForFinalResult(DataSource<T> dataSource) throws Throwable {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ValueHolder valueHolder = new ValueHolder();
        final ValueHolder valueHolder2 = new ValueHolder();
        dataSource.subscribe(new DataSubscriber<T>() { // from class: com.facebook.datasource.DataSources.2
            @Override // com.facebook.datasource.DataSubscriber
            public void onCancellation(DataSource<T> dataSource2) {
                countDownLatch.countDown();
            }

            /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Throwable, T] */
            @Override // com.facebook.datasource.DataSubscriber
            public void onFailure(DataSource<T> dataSource2) {
                try {
                    valueHolder2.value = dataSource2.getFailureCause();
                } finally {
                    countDownLatch.countDown();
                }
            }

            /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.Object] */
            @Override // com.facebook.datasource.DataSubscriber
            public void onNewResult(DataSource<T> dataSource2) {
                if (!dataSource2.isFinished()) {
                    return;
                }
                try {
                    ValueHolder.this.value = dataSource2.mo6898getResult();
                } finally {
                    countDownLatch.countDown();
                }
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource2) {
            }
        }, new Executor() { // from class: com.facebook.datasource.DataSources.3
            @Override // java.util.concurrent.Executor
            public void execute(Runnable command) {
                command.run();
            }
        });
        countDownLatch.await();
        T t = valueHolder2.value;
        if (t == null) {
            return valueHolder.value;
        }
        throw ((Throwable) t);
    }
}
