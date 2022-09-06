package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "it", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class URLBuilder$path$1 extends Lambda implements Function1<String, String> {
    public static final URLBuilder$path$1 INSTANCE = new URLBuilder$path$1();

    URLBuilder$path$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull String it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return CodecsKt.encodeURLQueryComponent$default(it2, false, false, null, 7, null);
    }
}
