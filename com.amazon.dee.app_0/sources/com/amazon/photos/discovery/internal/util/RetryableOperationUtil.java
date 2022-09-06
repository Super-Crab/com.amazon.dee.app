package com.amazon.photos.discovery.internal.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RetryableOperationUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/RetryableOperationUtil;", "", "()V", "runWithRetry", "", "maxAttempts", "", "operation", "Lcom/amazon/photos/discovery/internal/util/RetryableOperationUtil$RetryableOperation;", "RetryableOperation", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RetryableOperationUtil {
    public static final RetryableOperationUtil INSTANCE = new RetryableOperationUtil();

    /* compiled from: RetryableOperationUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\u00042\u000e\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H&¨\u0006\u0010"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/RetryableOperationUtil$RetryableOperation;", "", "()V", "finalOperation", "", "completed", "", "numRetry", "", "handleException", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "postOperation", "preOperation", "run", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static abstract class RetryableOperation {
        public void finalOperation(boolean z, int i) {
        }

        public void handleException(@Nullable Exception exc, int i) {
        }

        public void postOperation(boolean z, int i) {
        }

        public void preOperation() {
        }

        public abstract void run();
    }

    private RetryableOperationUtil() {
    }

    public final void runWithRetry(int i, @NotNull RetryableOperation operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        boolean z = false;
        while (true) {
            if (z) {
                break;
            }
            int i2 = i - 1;
            if (i <= 0) {
                i = i2;
                break;
            }
            operation.preOperation();
            try {
                try {
                    operation.run();
                    operation.postOperation(true, i2);
                    z = true;
                } catch (Exception e) {
                    operation.handleException(e, i2);
                    operation.postOperation(z, i2);
                }
                i = i2;
            } catch (Throwable th) {
                operation.postOperation(z, i2);
                throw th;
            }
        }
        operation.finalOperation(z, i);
    }
}
