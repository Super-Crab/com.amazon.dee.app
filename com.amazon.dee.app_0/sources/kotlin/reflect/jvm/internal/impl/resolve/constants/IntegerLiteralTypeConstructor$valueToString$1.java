package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: IntegerLiteralTypeConstructor.kt */
/* loaded from: classes4.dex */
final class IntegerLiteralTypeConstructor$valueToString$1 extends Lambda implements Function1<KotlinType, String> {
    public static final IntegerLiteralTypeConstructor$valueToString$1 INSTANCE = new IntegerLiteralTypeConstructor$valueToString$1();

    IntegerLiteralTypeConstructor$valueToString$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull KotlinType it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return it2.toString();
    }
}
