package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ErasedOverridabilityCondition.kt */
/* loaded from: classes3.dex */
public final class ErasedOverridabilityCondition implements ExternalOverridabilityCondition {

    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[OverridingUtil.OverrideCompatibilityInfo.Result.values().length];

        static {
            $EnumSwitchMapping$0[OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE.ordinal()] = 1;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Contract getContract() {
        return ExternalOverridabilityCondition.Contract.SUCCESS_ONLY;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    @NotNull
    public ExternalOverridabilityCondition.Result isOverridable(@NotNull CallableDescriptor superDescriptor, @NotNull CallableDescriptor subDescriptor, @Nullable ClassDescriptor classDescriptor) {
        List<TypeParameterDescriptor> typeParameters;
        Sequence asSequence;
        Sequence map;
        Sequence plus;
        List listOfNotNull;
        Sequence plus2;
        boolean z;
        List<TypeParameterDescriptor> typeParameters2;
        List<TypeParameterDescriptor> emptyList;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(superDescriptor, "superDescriptor");
        Intrinsics.checkParameterIsNotNull(subDescriptor, "subDescriptor");
        if (subDescriptor instanceof JavaMethodDescriptor) {
            JavaMethodDescriptor javaMethodDescriptor = (JavaMethodDescriptor) subDescriptor;
            Intrinsics.checkExpressionValueIsNotNull(javaMethodDescriptor.getTypeParameters(), "subDescriptor.typeParameters");
            if (!(!typeParameters.isEmpty())) {
                OverridingUtil.OverrideCompatibilityInfo basicOverridabilityProblem = OverridingUtil.getBasicOverridabilityProblem(superDescriptor, subDescriptor);
                KotlinType kotlinType = null;
                if ((basicOverridabilityProblem != null ? basicOverridabilityProblem.getResult() : null) != null) {
                    return ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                List<ValueParameterDescriptor> valueParameters = javaMethodDescriptor.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "subDescriptor.valueParameters");
                asSequence = CollectionsKt___CollectionsKt.asSequence(valueParameters);
                map = SequencesKt___SequencesKt.map(asSequence, ErasedOverridabilityCondition$isOverridable$signatureTypes$1.INSTANCE);
                KotlinType returnType = javaMethodDescriptor.getReturnType();
                if (returnType == null) {
                    Intrinsics.throwNpe();
                }
                plus = SequencesKt___SequencesKt.plus(map, returnType);
                ReceiverParameterDescriptor extensionReceiverParameter = javaMethodDescriptor.getExtensionReceiverParameter();
                if (extensionReceiverParameter != null) {
                    kotlinType = extensionReceiverParameter.getType();
                }
                listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(kotlinType);
                plus2 = SequencesKt___SequencesKt.plus((Sequence) plus, (Iterable) listOfNotNull);
                Iterator it2 = plus2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = false;
                        break;
                    }
                    KotlinType kotlinType2 = (KotlinType) it2.next();
                    if (!(!kotlinType2.getArguments().isEmpty()) || (kotlinType2.unwrap() instanceof RawTypeImpl)) {
                        z2 = false;
                        continue;
                    } else {
                        z2 = true;
                        continue;
                    }
                    if (z2) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    return ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                CallableDescriptor mo12098substitute = superDescriptor.mo12098substitute(RawSubstitution.INSTANCE.buildSubstitutor());
                if (mo12098substitute != null) {
                    if (mo12098substitute instanceof SimpleFunctionDescriptor) {
                        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) mo12098substitute;
                        Intrinsics.checkExpressionValueIsNotNull(simpleFunctionDescriptor.getTypeParameters(), "erasedSuper.typeParameters");
                        if (!typeParameters2.isEmpty()) {
                            FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
                            emptyList = CollectionsKt__CollectionsKt.emptyList();
                            mo12098substitute = newCopyBuilder.setTypeParameters(emptyList).build();
                            if (mo12098substitute == null) {
                                Intrinsics.throwNpe();
                            }
                        }
                    }
                    OverridingUtil.OverrideCompatibilityInfo isOverridableByWithoutExternalConditions = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(mo12098substitute, subDescriptor, false);
                    Intrinsics.checkExpressionValueIsNotNull(isOverridableByWithoutExternalConditions, "OverridingUtil.DEFAULT.i…er, subDescriptor, false)");
                    OverridingUtil.OverrideCompatibilityInfo.Result result = isOverridableByWithoutExternalConditions.getResult();
                    Intrinsics.checkExpressionValueIsNotNull(result, "OverridingUtil.DEFAULT.i…Descriptor, false).result");
                    if (WhenMappings.$EnumSwitchMapping$0[result.ordinal()] != 1) {
                        return ExternalOverridabilityCondition.Result.UNKNOWN;
                    }
                    return ExternalOverridabilityCondition.Result.OVERRIDABLE;
                }
                return ExternalOverridabilityCondition.Result.UNKNOWN;
            }
        }
        return ExternalOverridabilityCondition.Result.UNKNOWN;
    }
}
