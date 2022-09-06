package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.AbstractDataSource;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public final class SettableDataSource<T> extends AbstractDataSource<CloseableReference<T>> {
    private SettableDataSource() {
    }

    public static <V> SettableDataSource<V> create() {
        return new SettableDataSource<>();
    }

    @Override // com.facebook.datasource.AbstractDataSource
    protected /* bridge */ /* synthetic */ void closeResult(@Nullable Object result) {
        closeResult((CloseableReference) ((CloseableReference) result));
    }

    public boolean set(@Nullable CloseableReference<T> valueRef) {
        return super.setResult(CloseableReference.cloneOrNull(valueRef), true, null);
    }

    public boolean setException(Throwable throwable) {
        return super.setFailure(throwable);
    }

    @Override // com.facebook.datasource.AbstractDataSource
    public boolean setProgress(float progress) {
        return super.setProgress(progress);
    }

    protected void closeResult(@Nullable CloseableReference<T> result) {
        CloseableReference.closeSafely((CloseableReference<?>) result);
    }

    @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
    @Nullable
    /* renamed from: getResult */
    public CloseableReference<T> mo6898getResult() {
        return CloseableReference.cloneOrNull((CloseableReference) super.mo6898getResult());
    }
}
