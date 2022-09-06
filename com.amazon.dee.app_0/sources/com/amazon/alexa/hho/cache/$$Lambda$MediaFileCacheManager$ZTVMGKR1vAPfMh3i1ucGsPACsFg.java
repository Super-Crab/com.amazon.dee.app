package com.amazon.alexa.hho.cache;

import java.io.File;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$ZTVMGKR1vAPfMh3i1ucGsPACsFg  reason: invalid class name */
/* loaded from: classes8.dex */
public final /* synthetic */ class $$Lambda$MediaFileCacheManager$ZTVMGKR1vAPfMh3i1ucGsPACsFg implements Comparator {
    public static final /* synthetic */ $$Lambda$MediaFileCacheManager$ZTVMGKR1vAPfMh3i1ucGsPACsFg INSTANCE = new $$Lambda$MediaFileCacheManager$ZTVMGKR1vAPfMh3i1ucGsPACsFg();

    private /* synthetic */ $$Lambda$MediaFileCacheManager$ZTVMGKR1vAPfMh3i1ucGsPACsFg() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Long.compare(((File) obj).lastModified(), ((File) obj2).lastModified());
        return compare;
    }
}
