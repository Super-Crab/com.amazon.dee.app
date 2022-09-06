package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface ClassDescriptor extends ClassOrPackageFragmentDescriptor, ClassifierDescriptorWithTypeParameters {
    @Nullable
    /* renamed from: getCompanionObjectDescriptor */
    ClassDescriptor mo11505getCompanionObjectDescriptor();

    @NotNull
    /* renamed from: getConstructors */
    Collection<ClassConstructorDescriptor> mo11663getConstructors();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration */
    DeclarationDescriptor mo11607getContainingDeclaration();

    @NotNull
    List<TypeParameterDescriptor> getDeclaredTypeParameters();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    SimpleType getDefaultType();

    @NotNull
    ClassKind getKind();

    @NotNull
    MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution);

    @NotNull
    Modality getModality();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getOriginal */
    ClassDescriptor mo11613getOriginal();

    @NotNull
    /* renamed from: getSealedSubclasses */
    Collection<ClassDescriptor> mo11508getSealedSubclasses();

    @NotNull
    /* renamed from: getStaticScope */
    MemberScope mo12047getStaticScope();

    @NotNull
    ReceiverParameterDescriptor getThisAsReceiverParameter();

    @NotNull
    MemberScope getUnsubstitutedInnerClassesScope();

    @NotNull
    /* renamed from: getUnsubstitutedMemberScope */
    MemberScope mo11664getUnsubstitutedMemberScope();

    @Nullable
    /* renamed from: getUnsubstitutedPrimaryConstructor */
    ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor();

    @NotNull
    Visibility getVisibility();

    boolean isCompanionObject();

    boolean isData();

    boolean isInline();
}
