package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* compiled from: NameResolverUtil.kt */
/* loaded from: classes4.dex */
public final class NameResolverUtilKt {
    @NotNull
    public static final ClassId getClassId(@NotNull NameResolver getClassId, int i) {
        Intrinsics.checkParameterIsNotNull(getClassId, "$this$getClassId");
        ClassId fromString = ClassId.fromString(getClassId.getQualifiedClassName(i), getClassId.isLocalClassName(i));
        Intrinsics.checkExpressionValueIsNotNull(fromString, "ClassId.fromString(getQu… isLocalClassName(index))");
        return fromString;
    }

    @NotNull
    public static final Name getName(@NotNull NameResolver getName, int i) {
        Intrinsics.checkParameterIsNotNull(getName, "$this$getName");
        Name guessByFirstCharacter = Name.guessByFirstCharacter(getName.getString(i));
        Intrinsics.checkExpressionValueIsNotNull(guessByFirstCharacter, "Name.guessByFirstCharacter(getString(index))");
        return guessByFirstCharacter;
    }
}
