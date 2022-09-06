package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaClass.kt */
/* loaded from: classes2.dex */
final class ReflectJavaClass$innerClassNames$2 extends Lambda implements Function1<Class<?>, Name> {
    public static final ReflectJavaClass$innerClassNames$2 INSTANCE = new ReflectJavaClass$innerClassNames$2();

    ReflectJavaClass$innerClassNames$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Name mo12165invoke(Class<?> it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        String simpleName = it2.getSimpleName();
        if (!Name.isValidIdentifier(simpleName)) {
            simpleName = null;
        }
        if (simpleName != null) {
            return Name.identifier(simpleName);
        }
        return null;
    }
}
