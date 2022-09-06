package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractPolymorphicSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a \u0010\u0000\u001a\u00020\u00012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0000¨\u0006\u0007"}, d2 = {"throwSubtypeNotRegistered", "", "subClassName", "", "baseClass", "Lkotlin/reflect/KClass;", "subClass", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AbstractPolymorphicSerializerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwSubtypeNotRegistered(String str, KClass<?> kClass) {
        throw new SerializationException(str + " is not registered for polymorphic serialization in the scope of " + kClass, null, 2, null);
    }

    @NotNull
    public static final Void throwSubtypeNotRegistered(@NotNull KClass<?> subClass, @NotNull KClass<?> baseClass) {
        Intrinsics.checkParameterIsNotNull(subClass, "subClass");
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        throwSubtypeNotRegistered(subClass.toString(), baseClass);
        throw null;
    }
}
