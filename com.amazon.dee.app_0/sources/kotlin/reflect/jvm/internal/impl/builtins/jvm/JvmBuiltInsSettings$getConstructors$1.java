package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
final class JvmBuiltInsSettings$getConstructors$1 extends Lambda implements Function2<ConstructorDescriptor, ConstructorDescriptor, Boolean> {
    final /* synthetic */ TypeSubstitutor $substitutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInsSettings$getConstructors$1(TypeSubstitutor typeSubstitutor) {
        super(2);
        this.$substitutor = typeSubstitutor;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12248invoke(ConstructorDescriptor constructorDescriptor, ConstructorDescriptor constructorDescriptor2) {
        return Boolean.valueOf(invoke2(constructorDescriptor, constructorDescriptor2));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull ConstructorDescriptor isEffectivelyTheSameAs, @NotNull ConstructorDescriptor javaConstructor) {
        Intrinsics.checkParameterIsNotNull(isEffectivelyTheSameAs, "$this$isEffectivelyTheSameAs");
        Intrinsics.checkParameterIsNotNull(javaConstructor, "javaConstructor");
        return OverridingUtil.getBothWaysOverridability(isEffectivelyTheSameAs, javaConstructor.mo12098substitute(this.$substitutor)) == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE;
    }
}
