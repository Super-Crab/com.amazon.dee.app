package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: RawType.kt */
/* loaded from: classes3.dex */
public final class RawTypeImpl extends FlexibleType implements RawType {
    private RawTypeImpl(SimpleType simpleType, SimpleType simpleType2, boolean z) {
        super(simpleType, simpleType2);
        if (!z) {
            boolean isSubtypeOf = KotlinTypeChecker.DEFAULT.isSubtypeOf(simpleType, simpleType2);
            if (!_Assertions.ENABLED || isSubtypeOf) {
                return;
            }
            throw new AssertionError("Lower bound " + simpleType + " of a flexible type must be a subtype of the upper bound " + simpleType2);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public SimpleType getDelegate() {
        return getLowerBound();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public MemberScope getMemberScope() {
        ClassifierDescriptor mo12085getDeclarationDescriptor = mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
            mo12085getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
        if (classDescriptor != null) {
            MemberScope memberScope = classDescriptor.getMemberScope(RawSubstitution.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(memberScope, "classDescriptor.getMemberScope(RawSubstitution)");
            return memberScope;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Incorrect classifier: ");
        outline107.append(mo12131getConstructor().mo12085getDeclarationDescriptor());
        throw new IllegalStateException(outline107.toString().toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    @NotNull
    public String render(@NotNull DescriptorRenderer renderer, @NotNull DescriptorRendererOptions options) {
        String joinToString$default;
        List zip;
        Intrinsics.checkParameterIsNotNull(renderer, "renderer");
        Intrinsics.checkParameterIsNotNull(options, "options");
        RawTypeImpl$render$1 rawTypeImpl$render$1 = RawTypeImpl$render$1.INSTANCE;
        RawTypeImpl$render$2 rawTypeImpl$render$2 = new RawTypeImpl$render$2(renderer);
        RawTypeImpl$render$3 rawTypeImpl$render$3 = RawTypeImpl$render$3.INSTANCE;
        String renderType = renderer.renderType(getLowerBound());
        String renderType2 = renderer.renderType(getUpperBound());
        if (options.getDebugMode()) {
            return "raw (" + renderType + ".." + renderType2 + ')';
        } else if (getUpperBound().getArguments().isEmpty()) {
            return renderer.renderFlexibleType(renderType, renderType2, TypeUtilsKt.getBuiltIns(this));
        } else {
            List<String> mo12165invoke = rawTypeImpl$render$2.mo12165invoke((KotlinType) getLowerBound());
            List<String> mo12165invoke2 = rawTypeImpl$render$2.mo12165invoke((KotlinType) getUpperBound());
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(mo12165invoke, ", ", null, null, 0, null, RawTypeImpl$render$newArgs$1.INSTANCE, 30, null);
            zip = CollectionsKt___CollectionsKt.zip(mo12165invoke, mo12165invoke2);
            boolean z = true;
            if (!(zip instanceof Collection) || !zip.isEmpty()) {
                Iterator it2 = zip.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Pair pair = (Pair) it2.next();
                    if (!RawTypeImpl$render$1.INSTANCE.invoke2((String) pair.getFirst(), (String) pair.getSecond())) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                renderType2 = rawTypeImpl$render$3.mo12248invoke(renderType2, joinToString$default);
            }
            String mo12248invoke = rawTypeImpl$render$3.mo12248invoke(renderType, joinToString$default);
            return Intrinsics.areEqual(mo12248invoke, renderType2) ? mo12248invoke : renderer.renderFlexibleType(mo12248invoke, renderType2, TypeUtilsKt.getBuiltIns(this));
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public RawTypeImpl mo12132makeNullableAsSpecified(boolean z) {
        return new RawTypeImpl(getLowerBound().mo12132makeNullableAsSpecified(z), getUpperBound().mo12132makeNullableAsSpecified(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public RawTypeImpl mo12134replaceAnnotations(@NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return new RawTypeImpl(getLowerBound().mo12134replaceAnnotations(newAnnotations), getUpperBound().mo12134replaceAnnotations(newAnnotations));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    /* renamed from: refine */
    public FlexibleType mo12133refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        KotlinType refineType = kotlinTypeRefiner.refineType(getLowerBound());
        if (refineType != null) {
            SimpleType simpleType = (SimpleType) refineType;
            KotlinType refineType2 = kotlinTypeRefiner.refineType(getUpperBound());
            if (refineType2 != null) {
                return new RawTypeImpl(simpleType, (SimpleType) refineType2, true);
            }
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RawTypeImpl(@NotNull SimpleType lowerBound, @NotNull SimpleType upperBound) {
        this(lowerBound, upperBound, false);
        Intrinsics.checkParameterIsNotNull(lowerBound, "lowerBound");
        Intrinsics.checkParameterIsNotNull(upperBound, "upperBound");
    }
}
