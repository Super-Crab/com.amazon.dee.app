package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: functions.kt */
/* loaded from: classes4.dex */
final class FunctionsKt$IDENTITY$1 extends Lambda implements Function1<Object, Object> {
    public static final FunctionsKt$IDENTITY$1 INSTANCE = new FunctionsKt$IDENTITY$1();

    FunctionsKt$IDENTITY$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke */
    public final Object mo12165invoke(@Nullable Object obj) {
        return obj;
    }
}
