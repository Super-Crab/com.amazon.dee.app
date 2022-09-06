package com.facebook.common.references;

import android.graphics.Bitmap;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.FalseOnNull;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public class SharedReference<T> {
    @GuardedBy("itself")
    private static final Map<Object, Integer> sLiveObjects = new IdentityHashMap();
    @GuardedBy("this")
    private int mRefCount = 1;
    private final ResourceReleaser<T> mResourceReleaser;
    @GuardedBy("this")
    @Nullable
    private T mValue;

    /* loaded from: classes2.dex */
    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public SharedReference(T value, ResourceReleaser<T> resourceReleaser) {
        this.mValue = (T) Preconditions.checkNotNull(value);
        this.mResourceReleaser = (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser);
        addLiveReference(value);
    }

    private static void addLiveReference(Object value) {
        if (!CloseableReference.useGc() || (!(value instanceof Bitmap) && !(value instanceof HasBitmap))) {
            synchronized (sLiveObjects) {
                Integer num = sLiveObjects.get(value);
                if (num == null) {
                    sLiveObjects.put(value, 1);
                } else {
                    sLiveObjects.put(value, Integer.valueOf(num.intValue() + 1));
                }
            }
        }
    }

    private synchronized int decreaseRefCount() {
        ensureValid();
        Preconditions.checkArgument(Boolean.valueOf(this.mRefCount > 0));
        this.mRefCount--;
        return this.mRefCount;
    }

    private void ensureValid() {
        if (isValid(this)) {
            return;
        }
        throw new NullReferenceException();
    }

    private static void removeLiveReference(Object value) {
        synchronized (sLiveObjects) {
            Integer num = sLiveObjects.get(value);
            if (num == null) {
                FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", value.getClass());
            } else if (num.intValue() == 1) {
                sLiveObjects.remove(value);
            } else {
                sLiveObjects.put(value, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    public static String reportData() {
        return Objects.toStringHelper("SharedReference").add("live_objects_count", sLiveObjects.size()).toString();
    }

    public synchronized void addReference() {
        ensureValid();
        this.mRefCount++;
    }

    public synchronized boolean addReferenceIfValid() {
        if (isValid()) {
            addReference();
            return true;
        }
        return false;
    }

    public void deleteReference() {
        T t;
        if (decreaseRefCount() == 0) {
            synchronized (this) {
                t = this.mValue;
                this.mValue = null;
            }
            if (t == null) {
                return;
            }
            this.mResourceReleaser.release(t);
            removeLiveReference(t);
        }
    }

    public synchronized boolean deleteReferenceIfValid() {
        if (isValid()) {
            deleteReference();
            return true;
        }
        return false;
    }

    @Nullable
    public synchronized T get() {
        return this.mValue;
    }

    public synchronized int getRefCountTestOnly() {
        return this.mRefCount;
    }

    public synchronized boolean isValid() {
        return this.mRefCount > 0;
    }

    @FalseOnNull
    public static boolean isValid(@Nullable SharedReference<?> ref) {
        return ref != null && ref.isValid();
    }
}
