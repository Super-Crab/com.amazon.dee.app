package com.dee.app.cachemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.base.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class CacheMutation<T> {
    static final int PUT = 1;
    static final int REMOVE = 0;
    @NonNull
    final String key;
    final int type;
    @Nullable
    final T value;

    CacheMutation(@NonNull String str, @Nullable T t, int i) {
        Preconditions.checkNotNull(str);
        boolean z = true;
        if ((i != 1 || t == null) && (i != 0 || t != null)) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.key = str;
        this.value = t;
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <U> CacheMutation<U> put(@NonNull String str, @NonNull U u) {
        return new CacheMutation<>(str, u, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <U> CacheMutation<U> remove(@NonNull String str) {
        return new CacheMutation<>(str, null, 0);
    }
}
