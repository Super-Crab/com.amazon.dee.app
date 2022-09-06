package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: inlineClassesUtils.kt */
/* loaded from: classes4.dex */
public final class InlineClassesUtilsKt {
    public static final boolean isGetterOfUnderlyingPropertyOfInlineClass(@NotNull CallableDescriptor isGetterOfUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkParameterIsNotNull(isGetterOfUnderlyingPropertyOfInlineClass, "$this$isGetterOfUnderlyingPropertyOfInlineClass");
        if (isGetterOfUnderlyingPropertyOfInlineClass instanceof PropertyGetterDescriptor) {
            PropertyDescriptor correspondingProperty = ((PropertyGetterDescriptor) isGetterOfUnderlyingPropertyOfInlineClass).getCorrespondingProperty();
            Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "correspondingProperty");
            if (isUnderlyingPropertyOfInlineClass(correspondingProperty)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInlineClass(@NotNull DeclarationDescriptor isInlineClass) {
        Intrinsics.checkParameterIsNotNull(isInlineClass, "$this$isInlineClass");
        return (isInlineClass instanceof ClassDescriptor) && ((ClassDescriptor) isInlineClass).isInline();
    }

    public static final boolean isInlineClassType(@NotNull KotlinType isInlineClassType) {
        Intrinsics.checkParameterIsNotNull(isInlineClassType, "$this$isInlineClassType");
        ClassifierDescriptor mo12085getDeclarationDescriptor = isInlineClassType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor != null) {
            return isInlineClass(mo12085getDeclarationDescriptor);
        }
        return false;
    }

    public static final boolean isUnderlyingPropertyOfInlineClass(@NotNull VariableDescriptor isUnderlyingPropertyOfInlineClass) {
        Intrinsics.checkParameterIsNotNull(isUnderlyingPropertyOfInlineClass, "$this$isUnderlyingPropertyOfInlineClass");
        DeclarationDescriptor mo11607getContainingDeclaration = isUnderlyingPropertyOfInlineClass.mo11607getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "this.containingDeclaration");
        if (!isInlineClass(mo11607getContainingDeclaration)) {
            return false;
        }
        ValueParameterDescriptor underlyingRepresentation = underlyingRepresentation((ClassDescriptor) mo11607getContainingDeclaration);
        return Intrinsics.areEqual(underlyingRepresentation != null ? underlyingRepresentation.getName() : null, isUnderlyingPropertyOfInlineClass.getName());
    }

    @Nullable
    public static final KotlinType substitutedUnderlyingType(@NotNull KotlinType substitutedUnderlyingType) {
        Intrinsics.checkParameterIsNotNull(substitutedUnderlyingType, "$this$substitutedUnderlyingType");
        ValueParameterDescriptor unsubstitutedUnderlyingParameter = unsubstitutedUnderlyingParameter(substitutedUnderlyingType);
        if (unsubstitutedUnderlyingParameter != null) {
            MemberScope memberScope = substitutedUnderlyingType.getMemberScope();
            Name name = unsubstitutedUnderlyingParameter.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "parameter.name");
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) CollectionsKt.singleOrNull(memberScope.mo12100getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            if (propertyDescriptor == null) {
                return null;
            }
            return propertyDescriptor.getType();
        }
        return null;
    }

    @Nullable
    public static final ValueParameterDescriptor underlyingRepresentation(@NotNull ClassDescriptor underlyingRepresentation) {
        ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor;
        List<ValueParameterDescriptor> valueParameters;
        Intrinsics.checkParameterIsNotNull(underlyingRepresentation, "$this$underlyingRepresentation");
        if (!underlyingRepresentation.isInline() || (mo11511getUnsubstitutedPrimaryConstructor = underlyingRepresentation.mo11511getUnsubstitutedPrimaryConstructor()) == null || (valueParameters = mo11511getUnsubstitutedPrimaryConstructor.getValueParameters()) == null) {
            return null;
        }
        return (ValueParameterDescriptor) CollectionsKt.singleOrNull((List<? extends Object>) valueParameters);
    }

    @Nullable
    public static final ValueParameterDescriptor unsubstitutedUnderlyingParameter(@NotNull KotlinType unsubstitutedUnderlyingParameter) {
        Intrinsics.checkParameterIsNotNull(unsubstitutedUnderlyingParameter, "$this$unsubstitutedUnderlyingParameter");
        ClassifierDescriptor mo12085getDeclarationDescriptor = unsubstitutedUnderlyingParameter.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
            mo12085getDeclarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
        if (classDescriptor != null) {
            return underlyingRepresentation(classDescriptor);
        }
        return null;
    }
}
