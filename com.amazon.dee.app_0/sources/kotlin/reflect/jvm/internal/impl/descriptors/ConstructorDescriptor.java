package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface ConstructorDescriptor extends FunctionDescriptor {
    @NotNull
    ClassDescriptor getConstructedClass();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration */
    ClassifierDescriptorWithTypeParameters mo11607getContainingDeclaration();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    KotlinType getReturnType();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    List<TypeParameterDescriptor> getTypeParameters();

    boolean isPrimary();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    @Nullable
    /* renamed from: substitute */
    ConstructorDescriptor mo12098substitute(@NotNull TypeSubstitutor typeSubstitutor);
}
