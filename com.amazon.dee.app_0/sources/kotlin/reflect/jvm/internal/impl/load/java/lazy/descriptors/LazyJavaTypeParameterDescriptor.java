package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractLazyTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
/* compiled from: LazyJavaTypeParameterDescriptor.kt */
/* loaded from: classes3.dex */
public final class LazyJavaTypeParameterDescriptor extends AbstractLazyTypeParameterDescriptor {
    @NotNull
    private final LazyJavaAnnotations annotations;
    private final LazyJavaResolverContext c;
    @NotNull
    private final JavaTypeParameter javaTypeParameter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaTypeParameterDescriptor(@NotNull LazyJavaResolverContext c, @NotNull JavaTypeParameter javaTypeParameter, int i, @NotNull DeclarationDescriptor containingDeclaration) {
        super(c.getStorageManager(), containingDeclaration, javaTypeParameter.getName(), Variance.INVARIANT, false, i, SourceElement.NO_SOURCE, c.getComponents().getSupertypeLoopChecker());
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(javaTypeParameter, "javaTypeParameter");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        this.c = c;
        this.javaTypeParameter = javaTypeParameter;
        this.annotations = new LazyJavaAnnotations(this.c, this.javaTypeParameter);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    /* renamed from: reportSupertypeLoopError */
    protected void mo12071reportSupertypeLoopError(@NotNull KotlinType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    @NotNull
    protected List<KotlinType> resolveUpperBounds() {
        int collectionSizeOrDefault;
        List<KotlinType> listOf;
        Collection<JavaClassifierType> mo11637getUpperBounds = this.javaTypeParameter.mo11637getUpperBounds();
        if (mo11637getUpperBounds.isEmpty()) {
            SimpleType anyType = this.c.getModule().getBuiltIns().getAnyType();
            Intrinsics.checkExpressionValueIsNotNull(anyType, "c.module.builtIns.anyType");
            SimpleType nullableAnyType = this.c.getModule().getBuiltIns().getNullableAnyType();
            Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "c.module.builtIns.nullableAnyType");
            listOf = CollectionsKt__CollectionsJVMKt.listOf(KotlinTypeFactory.flexibleType(anyType, nullableAnyType));
            return listOf;
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo11637getUpperBounds, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (JavaClassifierType javaClassifierType : mo11637getUpperBounds) {
            arrayList.add(this.c.getTypeResolver().transformJavaType(javaClassifierType, JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, this, 1, null)));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    /* renamed from: getAnnotations  reason: collision with other method in class */
    public LazyJavaAnnotations mo12070getAnnotations() {
        return this.annotations;
    }
}
