package io.ktor.util;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Hash.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\"\u00020\u0001¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/util/Hash;", "", "()V", "combine", "", "objects", "", "([Ljava/lang/Object;)I", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Hash {
    public static final Hash INSTANCE = new Hash();

    private Hash() {
    }

    public final int combine(@NotNull Object... objects) {
        List list;
        Intrinsics.checkParameterIsNotNull(objects, "objects");
        list = ArraysKt___ArraysKt.toList(objects);
        return list.hashCode();
    }
}
