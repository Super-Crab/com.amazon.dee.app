package com.amazon.photos.uploader;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ResultMetadata.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\tJ\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0001J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\tJ\u0016\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\fJ\u0016\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000fJ\u0016\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/uploader/ResultMetadata;", "", "()V", "internalMap", "", "", "getAny", "key", "getBoolean", "", "default", "getInt", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "getLong", "", "(Ljava/lang/String;)Ljava/lang/Long;", "getString", "putAny", "", "value", "putBoolean", "putInt", "putLong", "putString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ResultMetadata {
    private final Map<String, Object> internalMap = new LinkedHashMap();

    public static /* synthetic */ boolean getBoolean$default(ResultMetadata resultMetadata, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return resultMetadata.getBoolean(str, z);
    }

    @Nullable
    public final Object getAny(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.internalMap.get(key);
    }

    public final boolean getBoolean(@NotNull String key, boolean z) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object obj = this.internalMap.get(key);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    @Nullable
    public final Integer getInt(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object obj = this.internalMap.get(key);
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        return null;
    }

    @Nullable
    public final Long getLong(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object obj = this.internalMap.get(key);
        if (obj instanceof Long) {
            return (Long) obj;
        }
        return null;
    }

    @Nullable
    public final String getString(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object obj = this.internalMap.get(key);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public final void putAny(@NotNull String key, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.internalMap.put(key, value);
    }

    public final void putBoolean(@NotNull String key, boolean z) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.internalMap.put(key, Boolean.valueOf(z));
    }

    public final void putInt(@NotNull String key, int i) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.internalMap.put(key, Integer.valueOf(i));
    }

    public final void putLong(@NotNull String key, long j) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.internalMap.put(key, Long.valueOf(j));
    }

    public final void putString(@NotNull String key, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.internalMap.put(key, value);
    }
}
