package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RangesSpecifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class RangesSpecifier$isValid$1 extends Lambda implements Function1<String, Boolean> {
    public static final RangesSpecifier$isValid$1 INSTANCE = new RangesSpecifier$isValid$1();

    RangesSpecifier$isValid$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(String str) {
        return Boolean.valueOf(invoke2(str));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull String it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return Intrinsics.areEqual(it2, RangeUnits.Bytes.getUnitToken());
    }
}
