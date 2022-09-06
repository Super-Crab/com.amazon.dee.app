package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface DeclarationDescriptor extends Named, Annotated {
    <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d);

    @Nullable
    /* renamed from: getContainingDeclaration */
    DeclarationDescriptor mo11607getContainingDeclaration();

    @NotNull
    /* renamed from: getOriginal */
    DeclarationDescriptor mo11613getOriginal();
}
