package com.facebook.common.memory;

import com.facebook.common.references.ResourceReleaser;
/* loaded from: classes2.dex */
public interface Pool<V> extends ResourceReleaser<V>, MemoryTrimmable {
    /* renamed from: get */
    V mo6905get(int size);

    @Override // com.facebook.common.references.ResourceReleaser
    void release(V value);
}
