package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinTypeFactory.kt */
/* loaded from: classes4.dex */
final class KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1 extends Lambda implements Function1 {
    public static final KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1 INSTANCE = new KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1();

    KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke */
    public final Void mo12165invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "<anonymous parameter 0>");
        return null;
    }
}
