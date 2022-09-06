package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: functions.kt */
/* loaded from: classes4.dex */
final class FunctionsKt$DO_NOTHING$1 extends Lambda implements Function1<Object, Unit> {
    public static final FunctionsKt$DO_NOTHING$1 INSTANCE = new FunctionsKt$DO_NOTHING$1();

    FunctionsKt$DO_NOTHING$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Object obj) {
        mo12165invoke(obj);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12165invoke(@Nullable Object obj) {
    }
}
