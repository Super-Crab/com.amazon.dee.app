package com.facebook.common.references;

import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class NoOpCloseableReference<T> extends CloseableReference<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NoOpCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        super(t, resourceReleaser, leakHandler, stacktrace);
    }

    @Override // com.facebook.common.references.CloseableReference
    /* renamed from: clone */
    public CloseableReference<T> mo6869clone() {
        return this;
    }

    @Override // com.facebook.common.references.CloseableReference, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
