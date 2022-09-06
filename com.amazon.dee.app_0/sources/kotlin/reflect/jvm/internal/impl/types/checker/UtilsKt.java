package kotlin.reflect.jvm.internal.impl.types.checker;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: utils.kt */
/* loaded from: classes4.dex */
public final class UtilsKt {
    private static final KotlinType approximate(@NotNull KotlinType kotlinType) {
        return CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType).getUpper();
    }

    private static final String debugInfo(@NotNull TypeConstructor typeConstructor) {
        StringBuilder sb = new StringBuilder();
        UtilsKt$debugInfo$1$1 utilsKt$debugInfo$1$1 = new UtilsKt$debugInfo$1$1(sb);
        utilsKt$debugInfo$1$1.mo12165invoke("type: " + typeConstructor);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("hashCode: ");
        outline107.append(typeConstructor.hashCode());
        utilsKt$debugInfo$1$1.mo12165invoke(outline107.toString());
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("javaClass: ");
        outline1072.append(typeConstructor.getClass().getCanonicalName());
        utilsKt$debugInfo$1$1.mo12165invoke(outline1072.toString());
        for (DeclarationDescriptor mo12085getDeclarationDescriptor = typeConstructor.mo12085getDeclarationDescriptor(); mo12085getDeclarationDescriptor != null; mo12085getDeclarationDescriptor = mo12085getDeclarationDescriptor.mo11607getContainingDeclaration()) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("fqName: ");
            outline1073.append(DescriptorRenderer.FQ_NAMES_IN_TYPES.render(mo12085getDeclarationDescriptor));
            utilsKt$debugInfo$1$1.mo12165invoke(outline1073.toString());
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("javaClass: ");
            outline1074.append(mo12085getDeclarationDescriptor.getClass().getCanonicalName());
            utilsKt$debugInfo$1$1.mo12165invoke(outline1074.toString());
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @Nullable
    public static final KotlinType findCorrespondingSupertype(@NotNull KotlinType subtype, @NotNull KotlinType supertype, @NotNull TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(subtype, "subtype");
        Intrinsics.checkParameterIsNotNull(supertype, "supertype");
        Intrinsics.checkParameterIsNotNull(typeCheckingProcedureCallbacks, "typeCheckingProcedureCallbacks");
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(new SubtypePathNode(subtype, null));
        TypeConstructor mo12131getConstructor = supertype.mo12131getConstructor();
        while (!arrayDeque.isEmpty()) {
            SubtypePathNode subtypePathNode = (SubtypePathNode) arrayDeque.poll();
            KotlinType type = subtypePathNode.getType();
            TypeConstructor mo12131getConstructor2 = type.mo12131getConstructor();
            if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(mo12131getConstructor2, mo12131getConstructor)) {
                boolean isMarkedNullable = type.isMarkedNullable();
                for (SubtypePathNode previous = subtypePathNode.getPrevious(); previous != null; previous = previous.getPrevious()) {
                    KotlinType type2 = previous.getType();
                    List<TypeProjection> arguments = type2.getArguments();
                    if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                        for (TypeProjection typeProjection : arguments) {
                            if (typeProjection.getProjectionKind() != Variance.INVARIANT) {
                                z = true;
                                continue;
                            } else {
                                z = false;
                                continue;
                            }
                            if (z) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                    z2 = false;
                    if (z2) {
                        KotlinType safeSubstitute = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(type2), false, 1, null).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                        Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                        type = approximate(safeSubstitute);
                    } else {
                        type = TypeConstructorSubstitution.Companion.create(type2).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                        Intrinsics.checkExpressionValueIsNotNull(type, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                    }
                    isMarkedNullable = isMarkedNullable || type2.isMarkedNullable();
                }
                TypeConstructor mo12131getConstructor3 = type.mo12131getConstructor();
                if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(mo12131getConstructor3, mo12131getConstructor)) {
                    return TypeUtils.makeNullableAsSpecified(type, isMarkedNullable);
                }
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113("Type constructors should be equals!\n", "substitutedSuperType: ");
                GeneratedOutlineSupport1.outline180(outline113, debugInfo(mo12131getConstructor3), ", \n\n", "supertype: ");
                outline113.append(debugInfo(mo12131getConstructor));
                outline113.append(" \n");
                outline113.append(typeCheckingProcedureCallbacks.assertEqualTypeConstructors(mo12131getConstructor3, mo12131getConstructor));
                throw new AssertionError(outline113.toString());
            }
            for (KotlinType immediateSupertype : mo12131getConstructor2.mo12135getSupertypes()) {
                Intrinsics.checkExpressionValueIsNotNull(immediateSupertype, "immediateSupertype");
                arrayDeque.add(new SubtypePathNode(immediateSupertype, subtypePathNode));
            }
        }
        return null;
    }
}
