package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeAliasExpansionReportStrategy.kt */
/* loaded from: classes4.dex */
public interface TypeAliasExpansionReportStrategy {

    /* compiled from: TypeAliasExpansionReportStrategy.kt */
    /* loaded from: classes4.dex */
    public static final class DO_NOTHING implements TypeAliasExpansionReportStrategy {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        private DO_NOTHING() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void boundsViolationInSubstitution(@NotNull KotlinType bound, @NotNull KotlinType unsubstitutedArgument, @NotNull KotlinType argument, @NotNull TypeParameterDescriptor typeParameter) {
            Intrinsics.checkParameterIsNotNull(bound, "bound");
            Intrinsics.checkParameterIsNotNull(unsubstitutedArgument, "unsubstitutedArgument");
            Intrinsics.checkParameterIsNotNull(argument, "argument");
            Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void conflictingProjection(@NotNull TypeAliasDescriptor typeAlias, @Nullable TypeParameterDescriptor typeParameterDescriptor, @NotNull KotlinType substitutedArgument) {
            Intrinsics.checkParameterIsNotNull(typeAlias, "typeAlias");
            Intrinsics.checkParameterIsNotNull(substitutedArgument, "substitutedArgument");
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void recursiveTypeAlias(@NotNull TypeAliasDescriptor typeAlias) {
            Intrinsics.checkParameterIsNotNull(typeAlias, "typeAlias");
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void repeatedAnnotation(@NotNull AnnotationDescriptor annotation) {
            Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        }
    }

    void boundsViolationInSubstitution(@NotNull KotlinType kotlinType, @NotNull KotlinType kotlinType2, @NotNull KotlinType kotlinType3, @NotNull TypeParameterDescriptor typeParameterDescriptor);

    void conflictingProjection(@NotNull TypeAliasDescriptor typeAliasDescriptor, @Nullable TypeParameterDescriptor typeParameterDescriptor, @NotNull KotlinType kotlinType);

    void recursiveTypeAlias(@NotNull TypeAliasDescriptor typeAliasDescriptor);

    void repeatedAnnotation(@NotNull AnnotationDescriptor annotationDescriptor);
}
