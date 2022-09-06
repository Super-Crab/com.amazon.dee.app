package com.amazon.photos.discovery.internal.worker;

import android.database.Cursor;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.util.RetryableOperationUtil;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b \u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\u0003H$J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0002\u0010\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bH$R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/CursorItemSource;", "Lcom/amazon/photos/discovery/internal/worker/ItemSource;", "lastAddedRowId", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(JLcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "cursor", "Landroid/database/Cursor;", "getCursor", "()Landroid/database/Cursor;", "cursor$delegate", "Lkotlin/Lazy;", "cursorDelegate", "Lkotlin/Lazy;", "close", "", "createCursor", "createItem", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "getItemCount", "", "()Ljava/lang/Integer;", "getNextItem", "itemFromCursor", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class CursorItemSource implements ItemSource {
    private final Lazy cursor$delegate;
    private final Lazy<Cursor> cursorDelegate;
    private final Metrics metrics;

    public CursorItemSource(long j, @NotNull Metrics metrics) {
        Lazy<Cursor> lazy;
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.metrics = metrics;
        lazy = LazyKt__LazyJVMKt.lazy(new CursorItemSource$cursorDelegate$1(this, j));
        this.cursorDelegate = lazy;
        this.cursor$delegate = this.cursorDelegate;
    }

    private final MutableLocalItem createItem(final Cursor cursor) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = null;
        RetryableOperationUtil.INSTANCE.runWithRetry(3, new RetryableOperationUtil.RetryableOperation() { // from class: com.amazon.photos.discovery.internal.worker.CursorItemSource$createItem$1
            @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
            public void finalOperation(boolean z, int i) {
                Metrics metrics;
                Metrics metrics2;
                Metrics metrics3;
                if (z) {
                    if (i >= 2) {
                        return;
                    }
                    metrics = CursorItemSource.this.metrics;
                    metrics.recordSimpleEvent(ConstantsKt.SCAN_ADDED_WORKER_UTIL, DiscoveryMetrics.GetCursorItemSucceedWithException, MetricRecordingType.STANDARD);
                    return;
                }
                Exception exc = (Exception) objectRef2.element;
                if (exc != null) {
                    metrics3 = CursorItemSource.this.metrics;
                    metrics3.recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER_UTIL, DiscoveryMetrics.GetCursorItemFailed, exc);
                    return;
                }
                metrics2 = CursorItemSource.this.metrics;
                metrics2.recordSimpleEvent(ConstantsKt.SCAN_ADDED_WORKER_UTIL, DiscoveryMetrics.GetCursorItemFailed, MetricRecordingType.STANDARD);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
            public void handleException(@Nullable Exception exc, int i) {
                objectRef2.element = exc;
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [com.amazon.photos.discovery.internal.model.MutableLocalItem, T] */
            @Override // com.amazon.photos.discovery.internal.util.RetryableOperationUtil.RetryableOperation
            public void run() {
                objectRef.element = CursorItemSource.this.itemFromCursor(cursor);
            }
        });
        return (MutableLocalItem) objectRef.element;
    }

    private final Cursor getCursor() {
        return (Cursor) this.cursor$delegate.getValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Cursor cursor;
        if (!this.cursorDelegate.isInitialized() || (cursor = getCursor()) == null) {
            return;
        }
        cursor.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public abstract Cursor createCursor(long j);

    @Override // com.amazon.photos.discovery.internal.worker.ItemSource
    @Nullable
    public Integer getItemCount() {
        Cursor cursor = getCursor();
        if (cursor != null) {
            return Integer.valueOf(cursor.getCount());
        }
        return null;
    }

    @Override // com.amazon.photos.discovery.internal.worker.ItemSource
    @Nullable
    public MutableLocalItem getNextItem() {
        Cursor cursor;
        Cursor cursor2 = getCursor();
        if (cursor2 == null || !cursor2.moveToNext() || (cursor = getCursor()) == null) {
            return null;
        }
        return createItem(cursor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public abstract MutableLocalItem itemFromCursor(@NotNull Cursor cursor);
}
