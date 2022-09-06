package com.facebook.common.references;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.FalseOnNull;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public abstract class CloseableReference<T> implements Cloneable, Closeable {
    public static final int REF_TYPE_DEFAULT = 0;
    public static final int REF_TYPE_FINALIZER = 1;
    public static final int REF_TYPE_NOOP = 3;
    public static final int REF_TYPE_REF_COUNT = 2;
    @GuardedBy("this")
    protected boolean mIsClosed = false;
    protected final LeakHandler mLeakHandler;
    protected final SharedReference<T> mSharedReference;
    @Nullable
    protected final Throwable mStacktrace;
    private static Class<CloseableReference> TAG = CloseableReference.class;
    @CloseableRefType
    private static int sBitmapCloseableRefType = 0;
    private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER = new ResourceReleaser<Closeable>() { // from class: com.facebook.common.references.CloseableReference.1
        @Override // com.facebook.common.references.ResourceReleaser
        public void release(Closeable value) {
            try {
                Closeables.close(value, true);
            } catch (IOException unused) {
            }
        }
    };
    private static final LeakHandler DEFAULT_LEAK_HANDLER = new LeakHandler() { // from class: com.facebook.common.references.CloseableReference.2
        @Override // com.facebook.common.references.CloseableReference.LeakHandler
        public void reportLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace) {
            Object obj = reference.get();
            Class cls = CloseableReference.TAG;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(System.identityHashCode(this));
            objArr[1] = Integer.valueOf(System.identityHashCode(reference));
            objArr[2] = obj == null ? null : obj.getClass().getName();
            FLog.w(cls, "Finalized without closing: %x %x (type = %s)", objArr);
        }

        @Override // com.facebook.common.references.CloseableReference.LeakHandler
        public boolean requiresStacktrace() {
            return false;
        }
    };

    /* loaded from: classes2.dex */
    public @interface CloseableRefType {
    }

    /* loaded from: classes2.dex */
    public interface LeakHandler {
        void reportLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace);

        boolean requiresStacktrace();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CloseableReference(SharedReference<T> sharedReference, LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        this.mSharedReference = (SharedReference) Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
        this.mLeakHandler = leakHandler;
        this.mStacktrace = stacktrace;
    }

    public static void closeSafely(@Nullable CloseableReference<?> ref) {
        if (ref != null) {
            ref.close();
        }
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/io/Closeable;>(TT;)Lcom/facebook/common/references/CloseableReference<TT;>; */
    public static CloseableReference of(@PropagatesNullable Closeable t) {
        return of(t, DEFAULT_CLOSEABLE_RELEASER);
    }

    public static void setDisableCloseableReferencesForBitmaps(@CloseableRefType int bitmapCloseableRefType) {
        sBitmapCloseableRefType = bitmapCloseableRefType;
    }

    public static boolean useGc() {
        return sBitmapCloseableRefType == 3;
    }

    /* renamed from: clone */
    public abstract CloseableReference<T> m6865clone();

    @Nullable
    public synchronized CloseableReference<T> cloneOrNull() {
        if (isValid()) {
            return m6865clone();
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            this.mIsClosed = true;
            this.mSharedReference.deleteReference();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                this.mLeakHandler.reportLeak(this.mSharedReference, this.mStacktrace);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public synchronized T get() {
        Preconditions.checkState(!this.mIsClosed);
        return (T) Preconditions.checkNotNull(this.mSharedReference.get());
    }

    @VisibleForTesting
    public synchronized SharedReference<T> getUnderlyingReferenceTestOnly() {
        return this.mSharedReference;
    }

    public int getValueHash() {
        if (isValid()) {
            return System.identityHashCode(this.mSharedReference.get());
        }
        return 0;
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }

    public static void closeSafely(@Nullable Iterable<? extends CloseableReference<?>> references) {
        if (references != null) {
            for (CloseableReference<?> closeableReference : references) {
                closeSafely(closeableReference);
            }
        }
    }

    @FalseOnNull
    public static boolean isValid(@Nullable CloseableReference<?> ref) {
        return ref != null && ref.isValid();
    }

    public static <T> CloseableReference<T> of(@PropagatesNullable T t, ResourceReleaser<T> resourceReleaser) {
        return of(t, resourceReleaser, DEFAULT_LEAK_HANDLER);
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/io/Closeable;>(TT;Lcom/facebook/common/references/CloseableReference$LeakHandler;)Lcom/facebook/common/references/CloseableReference<TT;>; */
    public static CloseableReference of(@PropagatesNullable Closeable t, LeakHandler leakHandler) {
        Throwable th = null;
        if (t == null) {
            return null;
        }
        ResourceReleaser<Closeable> resourceReleaser = DEFAULT_CLOSEABLE_RELEASER;
        if (leakHandler.requiresStacktrace()) {
            th = new Throwable();
        }
        return of(t, resourceReleaser, leakHandler, th);
    }

    @Nullable
    public static <T> CloseableReference<T> cloneOrNull(@Nullable CloseableReference<T> ref) {
        if (ref != null) {
            return ref.cloneOrNull();
        }
        return null;
    }

    public static <T> List<CloseableReference<T>> cloneOrNull(@PropagatesNullable Collection<CloseableReference<T>> refs) {
        if (refs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(refs.size());
        for (CloseableReference<T> closeableReference : refs) {
            arrayList.add(cloneOrNull(closeableReference));
        }
        return arrayList;
    }

    public static <T> CloseableReference<T> of(@PropagatesNullable T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler) {
        Throwable th = null;
        if (t == null) {
            return null;
        }
        if (leakHandler.requiresStacktrace()) {
            th = new Throwable();
        }
        return of(t, resourceReleaser, leakHandler, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CloseableReference(T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        this.mSharedReference = new SharedReference<>(t, resourceReleaser);
        this.mLeakHandler = leakHandler;
        this.mStacktrace = stacktrace;
    }

    public static <T> CloseableReference<T> of(@PropagatesNullable T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        if (t == null) {
            return null;
        }
        if ((t instanceof Bitmap) || (t instanceof HasBitmap)) {
            int i = sBitmapCloseableRefType;
            if (i == 1) {
                return new FinalizerCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
            }
            if (i == 2) {
                return new RefCountCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
            }
            if (i == 3) {
                return new NoOpCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
            }
        }
        return new DefaultCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
    }
}
