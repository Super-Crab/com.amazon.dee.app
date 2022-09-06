package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
/* compiled from: KClasses.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/KClass;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class KClasses$isSubclassOf$2 extends Lambda implements Function1<KClass<?>, Boolean> {
    final /* synthetic */ KClass $base;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClasses$isSubclassOf$2(KClass kClass) {
        super(1);
        this.$base = kClass;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(KClass<?> kClass) {
        return Boolean.valueOf(invoke2(kClass));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(KClass<?> kClass) {
        return Intrinsics.areEqual(kClass, this.$base);
    }
}
